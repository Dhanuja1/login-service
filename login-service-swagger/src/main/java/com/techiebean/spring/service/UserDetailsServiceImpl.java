package com.techiebean.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techiebean.spring.exception.AccountLockException;
import com.techiebean.spring.model.ERole;
import com.techiebean.spring.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService,AuthenticationProvider {
	
	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
  @Autowired
  UserRepository userRepository;
  
  com.techiebean.spring.model.User user;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,AccountLockException {
	   user = userRepository.findByLoginId(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with userId: " + username));
	userRepository.save(user);
	if(user.getRole().equals(ERole.USER) && user.getLockCounter() == 3) {
		throw new AccountLockException(user.getLoginId());
	}
	return UserDetailsImpl.build(user);
  }
  
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	  @Autowired
	  PasswordEncoder encoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String providedUsername = authentication.getPrincipal().toString();
		UserDetails userDetails = userDetailsService.loadUserByUsername(providedUsername);

		String providedPassword = authentication.getCredentials().toString();
		String correctPassword = userDetails.getPassword();

		// Authenticate
		// If Passwords don't match throw and exception
		if (!encoder.matches(providedPassword, correctPassword)) {
	    	user.setLockCounter(user.getLockCounter()+1);
	    	userRepository.save(user);
	    	throw new RuntimeException("Incorrect Credentials");
		}

		return new UsernamePasswordAuthenticationToken(userDetails,
				authentication.getCredentials(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
