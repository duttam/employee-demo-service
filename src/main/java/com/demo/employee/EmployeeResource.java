package com.demo.employee;




import java.util.List;

import javax.validation.constraints.NotNull;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/employee")
@CrossOrigin
public class EmployeeResource{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getAllEmployees() {
		
		logger.info("Getting all Employees ");
		List<Employee> employees = employeeRepository.findAllEmployees();
		logger.info("Employees List size "+employees.size());
    	return new ResponseEntity<>(employees, HttpStatus.OK);
		
	}
	
	@RequestMapping( path="/{employeeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getEmployee(final @PathVariable("employeeId") @NotNull String employeeId) {
		
		logger.info("Getting all Employees ");
		Employee employee = employeeRepository.getEmployee(new Employee(employeeId));
		if(employee!=null)
			return new ResponseEntity<>(employee,HttpStatus.OK);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping( path="/{employeeId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateEmployee(final @PathVariable("employeeId") @NotNull Integer employeeId,@RequestBody Employee updateEmployee) {
		
		logger.info("Getting all Employees ");
		employeeRepository.updateEmployee(updateEmployee);
    	return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@RequestMapping( path="", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> saveEmployee(@RequestBody Employee newEmployee) {
		
		logger.info("Getting all Employees ");
		Employee employee = employeeRepository.addEmployee(newEmployee);
    	return new ResponseEntity<>(employee,HttpStatus.CREATED);
		
	}
	
	 
	 
	 
	 
	 @RequestMapping( path="/{employeeId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public ResponseEntity<?> deleteAvailability(final @PathVariable ("employeeId") @NotNull String employeeId) {
		 
		 logger.info("Employee ID : "+ employeeId);
		 employeeRepository.deleteEmployee(new Employee(employeeId));
		 return new ResponseEntity<>(HttpStatus.OK);
	 }
	 
	 
	 
}
	
	  
	 
	 
	
