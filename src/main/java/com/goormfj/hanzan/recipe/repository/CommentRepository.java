package com.goormfj.hanzan.recipe.repository;

import com.goormfj.hanzan.recipe.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Additional custom methods can be declared here.
}
