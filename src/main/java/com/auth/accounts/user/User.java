/**
 * 
 */
package com.auth.accounts.user;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * @author praful
 *
 */
@Data
@Entity
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6900544632764776212L;

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private long id;
	private String name;
	private String username;
	private String email;
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	private Boolean locked = false;
	private Boolean enabled = true;

	public User() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public User(String name, String username, String email, String password, UserRole userRole) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}

}
