package com.org.kpit.v1.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.org.kpit.model.Employee;
import com.org.kpit.service.EmployeeService;
import com.org.kpit.util.GsonUtil;

@RestController
public class EmployeeController {
	
	@Autowired
    private EmployeeService employeeService;
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	/*@RequestMapping(value = "/addEditEmployeeDetails", method = RequestMethod.POST)
	public JsonObject addEditEmployeeDetails(@RequestParam("addEmployee") String addEmployee,@ModelAttribute("employeeForm") Employee employee) {
		logger.info("called addEmployee details" + employee.getDept());
		
		Gson gson = new Gson();
		Employee empObj = gson.fromJson(addEmployee, Employee.class);
		logger.info("Name : " + empObj.getName());
		logger.info("ID : " + empObj.getId());
		if(empObj.getId() != null)
		{
			empObj = employeeService.editEmployeeDetails(empObj);
		}
		else
		{
			empObj = employeeService.saveEmployeeDetails(empObj);
		}
		
		JsonObject newJson = new JsonObject();
		JsonElement jElement = GsonUtil.getInstance().toJsonTree(empObj);
	    newJson.add("DATA", jElement);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		return newJson;
	}
	*/
	@RequestMapping(value = "/addEditEmployeeDetails", method = RequestMethod.POST)
	public ModelAndView addEditEmployeeDetails(@ModelAttribute("employeeForm") Employee empObj,ModelAndView model) {
		logger.info("called addEmployee details" + empObj.getDept());
		
		Gson gson = new Gson();
		//Employee empObj = gson.fromJson(addEmployee, Employee.class);
		logger.info("Name : " + empObj.getName());
		logger.info("ID : " + empObj.getId());
		if(empObj.getId() != null)
		{
			empObj = employeeService.editEmployeeDetails(empObj);
		}
		else
		{
			empObj = employeeService.saveEmployeeDetails(empObj);
		}
		
		JsonObject newJson = new JsonObject();
		JsonElement jElement = GsonUtil.getInstance().toJsonTree(empObj);
	    newJson.add("DATA", jElement);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
	    model.addObject("listEmployee",employeeService.getEmployeeDetails(new Employee()));
	    model.setViewName("home");
		return model;
	}
	
	@RequestMapping(value = "/getEmployeeDetails", method = RequestMethod.GET)
	public JsonObject getEmployeeDetails(@RequestParam("getEmployee") String getEmployee) {
		logger.info("called getEmployeeDetails");
		
		Gson gson = new Gson();
		Employee empObj = gson.fromJson(getEmployee, Employee.class);
		logger.info("ID : " + empObj.getId());
		
		List<Employee> employeeObj = employeeService.getEmployeeDetails(empObj);
		
		JsonObject newJson = new JsonObject();
		JsonElement jElement = GsonUtil.getInstance().toJsonTree(employeeObj);
	    newJson.add("DATA", jElement);
	    logger.info("called getEmployee details");
		return newJson;
	}
	
	@RequestMapping(value = "/deleteEmployeeDetails", method = RequestMethod.GET)
	public ModelAndView deleteEmployeeDetails(@RequestParam("deleteEmployee") int employeeID,ModelAndView model) {
		logger.info("called deleteEmployee");
		
	/*	Gson gson = new Gson();
		Employee empObj = gson.fromJson(getEmployee, Employee.class);*/
		Employee empObj = new Employee();
		empObj.setId(employeeID);
		logger.info("ID : " + empObj.getId());
		
		int deleteID = employeeService.deleteEmployee(empObj);
		
		
		JsonObject newJson = new JsonObject();
		JsonElement jElement = GsonUtil.getInstance().toJsonTree(deleteID);
	    newJson.add("DATA", jElement);
	    logger.info("called deleteEmployee details");
	    Employee employeeForm = new Employee();    
        model.addObject("employeeForm", employeeForm);
	    model.addObject("listEmployee",employeeService.getEmployeeDetails(new Employee()));
	    model.setViewName("home");
		return model;
	}
	
	
}
