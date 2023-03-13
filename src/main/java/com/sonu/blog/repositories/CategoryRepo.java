package com.sonu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonu.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
