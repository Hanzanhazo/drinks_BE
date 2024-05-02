package com.goormfj.hanzan.drinkTest.repository;

import com.goormfj.hanzan.drinkTest.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
    // 사용자 ID로 테스트 결과를 찾는 메서드
    TestResult findByUserId(Long userId);

    // 사용자 ID로 기존 테스트 결과를 삭제하는 메서드
    void deleteByUserId(Long userId);
}
