package com.food.service;

import java.util.List;

import com.food.dto.CategoryDto;

public interface CategoryService {

	public abstract CategoryDto getCategoryById(Integer categoryId);
	
	public abstract void saveCategory(CategoryDto categoryDto);
	
	public abstract List<CategoryDto> getAllCategory();
	
	public abstract CategoryDto updateCategory(Integer categoryId);
	
	public abstract void deleteCategory(Integer categoryId);
}
