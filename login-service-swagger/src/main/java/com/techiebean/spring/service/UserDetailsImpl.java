package com.techiebean.spring.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techiebean.spring.model.User;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;

  private LocalDateTime lastSeen;

  public UserDetailsImpl(Long id, String username, String email, String password,
		  LocalDateTime lastSeen) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.lastSeen = lastSeen;
  }

  public static UserDetailsImpl build(User user) {

    return new UserDetailsImpl(
        user.getId(), 
        user.getName(), 
        user.getLoginId(),
        user.getPassword(), 
        user.getLastSeen());
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
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
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public LocalDateTime getLastLogin() {
	return lastSeen;
}

public void setLastLogin(LocalDateTime lastSeen) {
	this.lastSeen = lastSeen;
}

@Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	return new ArrayList<>();
}
}
