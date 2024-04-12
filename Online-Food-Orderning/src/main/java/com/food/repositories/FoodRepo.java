package com.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.dto.FoodDto;
import com.food.entities.Food;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {

	public List<Food>findFoodByCategoryId(Integer categoryId);
}
