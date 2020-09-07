package com.toystore.ecomm.tenants.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@Configuration
public class JsonNullIncludeExcludeConfig {
	/*
	 * @Bean public ObjectMapper objectMapper() { ObjectMapper mapper = new
	 * ObjectMapper();
	 * mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); // NON_EMPTY
	 * for '' or NULL value return mapper; }
	 */
	
	
    /*@Order(Ordered.HIGHEST_PRECEDENCE)
    public Jackson2ObjectMapperBuilderCustomizer customJackson() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                builder.serializationInclusion(JsonInclude.Include.NON_NULL);
                builder.failOnUnknownProperties(false);
            }
        };
    }*/

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		return mapper;
	}
}
