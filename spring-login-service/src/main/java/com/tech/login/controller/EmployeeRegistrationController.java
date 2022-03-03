package com.tech.login.controller;

import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techiebeans.employee.service.dao.EmployeeServiceDAO;
import com.techiebeans.employee.service.model.EmployeeData;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeeRegistrationController {

	static Logger logger = Logger.getLogger(EmployeeRegistrationController.class.getName());

	@Autowired
	EmployeeServiceDAO employeeServiceDAO;

	private String employeeId = null;

	@ApiOperation(value = "Register New Employee.")
	@PostMapping("/registerEmployee")
	public ResponseEntity<String> insertDetails(@RequestBody @Valid EmployeeData request) {
		try {
			employeeServiceDAO.registerEmployeeDetails(request);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body("Successful");
	}

	@ApiOperation(value = "Get employee details.")
	@GetMapping("/getEmployeeDetails/{empId}")
	public ResponseEntity<Object> getDetails(@PathVariable(value = "empId") @NotNull String empId) {
		logger.info("Input employee Id" + employeeId);
		this.employeeId = empId.trim();
		EmployeeData employeeData = new EmployeeData();
		try {
			validation(employeeId);
			employeeData = employeeServiceDAO.getEmployeeDetails(employeeId);
			if (employeeData == null) {
				throw new NoSuchElementException("No Data found");
			}
			return ResponseEntity.status(HttpStatus.OK).body(employeeData);

		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	private void validation(String employeeId) {
		Pattern isNumeric = Pattern.compile("^[0-9]+");
		if (employeeId == null) {
			throw new IllegalArgumentException("Error!! Employee ID cannot be null");
		}
		if (!isNumeric.matcher(employeeId).matches()) {
			throw new IllegalArgumentException("Error!! Employee ID should be numeric only(0 - 9)");
		}
	}
}
