package com.estsoft.guesshangeul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.estsoft.guesshangeul.exception.CustomAuthFailureHandler;
import com.estsoft.guesshangeul.exception.CustomAuthSuccessHandler;
import com.estsoft.guesshangeul.user.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final UsersRepository userRepository;

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
	public CustomAuthSuccessHandler customAuthSuccessHandler() {
		return new CustomAuthSuccessHandler(userRepository);
	}

	@Bean
	public CustomAuthFailureHandler customAuthFailureHandler() {
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
				.loginProcessingUrl("/api/login")
				.failureHandler(customAuthFailureHandler())
				.successHandler(customAuthSuccessHandler())
				.usernameParameter("email")
				.passwordParameter("password")
				.permitAll()
			)
			.logout(custom -> custom
				.logoutSuccessUrl("/login")
				.logoutUrl("/api/logout")
				.deleteCookies("SESSION", "JSESSIONID")
				.invalidateHttpSession(true)
				.permitAll()
			)
			.csrf(AbstractHttpConfigurer::disable)
			.build();
	}
}
