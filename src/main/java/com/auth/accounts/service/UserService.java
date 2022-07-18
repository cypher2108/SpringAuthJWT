/**
 * 
 */
package com.auth.accounts.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.accounts.repository.UserRepository;
import com.auth.accounts.user.User;

import lombok.AllArgsConstructor;

/**
 * @author praful
 *
 */
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private static final String USER_NOT_FOUND_MSG = "User with username: %s not Found";
	private static final String USER_ALREADY_EXIST = "User with username: %s already Exist";
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
	}
	
	public String signupUser(User user) {
		boolean isUserPresent = userRepository.findByUsername(user.getUsername()).isPresent();
		if (isUserPresent) {
			throw new IllegalStateException(String.format(USER_ALREADY_EXIST, user.getUsername()));
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return "User Saved Successfully.";
	}

}
