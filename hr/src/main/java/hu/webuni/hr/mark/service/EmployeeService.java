package hu.webuni.hr.mark.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.model.Employee;

@Service
public abstract class EmployeeService {

	private Map<Long,Employee> employees = new HashMap<>();
	
	{
		employees.put(1L,new Employee(1,"Józsi","CTO",45500,LocalDateTime.parse("2021-01-13T17:09:42.411")));
		employees.put(2L,new Employee(2,"Mari","BTO",65500,LocalDateTime.parse("2011-01-13T17:09:42.411")));
	}
	
	public abstract int getPayRaisePercent(Employee employee);
	
	
	public Collection<Employee> getAllEmployee() {
		
		return employees.values();
	}
	
	
	public Employee getEmployee(long id) {
		if(employees.containsKey(id) ) {
			return employees.get(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	public Collection<Employee> salaryFilter(int salary) {
		
		Collection<Employee> employeeFromCollection = employees.entrySet().stream()
															.filter(e-> e.getValue().getSalary() > salary ).collect(
																	Collectors.toMap(a -> a.getKey(), a -> a.getValue())).values();
		
		return employeeFromCollection;
	}
	
	public Employee saveEmployee(Employee employee) {
		
		 employees.put((long) employee.getId(), employee);
		 
		 return employee;
	}
	
	
	public void removeEmployee(long id) {
		employees.remove(id);
		
	}
	
	
	
	
	
}
