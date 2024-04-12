package com.food.cart;

import java.util.ArrayList;
import java.util.List;

import com.food.dto.FoodDto;

public class Cart {

	public static List<FoodDto> cart;
	
	static {
		cart=new ArrayList<FoodDto>();
	}
}
