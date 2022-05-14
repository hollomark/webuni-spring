package hu.webuni.hr.mark.service;

import org.springframework.stereotype.Service;

import hu.webuni.hr.mark.model.Employee;

@Service
public class SalaryService {
	
	private EmployeeService employeeService;

	public SalaryService(EmployeeService employeeService) {

		this.employeeService = employeeService;
	}
	
	public void setFinalSalary(Employee employee) {
		employee.setSalary((int)employee.getSalary() / 100.0 * (100 + employeeService.getPayRaisePercent(employee))); 
	}
	
	

}
