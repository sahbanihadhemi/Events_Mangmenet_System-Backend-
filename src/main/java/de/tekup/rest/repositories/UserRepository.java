package de.tekup.rest.repositories;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;


import de.tekup.rest.models.User;
 
public interface UserRepository extends JpaRepository<User, Long> {
 User getUserByUsername(String username);
 Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	Optional<User> findByUsername(String username);

}