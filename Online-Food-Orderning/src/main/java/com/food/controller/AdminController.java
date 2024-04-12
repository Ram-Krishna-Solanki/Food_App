package com.food.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.food.dto.CategoryDto;
import com.food.dto.FoodDto;
import com.food.dto.UserDto;
import com.food.entities.User;
import com.food.repositories.UserRepo;
import com.food.service.CategoryService;
import com.food.service.FoodService;
import com.food.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value={"/admin"})
public class AdminController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
    @Autowired
	private FoodService foodService;
    @Autowired
    private UserRepo userRepo;
	
	@GetMapping("/admin-dashboard")
	public String adminDashboard(Principal principal, Model model)
	{
		String email = principal.getName();
		User user = this.userRepo.findByEmail(email);
		model.addAttribute("firstName",user.getFirstName());
		List<FoodDto> foods = this.foodService.getAllFood();
		model.addAttribute("foods", foods);
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		return "admin/admin-dashboard";
	}
	
	@GetMapping("/admin-page")
	public String adminPage(Model model)
	{
		List<UserDto> users = this.userService.getAllUser();
		model.addAttribute("users", users);
		
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		
		List<FoodDto> foods = this.foodService.getAllFood();
		model.addAttribute("foods",foods);
		return "admin/admin-page";
	}
	
	@GetMapping("/total-categories")
	public String Categories(Model model)
	{
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		return "admin/admin-page";
	}
	
	@GetMapping("/update-category/{categoryId}")
	public String updateCategory(@PathVariable("categoryId")Integer categoryId,Model model,HttpSession session)
	{
		CategoryDto categoryDto = this.categoryService.updateCategory(categoryId);
	    model.addAttribute("categoryDto", categoryDto);
	    session.setAttribute("message","Category updated successfully!!");
	    return "/admin/update-category";
	}
	
	@GetMapping("/update-food/{foodId}")
	public String updateFood(@PathVariable("foodId")Integer foodId,Model model,HttpSession session)
	{
		FoodDto foodDto = this.foodService.updateFood(foodId);
		model.addAttribute("foodDto", foodDto);
		List<CategoryDto> categories = this.categoryService.getAllCategory();
		model.addAttribute("categories", categories);
		session.setAttribute("message","Food updated successfully!!");
		return "admin/update-food";
	}
	
	
	@GetMapping("/delete-category/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") Integer categoryId,Model model,HttpSession session)
	{
		this.categoryService.deleteCategory(categoryId);
		session.setAttribute("message","Category deleted successfully!!");
		return "redirect:/admin/admin-page";
	}
	
	@GetMapping("/delete-food/{foodId}")
	public String deleteFood(@PathVariable("foodId") Integer foodId,Model model,HttpSession session)
	{
		this.foodService.deleteFood(foodId);
		session.setAttribute("message", "Food deleted successfully!!");
		return "redirect:/admin/admin-page";
	}
	
	
	@PostMapping("/add-food")
	public String addFood(@ModelAttribute("foodDto") FoodDto foodDto, @RequestParam("foodImage") MultipartFile file, Model model,
			HttpSession session, ModelMap modelmap) throws IOException {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());

		try {
			File imageFolder = new File(new ClassPathResource("/static/images/").getFile().getPath());
			if (!imageFolder.exists()) {
				imageFolder.mkdir();
			}

			Path path = Paths.get(imageFolder.getAbsolutePath() + File.separator + file.getOriginalFilename());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			System.out.println(path);
			model.addAttribute("photo", file.getOriginalFilename());
			System.out.println("file uploaded successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		foodDto.setImage(file.getOriginalFilename());
		this.foodService.saveFood(foodDto);
		session.setAttribute("message", "Food added successfully!!");
		return "redirect:/admin/admin-page";
	}
	
	@PostMapping("/add-category")
	public String addCategory(@ModelAttribute("categoryDto")CategoryDto categoryDto, Model model,HttpSession session)
	{
		this.categoryService.saveCategory(categoryDto);
		session.setAttribute("message", "Category added successfully!!");
		return "redirect:/admin/admin-page";
	}
	
	@GetMapping("/categoryId/{categoryId}")
	public String getFoodByCategoryId(@PathVariable("categoryId") Integer categoryId, Model model) {
		List<FoodDto> foods = this.foodService.getFoodByCategoryId(categoryId);
		model.addAttribute("foods", foods);
		List<CategoryDto> categ = this.categoryService.getAllCategory();
		model.addAttribute("categories", categ);
		return "admin/admin-dashboard";
	}


}
