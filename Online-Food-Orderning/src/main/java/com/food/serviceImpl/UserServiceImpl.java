package com.food.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.food.dto.FoodDto;
import com.food.dto.UserDto;
import com.food.entities.Food;
import com.food.entities.User;
import com.food.exception.ResourceNotFoundException;
import com.food.repositories.UserRepo;
import com.food.service.UserService;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void saveUser(UserDto userDto) {
       User user = this.modelMapper.map(userDto,User.class);
       this.userRepo.save(user);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id", userId));
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user->this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return userDtos;
	}
	
	@Override
	public void removeMessage() {
		// TODO Auto-generated method stub
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();
		session.removeAttribute("message");
		
	}

	@Override
	public List<FoodDto> searchFood(String query) {
		List<Food> foods = this.userRepo.searchProducts(query);
		List<FoodDto> foodDtos = foods.stream().map(f->this.modelMapper.map(f, FoodDto.class)).collect(Collectors.toList());
		return foodDtos;
	}

}
