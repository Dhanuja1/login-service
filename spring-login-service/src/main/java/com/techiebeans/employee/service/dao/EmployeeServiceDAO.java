package com.techiebeans.employee.service.dao;

import java.sql.Date;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.techiebeans.employee.service.model.EmployeeData;

@Repository
public class EmployeeServiceDAO {

	static Logger logger = Logger.getLogger(EmployeeServiceDAO.class.getName());

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void registerEmployeeDetails(EmployeeData e) {
		jdbcTemplate.update("INSERT INTO employee_details (employeeId,name,department,address,emailId) VALUES (?,?,?,?,?)", e.getEmployeeId(),
				e.getName(), e.getDepartment(), e.getAddress(), e.getEmailId());
	}

	public EmployeeData getEmployeeDetails(String employeeId) {
		return jdbcTemplate.queryForObject(
				"select e.employeeId, e.name, e.department, e.address, e.emailId from employee_details e where employeeId= ?",
				new Object[] { employeeId },
				(rs, rowNum) -> new EmployeeData(rs.getString("employeeId"), rs.getString("name"),
						rs.getString("department"), rs.getString("address"), rs.getString("emailId")));
	}

}
