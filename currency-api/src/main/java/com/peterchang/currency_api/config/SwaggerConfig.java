package com.peterchang.currency_api.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "CurrencyAPI", version = "v1.0"))
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi openapiGroup() {
		return GroupedOpenApi.builder().group("CurrencyAPI").pathsToMatch("/currencyInfo/**").build();
	}

}
