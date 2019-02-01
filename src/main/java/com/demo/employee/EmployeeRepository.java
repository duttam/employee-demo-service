package com.demo.employee;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployeeRepository {
	List<Employee> employees;
	@PostConstruct
    public void init(){
		ObjectMapper mapper = new ObjectMapper();
		
		TypeReference<List<Employee>> mapType = new TypeReference<List<Employee>>() {};
		
		try {
			employees = mapper.readValue(new File("employees.json"), mapType);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Employee> findAllEmployees(){
		return employees;
	}
	
	public Employee addEmployee(Employee newEmployee) {
		int newId = new Random().nextInt(1000);
		newEmployee.setId(new Integer(newId).toString());
		employees.add(newEmployee);
		
		return newEmployee;
	}
	
	public void deleteEmployee(Employee deleteEmployee) {
		employees = employees.stream().filter((Employee employee) -> {
			return !employee.equals(deleteEmployee);
		}).collect(Collectors.toList());
	}
	public void updateEmployee(Employee updateEmployee) {
		for (Employee employee : employees) {
			if(employee.equals(updateEmployee)) {
				int index = employees.indexOf(employee);
				employees.set(index, updateEmployee);
			}
				
		}
	}

	public Employee getEmployee(Employee selectedEmployee) {
		for (Employee employee : employees) {
			if(employee.equals(selectedEmployee))
				return employee;
		}
		
		return null;
	}
}
