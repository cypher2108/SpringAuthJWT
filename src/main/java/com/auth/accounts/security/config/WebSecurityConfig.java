/**
 * 
 */
package com.auth.accounts.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.auth.accounts.service.UserService;

import lombok.AllArgsConstructor;

/**
 * @author praful
 *
 */
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {
	
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.authenticationProvider(daoAuthenticationProvider());
		AuthenticationManager authenticationManager = auth.build();
		
		http.csrf().disable().authorizeRequests().antMatchers("/api/v*/accounts/registration/**").permitAll().anyRequest()
				.authenticated().and().authenticationManager(authenticationManager).formLogin();
		return http.build();
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		provider.setUserDetailsService(userService);
		return provider;
	}
}
