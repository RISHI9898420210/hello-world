package com.org.kpit.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.org.kpit.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);
	
	public static String EMPLOYEE_INSERT = "Insert INTO employee_data (AGE,DEPT,NAME)"
			+ " values(?,?,?)";
	public static String EMPLOYEE_EDIT = "UPDATE employee_data set AGE=?, DEPT=?,NAME =? where ID=?";
	
	@Override
	public int saveEmployee(Employee employee) {
		logger.info("save employee details called ");
		logger.info("ID : " + employee.getId());
		logger.info("NAME : " + employee.getName());
		logger.info("DEPT : " + employee.getDept());
		logger.info("AGE : " + employee.getAge());
		
		int empID = jdbcTemplate.update(EMPLOYEE_INSERT, new Object[]
		        {employee.getAge(), employee.getDept(), employee.getName() });
		return empID;
	}

	@Override
	public int editEmployee(Employee employee) {
		logger.info("edit employee details called ");
		logger.info("ID : " + employee.getId());
		logger.info("NAME : " + employee.getName());
		logger.info("DEPT : " + employee.getDept());
		logger.info("AGE : " + employee.getAge());
		jdbcTemplate.update(EMPLOYEE_EDIT, new Object[]
		        {employee.getAge(), employee.getDept(), employee.getName(),employee.getId() });
		return employee.getId();
	}

	public List<Employee> fetchEmployeeDetails(Employee employee) {
		String SQL ="";
		if(employee.getId() != null)
		{
			SQL = "SELECT * FROM employee_data where id = " + employee.getId();
		}
		else
		{
			SQL = "SELECT * FROM employee_data";
		}
	    
		 
		List<Employee> employees = new ArrayList<Employee>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SQL);
		for (Map row : rows) {
			Employee empObj = new Employee();
			empObj.setId((Integer)row.get("ID"));
			empObj.setName((String)row.get("NAME"));
			empObj.setAge((Integer)row.get("AGE"));
			empObj.setDept((String)row.get("DEPT"));
			employees.add(empObj);
		}
	 
	    return employees;
	}

	@Override
	public int deleteEmployee(Employee employee) {
		String sql = "DELETE FROM employee_data WHERE id=?";
	    int deleteID = jdbcTemplate.update(sql, employee.getId());
	    
	    return deleteID;
	}
}
