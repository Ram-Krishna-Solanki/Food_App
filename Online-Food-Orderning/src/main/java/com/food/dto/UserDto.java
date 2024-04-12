package com.food.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private int id;
	@Column(name = "first_name")
	@NotEmpty(message = "first name fields is required")
	@Size(min = 2, max = 20, message = "first name length must be between 2 to 20 characters")
	private String firstName;
	@NotEmpty(message = "last name fields is required")
	@Size(min = 2, max = 20, message = "last name length must be between 2 to 20 characters")
	private String lastName;
	@NotEmpty(message = "email fields is required")
	@Size(min = 2, max = 25, message = "email length must be between 2 to 25 characters")
	private String email;
	@NotEmpty(message = "mobile fields is required")
	@Size(min = 2, max = 15, message = "mobile length must be between 2 to 30 characters")
	private String mobile;
	@NotEmpty(message = "street fields is required")
	@Size(min = 2, max = 50, message = "street length must be between 2 to 50 characters")
	private String street;
	@NotEmpty(message = "city fields is required")
	@Size(min = 2, max = 20, message = "city length must be between 2 to 20 characters") 
	String city;
	@NotEmpty(message = "pincode fields is required")
	@Size(min = 2, max = 10, message = "pincode length must be between 2 to 10 characters") 
	private String pincode;
	@NotEmpty(message = "password fields is required")
	@Size(min = 2, max = 15, message = "password length must be between 2 to 15 characters")
	private String password;
	
	private String role;
}
