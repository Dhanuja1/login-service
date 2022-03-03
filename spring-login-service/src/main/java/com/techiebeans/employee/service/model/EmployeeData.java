package com.techiebeans.employee.service.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class EmployeeData {

	@NotEmpty
	private String employeeId;
	@NotEmpty
	@Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
	private String name;
	private String department;
	private String address;
	@Email
	private String emailId;

	public EmployeeData(String employeeId, String name, String department, String address, String emailId)
	{
		super();
		this.employeeId = employeeId;
		this.name = name;
		this.department = department;
		this.address = address;
		this.emailId = emailId;
	}
		
	public EmployeeData() {
		super();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
