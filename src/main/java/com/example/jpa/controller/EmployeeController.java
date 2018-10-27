package com.example.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class EmployeeController<EmployeeRepository> {
	@Autowired
	private EmployeeRepository empl;
	
	@GetMapping("/employee")
	public List<Employee>getEmplinfo(){
		return empl.findAll();
	}

}
