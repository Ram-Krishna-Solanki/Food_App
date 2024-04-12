package com.food.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.food.dto.FoodDto;
import com.food.entities.Food;
import com.food.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	
	@Query("SELECT f FROM Food f WHERE " +"f.title LIKE CONCAT('%',:query, '%')")
	List<Food> searchProducts(String query);
}
