package com.api.library.domain.repository;

import com.api.library.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Page<Category> findAll(Pageable page);
    Optional<Category> findByName(String name);
}