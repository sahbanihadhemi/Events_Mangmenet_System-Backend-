package de.tekup.rest.Cont;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.http.ResponseEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.tekup.rest.config.JwtTokenUtil;
import de.tekup.rest.doamin.JwtResponse;
import de.tekup.rest.doamin.Message;
import de.tekup.rest.models.Email;
import de.tekup.rest.models.User;
import de.tekup.rest.repositories.UserRepository;
import de.tekup.rest.request.LoginRequest;
import de.tekup.rest.request.RegisterRequest;
import de.tekup.rest.services.MyUserDetails;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin(origins ="http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired 	UserRepository repository;
	@Autowired 	AuthenticationManager authenticationManager;

	@Autowired	UserRepository userRepository;

	@Autowired	PasswordEncoder encoder;

	@Autowired	JwtTokenUtil jwtUtils;
	
	@RequestMapping(value = "/users/login", method= RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
		System.out.println("aaaa");
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword())
			
				);
		  System.out.println("bbbbb");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();		
		
        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());

		return ResponseEntity.ok(new JwtResponse(jwt, 
				 userDetails.getId(), 
				 userDetails.getUsername(), 
				 userDetails.getEmail(), 
				 userDetails.getRole()));
}



	@PostMapping("/users/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getRole()
										 );
		userRepository.save(user);

		return ResponseEntity.ok(new Message("User registered successfully!"));
	}	  
	
	
}
