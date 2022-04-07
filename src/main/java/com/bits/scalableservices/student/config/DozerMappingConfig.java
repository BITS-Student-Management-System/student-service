package com.bits.scalableservices.student.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.github.dozermapper.spring.DozerBeanMapperFactoryBean;

@Component
public class DozerMappingConfig {

	@Bean
	public DozerBeanMapperFactoryBean dozerBeanMapperFactoryBean() throws IOException {
		DozerBeanMapperFactoryBean mapper = new DozerBeanMapperFactoryBean();
		return mapper;
	}

}
