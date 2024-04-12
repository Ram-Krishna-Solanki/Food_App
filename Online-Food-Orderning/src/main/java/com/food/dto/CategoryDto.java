package com.food.dto;

import java.util.ArrayList;
import java.util.List;

import com.food.entities.Food;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CategoryDto {
	private int id;
	private String title;
	@Column(length = 1000)
	private String description;
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Food> foods = new ArrayList<>();
}
