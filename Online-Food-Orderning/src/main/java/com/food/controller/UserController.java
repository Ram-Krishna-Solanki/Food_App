package com.food.controller;

import java.security.Principal;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.food.cart.Cart;
import com.food.dto.CategoryDto;
import com.food.dto.FoodDto;
import com.food.entities.User;
import com.food.repositories.UserRepo;
import com.food.service.CategoryService;
import com.food.service.FoodService;
import com.food.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private FoodService foodService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserService userService;

	@GetMapping("/user-dashboard")
	public String userDashboard(Principal principal,Model model) {
		String email = principal.getName();
		User user = this.userRepo.findByEmail(email);
		model.addAttribute("firstName",user.getFirstName());
		List<FoodDto> foods = this.foodService.getAllFood();
		model.addAttribute("foods", foods);
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		model.addAttribute("c",Cart.cart.size());
		return "user/user-dashboard";
	}
	
	
	
	@GetMapping("/categoryId/{categoryId}")
	public String getFoodByCategoryId(@PathVariable("categoryId") Integer categoryId, Model model) {
		List<FoodDto> foods = this.foodService.getFoodByCategoryId(categoryId);
		model.addAttribute("foods", foods);
		List<CategoryDto> categ = this.categoryService.getAllCategory();
		model.addAttribute("categories", categ);
		return "user/user-dashboard";
	}
	
	@GetMapping("/add-cart/{foodId}")
	public String addCart(@PathVariable("foodId")Integer foodId,Model model)
	{
		FoodDto foodDto = this.foodService.getFoodById(foodId);
		Cart.cart.add(foodDto);
		System.out.println("cart "+Cart.cart.size());
		model.addAttribute("c",Cart.cart.size());
		return "redirect:/user/user-dashboard";
	}
	
	@GetMapping("/cart-show")
	public String getCart(Model model)
	{
		model.addAttribute("c",Cart.cart.size());
		List<Double> cartprices = Cart.cart.stream().map(c->c.getPriceAfterDiscountForCart(c.getPrice(), c.getDiscount())).collect(Collectors.toList());
		double sum=0.0;
		for (Double price : cartprices) {
			sum=sum+price;
		}
		model.addAttribute("sum",sum);
		model.addAttribute("cart", Cart.cart);
		return "user/card-show";
	}
	
	@GetMapping("/remove-cart-item/{index}")
	public String removeCartItem(@PathVariable("index")int index)
	{
		Cart.cart.remove(index);
		return "redirect:/user/cart-show";
	}
	
	@GetMapping("/checkout")
	public String checkOutPage()
	{
		return "/user/checkout-page";
	}
	
	@GetMapping("/search")
	public String getFoodBySearch(@RequestParam("query")String query, Model model) {
		List<CategoryDto> categ = this.categoryService.getAllCategory();
		model.addAttribute("categories", categ);
		List<FoodDto> foods = this.userService.searchFood(query);
		model.addAttribute("foods", foods);
		System.out.println("Foods"+foods);
		model.addAttribute("c",Cart.cart.size());
		return "user/user-dashboard";
	}
	
	@GetMapping("/about")
	public String about()
	{
		return "/user/about";
	}
	
	
}


