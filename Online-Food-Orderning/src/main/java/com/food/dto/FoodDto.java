package com.food.dto;

import com.food.entities.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {
	private int id;
	private String title;
	private String description;
	private String price;
	private String discount;
	private String image;
	private Category category;
	
	public double getPriceAfterDiscount()
	{
		int d=(int) ((int)((Double.valueOf(this.getPrice()))/100.0)*Double.valueOf(this.getDiscount()));
		return Double.valueOf(this.getPrice())-d;
	}
	public double getPriceAfterDiscountForCart(String price,String discount)
	{
		int d=(int) ((int)((Double.valueOf(price))/100.0)*Double.valueOf(discount));
		return Double.valueOf(price)-d;
	}
}
