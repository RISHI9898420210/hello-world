package com.org.kpit.DAO;

import java.util.List;

import com.org.kpit.model.Employee;

public interface EmployeeDAO {

	public int saveEmployee(Employee employee);
	
	public int editEmployee(Employee employee);

	public List<Employee> fetchEmployeeDetails(Employee employee);
	
	public int deleteEmployee(Employee employee);
}
