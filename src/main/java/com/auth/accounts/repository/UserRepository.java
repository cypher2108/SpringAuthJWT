/**
 * 
 */
package com.auth.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.auth.accounts.user.User;

/**
 * @author praful
 *
 */

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);	
}
