package com.food.service;

import java.util.List;

import com.food.dto.FoodDto;

public interface FoodService {

	public abstract void saveFood(FoodDto foodDto);
	
	public abstract FoodDto getFoodById(Integer foodId);
	
	public abstract List<FoodDto> getAllFood();
	
	public abstract void deleteFood(Integer foodId);
	
	public abstract FoodDto updateFood(Integer foodId);
	
	public List<FoodDto> getFoodByCategoryId(Integer categorId);
}
