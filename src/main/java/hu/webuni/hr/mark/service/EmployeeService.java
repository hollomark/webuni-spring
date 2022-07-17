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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.model.Company;
import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.model.Position;
import hu.webuni.hr.mark.repository.EmployeeRepository;

import org.springframework.data.domain.Sort;

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
		return employeeRepository.findByPositions(position);
		
	}
	
	public List<Employee> likeEmployeNameStart(String name){
		return employeeRepository.findByNameStartingWithIgnoreCase(name);
		
	}
	
	public List<Employee> betweenEmployees(LocalDateTime from , LocalDateTime to){
		
		return employeeRepository.findByStartDateBetween(from,to);
		
	}
	
	public List<Employee> findEmployeeByExample1(Employee example) {
		
		
		long id = example.getId();
		Company company = example.getCompany();
		String name = example.getName();
		Position position = example.getPositions();
		double salary = example.getSalary();
		LocalDateTime startDatetime = example.getStartDate();


		
		return null;
		
	}
	
	public List<Employee> findEmployeeByExample(Employee example) {
		
		LocalDateTime enteranceDate = null;
		LocalDateTime defTime = LocalDateTime.of(0001, 01, 01, 00, 00, 00);
		
		Long id 		= example.getId();
		String name 	= example.getName();
		String position = example.getPositions().getName();
		double salary 		= example.getSalary();
		 
		if(example.getStartDate() != null) {
			enteranceDate = example.getStartDate();
		}
		String companyName = !example.getCompany().getCompanyName().isEmpty() ? example.getCompany().getCompanyName() : "";

		Specification<Employee> specification = Specification.where(null);
		
		if(id > 0 && id != null) {
			specification = specification.and(EmployeeSpecification.hasId(id));
		}
		
		if(StringUtils.hasText(name) && !name.isEmpty()) {
			specification = specification.and(EmployeeSpecification.nameStartingWithIgnoreCase(name));
		}
		
		if(StringUtils.hasText(position) && !position.isEmpty()) {
			specification = specification.and(EmployeeSpecification.hasPosition(position));
		}
		
		if(salary > 0) {
			specification = specification.and(EmployeeSpecification.hasSalary(salary));
		}
		
		if(enteranceDate.isAfter(defTime)) {
			specification = specification.and(EmployeeSpecification.startWorkingDate(enteranceDate));
		}
		
		if(StringUtils.hasText(companyName) || !companyName.isEmpty()) {
			specification = specification.and(EmployeeSpecification.hasCompany(companyName));
		}
		return employeeRepository.findAll(specification,Sort.by("id"));
		//return employeeRepository.findAll(specification, Sort.by("id"));		
	}

	
}
