package com.techiebean.spring.response;

import java.time.LocalDateTime;

public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private LocalDateTime lastSeen;

	public UserInfoResponse(Long id, String username, String email, LocalDateTime lastSeen) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.lastSeen = lastSeen;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}
	
	public LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now();
	}

	public LocalDateTime getLastSeen() {
		return lastSeen;
	}
}
