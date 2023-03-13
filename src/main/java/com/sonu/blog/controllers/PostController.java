package com.sonu.blog.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sonu.blog.config.AppConstants;
import com.sonu.blog.payloads.ApiResponse;
import com.sonu.blog.payloads.PostDto;
import com.sonu.blog.payloads.PostResponse;
import com.sonu.blog.services.FileService;
import com.sonu.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("$(project.image)")
	private String path;
	
	//POST- create post
		@PostMapping("/user/{userId}/category/{categoryId}/posts")
		public ResponseEntity<PostDto> createCategory(@Valid @RequestBody PostDto postDto,
				                                              @PathVariable Integer userId,
				                                              @PathVariable Integer categoryId) {
			
			PostDto createdPostDto = this.postService.createPost(postDto, userId, categoryId);
			return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
		}
		
		// get post By userId
		
		@GetMapping("/user/{userId}/posts")
		public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
			
			List<PostDto> posts = this.postService.getAllPostByUser(userId);
			return new ResponseEntity<>(posts, HttpStatus.OK);		
		}
		
		// get post By categoryId
		
				@GetMapping("/category/{categoryId}/posts")
				public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
					
					List<PostDto> posts = this.postService.getAllPostByCategory(categoryId);
					return new ResponseEntity<>(posts, HttpStatus.OK);	
				}
		
		// get all posts
				
				@GetMapping("/posts")
				public ResponseEntity<PostResponse> getAllPost(
						@RequestParam(value="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required= false) Integer pageNumber,
						@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required= false) Integer pageSize,
						@RequestParam(value="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
						@RequestParam(value="sortDir", defaultValue= AppConstants.SORT_DIR, required = false) String sortDir
						) {
					
					PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
					
					return new ResponseEntity<>(postResponse, HttpStatus.OK);
				}
				
				// get post by id
				

				@GetMapping("/posts/{postId}")
				public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
					
					PostDto post = this.postService.getPostById(postId);
					
					return new ResponseEntity<>(post, HttpStatus.OK);
				}
				
				// delete post by Id
				@DeleteMapping("/posts/{postId}")
				public ApiResponse deletePost(@PathVariable Integer postId) {
					
					this.postService.deletePost(postId);
					
					return new ApiResponse("post deleted succesfully", true);
				}
				
				// update post by Id
				@PutMapping("/posts/{postId}")
				public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
					
					PostDto updatedPost = this.postService.updatePost(postDto, postId);
					
					return new ResponseEntity<>(updatedPost, HttpStatus.OK);
				}
		
				// search 
				@GetMapping("/posts/search/{keywords}")
				public ResponseEntity<List<PostDto>> searchPostByTitle(
						@PathVariable String keywords) {
					
					List<PostDto> result = this.postService.searchPosts(keywords);
					
					return new ResponseEntity<> (result, HttpStatus.OK);
				}
				
				
				// post image upload
				
				@PostMapping("/post/image/upload/{postId}")
				public ResponseEntity<PostDto> uploadIamge(
						@RequestParam("image") MultipartFile image,
						@PathVariable Integer postId) throws IOException {
					
					String fileName;
                         
					PostDto postDto  = this.postService.getPostById(postId);
						fileName = this.fileService.uploadImage(path, image);
					
					postDto.setImageName(fileName);
					PostDto updatedPost =  this.postService.updatePost(postDto, postId);
					
					return new ResponseEntity<> (updatedPost, HttpStatus.OK);
				}
				
			// method to serve file
				
				@GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
				public void downloadImage(
						@PathVariable("imageName") String imageName,
						HttpServletResponse response) throws IOException {
					
					InputStream resource = this.fileService.getResource(path, imageName);
					response.setContentType(MediaType.IMAGE_JPEG_VALUE);
					StreamUtils.copy(resource, response.getOutputStream());
				}
				
				
				
				
}
