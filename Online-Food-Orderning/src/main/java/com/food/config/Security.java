package com.food.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Security {

	@Autowired
	private CustomSuccessHandler CustomSuccessHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//		http.csrf().disable()
//		.authorizeHttpRequests().requestMatchers("/","/register","/saveuser","/login").permitAll().requestMatchers("/css/**","/js/**","/images/**").permitAll()
//		.requestMatchers("/user/**").authenticated().and()
//		.formLogin().loginPage("/login").loginProcessingUrl("/userlogin")
//		.defaultSuccessUrl("/user/user-dashboard").permitAll();
//		return http.build();

		http.csrf().disable().authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER")
				.requestMatchers("/admin/**").hasRole("ADMIN").requestMatchers("/**").permitAll().and().formLogin()
				.loginPage("/login").loginProcessingUrl("/userlogin").successHandler(CustomSuccessHandler).permitAll();

		return http.build();

	}
}
