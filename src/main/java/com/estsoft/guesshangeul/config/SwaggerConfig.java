package com.estsoft.guesshangeul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	private static final String API_NAME = "Study API";
	private static final String API_VERSION = "1.0.0";
	private static final String API_DESCRIPTION = "Study API 명세서";
	@Bean
	public Docket restAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.hobbybee"))
			.paths(PathSelectors.any())
			.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("나랏말싸미")
			.version("1.0.0")
			.description("오르미 6기 1차 팀별과제")
			.build();
	}
}
