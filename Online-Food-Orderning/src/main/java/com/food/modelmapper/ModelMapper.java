package com.food.modelmapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapper {

	@Bean
	public org.modelmapper.ModelMapper getModelMapper()
	{
		org.modelmapper.ModelMapper modelMapper = new org.modelmapper.ModelMapper();
		return modelMapper;
	}
}
