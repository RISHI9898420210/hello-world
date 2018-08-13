package com.org.kpit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.org.kpit.DAO.EmployeeDAO;
import com.org.kpit.model.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeDAO employeeDAO;
	
	@Override
	public Employee saveEmployeeDetails(Employee employee) {
		int empID = employeeDAO.saveEmployee(employee);
		employee.setId(empID);
		return employee;
	}

	@Override
	public Employee editEmployeeDetails(Employee employee) {
		int empID = employeeDAO.editEmployee(employee);
		return employee;
	}

	@Override
	public List<Employee> getEmployeeDetails(Employee employee) {
		return employeeDAO.fetchEmployeeDetails(employee);
	}

	@Override
	public int deleteEmployee(Employee employee) {
		return employeeDAO.deleteEmployee(employee);
	}

}
