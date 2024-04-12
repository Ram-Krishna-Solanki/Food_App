package com.food.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.dto.CategoryDto;
import com.food.dto.FoodDto;
import com.food.entities.Category;
import com.food.entities.Food;
import com.food.exception.ResourceNotFoundException;
import com.food.repositories.FoodRepo;
import com.food.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepo foodRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public void saveFood(FoodDto foodDto) {
	Food food = this.modelMapper.map(foodDto, Food.class);
	this.foodRepo.save(food);

	}

	@Override
	public FoodDto getFoodById(Integer foodId) {
		Food food = this.foodRepo.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("food","id", foodId));
		return this.modelMapper.map(food, FoodDto.class);
	}

	@Override
	public List<FoodDto> getAllFood() {
		List<Food> foods = this.foodRepo.findAll();
		List<FoodDto> foodDtos = foods.stream().map(food->this.modelMapper.map(food,FoodDto.class)).collect(Collectors.toList());
		return foodDtos;
	}
	
	@Override
	public void deleteFood(Integer foodId) {
		Food food = this.foodRepo.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("food","id",foodId));
		this.foodRepo.delete(food);
		
	}
	
	@Override
	public FoodDto updateFood(Integer foodId) {
		Food food = this.foodRepo.findById(foodId).orElseThrow(()-> new ResourceNotFoundException("food","id",foodId));
		FoodDto foodDto = this.modelMapper.map(food, FoodDto.class);
		return foodDto;
	}
	
	@Override
	public List<FoodDto> getFoodByCategoryId(Integer categorId) {
		List<Food> foods = this.foodRepo.findFoodByCategoryId(categorId);
		System.out.println(foods);
		List<FoodDto> foodDtos = foods.stream().map(food->this.modelMapper.map(food, FoodDto.class)).collect(Collectors.toList());
		return foodDtos;
	}



}
