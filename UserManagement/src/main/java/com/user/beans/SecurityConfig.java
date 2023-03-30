package com.user.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private static final String[] WHITE_LIST_URLS = { "/","/welcome", "/getAllUsers", "/getUsers", "/getUser", "/deleteUser/**",
			"/addUser", "/updateUser", "/login/logUserIn",
			"/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeHttpRequests().requestMatchers(WHITE_LIST_URLS).permitAll().and().authorizeHttpRequests().requestMatchers("/**").authenticated();
		return httpSecurity.build();
	}

}
