package com.techiebean.spring.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techiebean.spring.model.User;
import com.techiebean.spring.repository.UserRepository;
import com.techiebean.spring.request.LoginRequest;

@Service
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	PasswordEncoder encoder;

	public UserDetailsImpl userLogin(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		if (authentication.isAuthenticated())
			logger.debug("User authenticated");

		return (UserDetailsImpl) authentication.getPrincipal();
	}

	public void userProfileUpdate(Long id, String skillDescription) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User userToUpdate = user.get();
			userToUpdate.setSkillDescription(skillDescription);
			userRepository.save(userToUpdate);
		}
	}

	public void logout(Long id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User userToUpdate = user.get();
			userToUpdate.setLastSeen(LocalDateTime.now());
			userRepository.save(userToUpdate);
		}
	}

}
