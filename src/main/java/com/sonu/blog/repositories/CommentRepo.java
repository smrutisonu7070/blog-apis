package com.sonu.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonu.blog.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
