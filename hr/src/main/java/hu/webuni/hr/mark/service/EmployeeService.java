package hu.webuni.hr.mark.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.repository.EmployeeRepository;

@Service
public abstract class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	

	public abstract int getPayRaisePercent(Employee employee);
	
	
	public Collection<Employee> getAllEmployee() {
		
		return employeeRepository.findAll();
	}
	
	
	public Optional<Employee> getEmployee(long id) {
		
		return employeeRepository.findById(id);
		
	}
	
//	public Collection<Employee> salaryFilter(int salary) {
//		
//		Collection<Employee> employeeFromCollection = employees.entrySet().stream()
//															.filter(e-> e.getValue().getSalary() > salary ).collect(
//																	Collectors.toMap(a -> a.getKey(), a -> a.getValue())).values();
//		
//		
//		
//		
//		return employeeFromCollection;
//	}
//	
	@Transactional
	public Employee saveEmployee(Employee employee) {
		
		employeeRepository.save(employee);
		
		 
		 return employee;
	}
	
	@Transactional
	public void removeEmployee(long id) {
		employeeRepository.deleteById(id);
		
	}
	
	
	public List<Employee> getEmployeesByPos(String position){
		return employeeRepository.findByPosition(position);
		
	}
	
	public List<Employee> likeEmployeNameStart(String name){
		return employeeRepository.findByNameStartingWithIgnoreCase(name);
		
	}
	
	public List<Employee> betweenEmployees(LocalDateTime from , LocalDateTime to){
		
		return employeeRepository.findByStartDateBetween(from,to);
		
	}
	
	
}
