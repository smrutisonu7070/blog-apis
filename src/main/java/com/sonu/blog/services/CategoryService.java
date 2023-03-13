package com.sonu.blog.services;

import java.util.List;

import com.sonu.blog.payloads.CategoryDto;

public interface CategoryService {

	// create
	public CategoryDto createCategory(CategoryDto categoryDto);
	// update
	public CategoryDto udateCategory(CategoryDto categoryDto, Integer categoryId);
	// delete
	public void deleteCategory( Integer categoryId);
	//get
	public CategoryDto getCategory( Integer categoryId);
	//get all
	public List<CategoryDto> getAllCategory();
}
