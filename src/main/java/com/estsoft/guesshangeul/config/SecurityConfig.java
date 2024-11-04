package com.estsoft.guesshangeul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// 임시로 Spring Security 모든 요청을 허용하는 설정
	// 실제 Spring Security 설정 적용 시 삭제
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(
				custom -> custom.anyRequest().permitAll()
			)
			.csrf(custom -> custom.disable())
			.build();
	}
}
