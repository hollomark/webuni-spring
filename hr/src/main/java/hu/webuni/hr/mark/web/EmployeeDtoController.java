package hu.webuni.hr.mark.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.mapper.EmployeeMapper;
import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.service.EmployeeService;
import hu.webuni.hr.mark.service.SalaryService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeDtoController {

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeService employeeService;
	
	
	 @GetMapping
	 public Collection<EmployeeDto> getAll() {
		 
//		 return new ArrayList<>(employees.values());
	 
		 return employeeMapper.collectionEmployeeDtoListToEmployeeList(employeeService.getAllEmployee());
		 
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		 
		  EmployeeDto employeeDto = employeeMapper.employeeToDto(employeeService.getEmployee(id));
		 
		 if(employeeDto != null) {
			 return ResponseEntity.ok(employeeDto);
		 }
		 return ResponseEntity.notFound().build();
	 }
	 
	 
	 @PostMapping
	 public EmployeeDto createEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
		 EmployeeDto dto = employeeMapper.employeeToDto(employeeService.saveEmployee(employeeMapper.dtoToEmployee(employeeDto)));
		  
		 return dto;
		  
	 }
	 
	 @PutMapping("/{id}")
	 public EmployeeDto modifyEmployee(@PathVariable long id, @RequestBody @Valid EmployeeDto employeeDto) {
		 
		 employeeDto.setId(id);
		 EmployeeDto dto = employeeMapper.employeeToDto(employeeService.saveEmployee(employeeMapper.dtoToEmployee(employeeDto)));
		 
		 if(!dto.equals(null)) {
			 
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		 }
		 		 
		 return dto;
	}
	 
	 
	 @DeleteMapping("/{id}")
	 public void deleteAirport(@PathVariable long id) {
		
		 employeeService.removeEmployee(id);
		
	}
	 
	@SuppressWarnings("unused")
	@GetMapping("/salary/{salary}")
	 public ResponseEntity<Collection<EmployeeDto>> getAllEmployeBySalary(@PathVariable int salary) {
		 
		
	 	Collection<EmployeeDto> employees = employeeMapper.collectionEmployeeDtoListToEmployeeList(employeeService.salaryFilter(salary));
		
		 if(employees != null) {
			 
			 return ResponseEntity.ok(employees);
		 } else {
			 
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		 }
	 }
	 
	 
	 
}
