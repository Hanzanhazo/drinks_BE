package com.goormfj.hanzan.drinkTest.service;

import com.goormfj.hanzan.drinkTest.domain.User;
import com.goormfj.hanzan.drinkTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("사용자 정보 저장 중 오류 발생", e);
        }
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        // userid 반환
        return userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자가 없습니다."));

    }
}
