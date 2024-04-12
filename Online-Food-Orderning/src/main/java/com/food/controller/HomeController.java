package com.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.food.dto.UserDto;
import com.food.exception.Message;
import com.food.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("title", "Home");
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("title", "Register");
		model.addAttribute("userDto", userDto);
		return "register";
	}

	@PostMapping("/user-register")
	public String registerUser(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreeemnt, HttpSession session) {
		try {
			if (!agreeemnt) {
				throw new Exception("Please agreed the terms & conditions!!");
			}

			if (result.hasErrors()) {
				model.addAttribute("userDto", userDto);
				System.out.println(result.getFieldErrors("firstName"));
				return "redirect:/register";
			}

			// save userDto in DB
			String password = passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(password);
			userDto.setRole("ROLE_USER");
			this.userService.saveUser(userDto);
			model.addAttribute("userDto", new UserDto());
			session.setAttribute("message", new Message("Registration Successfully!!", "alert-success"));
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("message", new Message("Please agreed the terms & conditions", "alert-danger"));
		}

		return "register";
	}

}
