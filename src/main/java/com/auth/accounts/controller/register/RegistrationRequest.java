/**
 * 
 */
package com.auth.accounts.controller.register;

import lombok.Data;

/**
 * @author praful
 *
 */
@Data
public class RegistrationRequest {
	
	private final String name;
	private final String username;
	private final String email;
	private final String password;
	
	
}
