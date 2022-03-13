package com.techiebean.spring.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "Id"),
           @UniqueConstraint(columnNames = "loginId")
       })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  @Email(message = "Invalid email Id")
  private String loginId;

  @NotBlank
  @Size(min = 1, max = 30, message = "Invalid name")
  private String name;
  
  private LocalDateTime lastSeen;

  @NotBlank
  @JsonIgnore
  @Size(min = 5, max = 60, message = "Password should have minimum 5 characters")
  private String password;
  
  @NotBlank
  @Size(max = 20, message = "Invalid mobile number")
  private String mobileNumber;
  
  private EPrimarySkill primarySkill;
  
  @JsonIgnore
  private int lockCounter;
  
  private String skillDescription;
  
  @JsonIgnore
  private ERole role;  

  public User() {
  }

	public User(String loginId, String name, String password, String mobileNumber, EPrimarySkill primarySkill,int lockCounter,String skillDescription,ERole role) {
		super();
		this.loginId = loginId;
		this.name = name;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.primarySkill = primarySkill;
		this.lockCounter = lockCounter;
		this.skillDescription = skillDescription;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public EPrimarySkill getPrimarySkill() {
		return primarySkill;
	}

	public void setPrimarySkill(EPrimarySkill primarySkill) {
		this.primarySkill = primarySkill;
	}

	public int getLockCounter() {
		return lockCounter;
	}

	public void setLockCounter(int lockCounter) {
		this.lockCounter = lockCounter;
	}

	public LocalDateTime getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(LocalDateTime localDateTime) {
		this.lastSeen = localDateTime;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}
}
