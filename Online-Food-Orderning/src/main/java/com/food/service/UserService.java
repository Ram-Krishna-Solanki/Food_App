package com.food.service;

import java.util.List;

import com.food.dto.FoodDto;
import com.food.dto.UserDto;

public interface UserService {

	public abstract void saveUser(UserDto userDto);
	
	public UserDto getUserById(Integer userId);
	
	public List<UserDto> getAllUser();
	
	public void removeMessage();
	
	public List<FoodDto> searchFood(String query);
}

