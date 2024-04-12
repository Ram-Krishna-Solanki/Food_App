package com.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OnlineFoodOrderningApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderningApplication.class, args);
	}

}



/*
step :
	customUser class-->implementS UserService
	
	customUserDetailsService class-->implements UserDetailsService
	
	Create Security class-->
	
	1.create BcryptPsswordEncoder Bean
	2.Create DaoProvider Bean-->userDeatils obj, Password encoder
	3.Security chain configuration
	
	
	
	UserNamePasswordAuthToken
	 AuthenticationManager
	           |
	        Provider
	   (DaoAuthenticationProvider)
	           |
	      ----------------- 
	      |               |
	UserDetailsService   PasswordEncoder
	      |
	  UserDetails   
	
*/	