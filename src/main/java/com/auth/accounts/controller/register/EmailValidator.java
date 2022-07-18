package com.auth.accounts.controller.register;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {

	@Override
	public boolean test(String email) {
		String regex = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
		Pattern pattern = Pattern.compile(regex);
		if (email == null)
			return false;
		return pattern.matcher(email).matches();
	}

}
