package com.goormfj.hanzan.jwt;

import com.goormfj.hanzan.oauth2.dto.CustomOAuth2User;
import com.goormfj.hanzan.user.domain.CustomUserDetails;
import com.goormfj.hanzan.user.domain.Member;
import com.goormfj.hanzan.user.domain.Role;
//import com.goormfj.hanzan.user.dto.MemberDTO;
//import com.goormfj.hanzan.user.oauth2.dto.CustomOAuth2User;
import com.goormfj.hanzan.user.dto.MemberDTO;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();

        if (requestUri.matches("^\\/login(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }
        if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {

            filterChain.doFilter(request, response);
            return;
        }

        String accessToken = request.getHeader("Authorization");
        // 토큰이 없다면 다음 필터로 넘김
        if (accessToken == null) {

            filterChain.doFilter(request, response);

            return;
        }

        // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
        try {
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("access token expired");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(accessToken);

        if (!category.equals("access")) {

            //response body
            PrintWriter writer = response.getWriter();
            writer.print("invalid access token");

            //response status code
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 토큰에서 userId와 role 획득
        String userId = jwtUtil.getUserId(accessToken);
        String roleString = jwtUtil.getRole(accessToken);
        Role role = Role.valueOf(roleString);

        // 사용자 정보 설정

        Authentication authToken = null;

        if (Pattern.matches("^kakao\\s.*", userId)) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setUserId(userId);
            memberDTO.setRole(roleString);

            CustomOAuth2User customOAuth2User = new CustomOAuth2User(memberDTO);

//            스프링 시큐리티 인증 토큰 생성
            authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        } else {
            Member member = new Member(userId, "", role);
            CustomUserDetails customUserDetails = new CustomUserDetails(member);

            // 스프링 시큐리티 인증 토큰 생성 및 등록
            authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        }

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);

    }
}
