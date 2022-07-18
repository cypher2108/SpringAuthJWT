/**
 * 
 */
package com.auth.accounts.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.accounts.controller.register.RegistrationRequest;
import com.auth.accounts.controller.register.RegistrationService;

import lombok.AllArgsConstructor;

/**
 * @author praful
 *
 */
@RestController
@RequestMapping(path = "api/v1/accounts")
@AllArgsConstructor
public class UserController {
	
	private RegistrationService registrationService;
	
	@PostMapping("/registration")	
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}
	
}
