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
import com.sonu.blog.payloads.CategoryDto;
import com.sonu.blog.payloads.UserDto;
import com.sonu.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	//POST- create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		
		CategoryDto createdCategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
	}
	//PUT- update category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
		
		CategoryDto updatedCategoryDto = this.categoryService.udateCategory(categoryDto, categoryId);
		return ResponseEntity.ok(updatedCategoryDto);
	}
	
	// DELETE- delete user
	@DeleteMapping("/{CategoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer cId) {
		
		this.categoryService.deleteCategory(cId);
		
		// return new ResponseEntity(Map.of("message", "User deleted Successfully"), HttpStatus.ok);
		return new ResponseEntity<>(new ApiResponse("user Deleted Successfully", true), HttpStatus.OK);
	}
	
	// GET- all category & categoryById
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		
		return ResponseEntity.ok(this.categoryService.getAllCategory());
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
		
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
	}
}
