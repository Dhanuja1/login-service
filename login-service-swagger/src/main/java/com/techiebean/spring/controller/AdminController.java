package com.techiebean.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techiebean.spring.model.ERole;
import com.techiebean.spring.model.User;
import com.techiebean.spring.repository.UserRepository;
import com.techiebean.spring.request.AdminRegistrationRequest;
import com.techiebean.spring.request.LoginRequest;
import com.techiebean.spring.request.UserRegistrationRequest;
import com.techiebean.spring.response.MessageResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class AdminController {

	static Logger logger = Logger.getLogger(AdminController.class.getName());

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@ApiOperation(value = "Register Admin.")
	@PostMapping(value = "/createAdmin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAdminUser(@Valid @RequestBody AdminRegistrationRequest registrationRequest) {

		User adminUser = new User(registrationRequest.getEmail(), registrationRequest.getUsername(),
				encoder.encode(registrationRequest.getPassword()), registrationRequest.getMobileNumber(), null, 0, null,
				ERole.ADMIN);

		//userRepository.save(adminUser);

		return ResponseEntity.ok(userRepository.save(adminUser));
	}

	/*@ApiOperation(value = "Admin Login.")
	@PostMapping(value = "/admin/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> adminLogin(@Valid @RequestBody LoginRequest loginRequest) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			if (authentication.isAuthenticated())
				logger.debug("User is Authenticated");
			return ResponseEntity.ok().body(new MessageResponse("\"Welcome to Admin Portal!\""));
	}*/
	
	@ApiOperation(value = "Admin Login.")
	@PostMapping("/admin/login")
	public ResponseEntity<Object> adminLogin(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			if (authentication.isAuthenticated())
				logger.debug("User is Authenticated");
			return ResponseEntity.ok().body("Welcome to Admin Portal!");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@ApiOperation(value = "Register User.")
	@PostMapping(value = "/admin/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
		User user = new User(registrationRequest.getEmail(), registrationRequest.getUsername(),
				encoder.encode(registrationRequest.getMobileNumber()), registrationRequest.getMobileNumber(),
				registrationRequest.getPrimarySkill(), 0, null, ERole.USER);
		userRepository.save(user);

		return ResponseEntity.ok().body(new MessageResponse("\"User created\""));
	}

	@ApiOperation(value = "Get User List.")
	@GetMapping("/admin/userList")
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = new ArrayList<>();
		users = userRepository.findAll().stream().filter(a -> a.getRole().equals(ERole.USER))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(users);
	}

}
