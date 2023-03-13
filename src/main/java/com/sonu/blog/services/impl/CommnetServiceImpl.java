package com.sonu.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonu.blog.entity.Comment;
import com.sonu.blog.entity.Post;
import com.sonu.blog.exceptions.ResourceNotFoundException;
import com.sonu.blog.payloads.CommentDto;
import com.sonu.blog.repositories.CommentRepo;
import com.sonu.blog.repositories.PostRepo;
import com.sonu.blog.services.CommentService;

@Service
public class CommnetServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	
		Post post = this.postRepo.findById(postId).orElseThrow(
				()-> new ResourceNotFoundException("Post", "postId", postId) );
		
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {

		Comment com = this.commentRepo.findById(commentId).orElseThrow(
				() -> new ResourceNotFoundException("comment", "commentId", commentId));
		
		this.commentRepo.delete(com);
	}

}
