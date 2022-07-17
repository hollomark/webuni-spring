package hu.webuni.hr.mark.model;

import javax.persistence.Entity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;


import hu.webuni.hr.mark.service.DefaultEmployeeService;
import hu.webuni.hr.mark.service.EmployeeService;
import hu.webuni.hr.mark.service.SmartEmployeeService;

@Configuration
@Profile("smart")
public class SmartEmployee {

	
	@Bean
	public EmployeeService employeeService() {
		return new DefaultEmployeeService();
	}
}
