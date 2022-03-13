package com.techiebean.spring.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techiebean.spring.repository.UserRepository;
import com.techiebean.spring.request.LoginRequest;
import com.techiebean.spring.response.MessageResponse;
import com.techiebean.spring.response.UserInfoResponse;
import com.techiebean.spring.service.UserDetailsImpl;
import com.techiebean.spring.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;

	@ApiOperation(value = "User Login.")
	@PostMapping("/user/login")
	public ResponseEntity<Object> userLogin(@Valid @RequestBody LoginRequest loginRequest) {
		try {
			UserDetailsImpl userDetails = userService.userLogin(loginRequest);
			return ResponseEntity.ok().body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(),
					userDetails.getEmail(), userDetails.getLastLogin()));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@ApiOperation(value = "Profile update.")
	@PutMapping("/user/profileUpdate/{id}/{description}")
	public ResponseEntity<Object> userProfileUpdate(@PathVariable @NotNull Long id, @PathVariable String description) {
			userService.userProfileUpdate(id, description);
			return ResponseEntity.ok().body(new MessageResponse("User profile updated"));
	}

	@ApiOperation(value = "User Logout.")
	@PostMapping(value = "/api/logout/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> logout(@NotNull @PathVariable Long id) {
			userService.logout(id);
			return ResponseEntity.ok().body(new MessageResponse("Successfully logged out"));
	}
}
