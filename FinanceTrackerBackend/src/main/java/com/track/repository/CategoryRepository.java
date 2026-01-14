package com.track.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.track.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
