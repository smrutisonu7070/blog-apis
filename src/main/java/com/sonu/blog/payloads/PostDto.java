package com.sonu.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sonu.blog.entity.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private String id;
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<Comment> commnets = new HashSet<>();

}
