package com.techiebean.spring.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.techiebean.spring.model.EPrimarySkill;
 
public class UserRegistrationRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
 
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    
    @NotBlank
    @Size(min = 10, max = 20)
    private String mobileNumber;
    
    private EPrimarySkill primarySkill;
    
    @Size(min = 0, max = 250)
    private String skillDescription;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
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

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}
}
