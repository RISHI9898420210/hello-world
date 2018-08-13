package com.org.kpit.service;

import java.util.List;

import com.org.kpit.model.Employee;

public interface EmployeeService {

	 public Employee saveEmployeeDetails(Employee employee);
	 
	 public Employee editEmployeeDetails(Employee employee);
	 
	 public List<Employee> getEmployeeDetails(Employee employee);

	 public int deleteEmployee(Employee employee);
}
