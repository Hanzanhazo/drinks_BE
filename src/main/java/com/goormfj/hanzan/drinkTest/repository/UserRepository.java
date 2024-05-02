package com.goormfj.hanzan.drinkTest.repository;

import com.goormfj.hanzan.drinkTest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserEmail(String userEmail);
}
