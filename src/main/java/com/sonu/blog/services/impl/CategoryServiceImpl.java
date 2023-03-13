package com.sonu.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonu.blog.entity.Category;
import com.sonu.blog.exceptions.ResourceNotFoundException;
import com.sonu.blog.payloads.CategoryDto;
import com.sonu.blog.repositories.CategoryRepo;
import com.sonu.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(cat);
		
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto udateCategory(CategoryDto categoryDto, Integer categoryId) {
		
     Category cat = this.categoryRepo.findById(categoryId).
	    orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryId));
     
     cat.setCategoryTitle(categoryDto.getCategoryTitle());
     cat.setCategoryDescription(categoryDto.getCategoryDescription());
     Category updatedCategory = this.categoryRepo.save(cat);
     
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		
		 Category cat = this.categoryRepo.findById(categoryId).
				    orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryId));
		 
		 this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId).
			    orElseThrow(() -> new ResourceNotFoundException("Category", "categoryID", categoryId));
	 
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		
		List<Category> categories = this.categoryRepo.findAll();
		
		List<CategoryDto> categoryDtos = categories.stream().map(cat -> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return null;
	}
	

}
