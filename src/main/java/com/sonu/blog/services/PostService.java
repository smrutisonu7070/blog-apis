package com.sonu.blog.services;

import java.util.List;

import com.sonu.blog.payloads.PostDto;
import com.sonu.blog.payloads.PostResponse;

public interface PostService {

	// create post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete post
	void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	PostDto getPostById(Integer postId);
	List<PostDto> getAllPostByCategory(Integer categoryId);
	List<PostDto> getAllPostByUser(Integer userId);
	List<PostDto> searchPosts(String KeyWord);


	
	
	
}
