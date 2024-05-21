package com.goormfj.hanzan.jwt;

import com.goormfj.hanzan.oauth2.dto.CustomOAuth2User;
import com.goormfj.hanzan.user.domain.CustomUserDetails;
import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.domain.Role;
import com.goormfj.hanzan.user.dto.MemberDTO;
import com.goormfj.hanzan.user.repository.MemberRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        log.debug("Processing URI: {}", requestUri);

        // Skip filter for login and OAuth2 routes
        if (requestUri.matches("^\\/login(?:\\/.*)?$") || requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {
            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = request.getHeader("Authorization");
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            log.debug("No or malformed Authorization header.");
            filterChain.doFilter(request, response);
            return;
        }

        accessToken = accessToken.substring(7).trim(); // Remove 'Bearer ' prefix and trim any whitespace

        try {
            if (jwtUtil.isExpired(accessToken)) {
                throw new ExpiredJwtException(null, null, "Token expired");
            }

            String category = jwtUtil.getCategory(accessToken);
            if (!"access".equals(category)) {
                throw new IllegalArgumentException("Invalid token category: " + category);
            }

            String userId = jwtUtil.getUserId(accessToken);
            String roleString = jwtUtil.getRole(accessToken);
            Role role = Role.valueOf(roleString);
            log.debug("Token valid for userId: {} with role: {}", userId, role);

            Authentication authToken = createAuthentication(userId, role);
            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (ExpiredJwtException e) {
            log.error("Token expired: {}", e.getMessage());
            respondWithError(response, "Access token expired");
            return;
        } catch (Exception e) {
            log.error("Token validation error: {}", e.getMessage());
            respondWithError(response, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private Authentication createAuthentication(String userId, Role role) {
        Member member = memberRepository.findMemberByUserId(userId);
        if (member == null) {
            throw new UsernameNotFoundException("User not found with userId: " + userId);
        }

        if (Pattern.matches("^kakao\\s.*", userId)) {
            MemberDTO memberDTO = new MemberDTO(member.getUserId(), member.getName(), member.getEmail(), role);
            CustomOAuth2User customOAuth2User = new CustomOAuth2User(memberDTO);
            return new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        } else {
            CustomUserDetails customUserDetails = new CustomUserDetails(member);
            return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        }
    }

    private void respondWithError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.print("{\"error\": \"" + message + "\"}");
        writer.flush();
    }
}
