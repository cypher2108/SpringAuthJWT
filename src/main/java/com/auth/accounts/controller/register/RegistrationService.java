/**
 * 
 */
package com.auth.accounts.controller.register;

import org.springframework.stereotype.Service;

import com.auth.accounts.service.UserService;
import com.auth.accounts.user.User;
import com.auth.accounts.user.UserRole;

import lombok.AllArgsConstructor;

/**
 * @author praful
 *
 */
@Service
@AllArgsConstructor
public class RegistrationService {

	private final EmailValidator emailValidator;
	private final UserService userService;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if (!isValidEmail) {
			throw new IllegalStateException("Email is not valid");
		}
		return userService.signupUser(new User(request.getName(), request.getUsername(), request.getEmail(),
				request.getPassword(), UserRole.USER));
	}
}
