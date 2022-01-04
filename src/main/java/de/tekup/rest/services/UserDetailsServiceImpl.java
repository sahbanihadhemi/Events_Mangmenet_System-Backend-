package de.tekup.rest.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import de.tekup.rest.repositories.UserRepository;
import de.tekup.rest.models.User;

 @Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return MyUserDetails.build(user);
	}
 
}