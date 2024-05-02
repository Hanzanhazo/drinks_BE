package com.goormfj.hanzan.recipe.repository;

import com.goormfj.hanzan.recipe.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByOrderByLikesDesc();

    Page<Recipe> findByTypeOrderByLikesDesc(String type, Pageable pageable);

    // 태그 기반 검색을 위한 메소드
    @Query("SELECT r FROM Recipe r JOIN r.tags t WHERE t IN :tags")
    Page<Recipe> findByTagsIn(List<String> tags, Pageable pageable);
}
