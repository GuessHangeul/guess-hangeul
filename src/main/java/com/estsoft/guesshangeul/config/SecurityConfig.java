package com.estsoft.guesshangeul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.estsoft.guesshangeul.user.controller.CustomAuthFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return webSecurity -> webSecurity.ignoring()
			.requestMatchers("/static/**", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationFailureHandler customAuthFailureHandler() {
		return new CustomAuthFailureHandler();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(
				custom -> custom
					.requestMatchers("/login", "/signup", "/", "/api/*").permitAll()
					// .requestMatchers("").hasRole("NOBI")
					// .requestMatchers("").hasRole("PYEONGMIN")
					// .requestMatchers("").hasRole("YANGBAN")
					// .requestMatchers("/api/quizBoard").hasRole("JIPHYEONJEON")
					// .requestMatchers("").hasRole("KINGSEJONG")

					.anyRequest()
					// .authenticated()
					.permitAll()
			)
			.formLogin(custom -> custom
				.loginPage("/login")
				.defaultSuccessUrl("/", true)
				.failureHandler(customAuthFailureHandler())
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll()
			)
			.logout(custom -> custom
				.logoutSuccessUrl("/login")
				.logoutUrl("/logout")
				.deleteCookies("SESSION", "JSESSIONID")
				.invalidateHttpSession(true)
				.permitAll()
			)
			.csrf(AbstractHttpConfigurer::disable)
			.build();
	}
}