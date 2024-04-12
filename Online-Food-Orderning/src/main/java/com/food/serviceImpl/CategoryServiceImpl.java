package com.food.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.dto.CategoryDto;
import com.food.entities.Category;
import com.food.exception.ResourceNotFoundException;
import com.food.repositories.CategoryRepo;
import com.food.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
		return this.modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public void saveCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto,Category.class);
        this.categoryRepo.save(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoriesDtos = categories.stream().map(category->this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
		return categoriesDtos;
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","id",categoryId));
		this.categoryRepo.delete(category);
		
	}

}
