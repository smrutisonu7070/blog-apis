package com.sonu.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonu.blog.payloads.ApiResponse;
import com.sonu.blog.payloads.UserDto;
import com.sonu.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//POST- create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		
		UserDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}
	//PUT- update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		
		UserDto updatedUserDto = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUserDto);
	}
	
	// DELETE- delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId) {
		
		this.userService.deleteUser(uId);
		
		// return new ResponseEntity(Map.of("message", "User deleted Successfully"), HttpStatus.ok);
		return new ResponseEntity<>(new ApiResponse("user Deleted Successfully", true), HttpStatus.OK);
	}
	
	// GET- user get
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
