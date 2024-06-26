package com.food.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	
}
