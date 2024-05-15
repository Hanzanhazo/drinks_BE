package com.goormfj.hanzan.recipe.repository;

import com.goormfj.hanzan.recipe.entity.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AlcoholRepository extends JpaRepository<Alcohol, Long> {
    Page<Alcohol> findByType(String type, Pageable pageable);

}
