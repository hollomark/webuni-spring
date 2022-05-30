package hu.webuni.hr.mark.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.mark.dto.EmployeeDto;


@RestController
//@RequestMapping("/api/employees")
public class EmployeeDtoController {

	private Map<Long,EmployeeDto> employees = new HashMap<>();
	
	{
		employees.put(1L,new EmployeeDto(1,"Józsi","CTO",45500,LocalDateTime.parse("2021-01-13T17:09:42.411")));
		employees.put(2L,new EmployeeDto(2,"Mari","BTO",65500,LocalDateTime.parse("2011-01-13T17:09:42.411")));
	}
	
	 @GetMapping
	 public List<EmployeeDto> getAll() {
		 
		 return new ArrayList<>(employees.values());
	 }
	 
	 @GetMapping("/{id}")
	 public ResponseEntity<EmployeeDto> getById(@PathVariable long id) {
		 EmployeeDto employeeDto = employees.get(id);
		 if(employeeDto != null) {
			 return ResponseEntity.ok(employeeDto);
		 }
		 return ResponseEntity.notFound().build();
	 }
	 
	 
	 @PostMapping
	 public EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
		 employees.put(employeeDto.getId(), employeeDto);
		 
		 return employeeDto;
	 }
	 
	 @PutMapping("/{id}")
	 public ResponseEntity<EmployeeDto> modifyEmployee(@PathVariable long id, @RequestBody EmployeeDto employeeDto) {
		 employeeDto.setId(id);
		 if(!employees.containsKey(employeeDto.getId())) {
			 
			 return ResponseEntity.notFound().build();
		 }
		 employees.put(id, employeeDto);
		 
		 return ResponseEntity.ok(employeeDto);
	}
	 
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteAirport(@PathVariable long id) {
		String responseText = "Employee with id" + id + " removed from the local storage"; 
		if(employees.containsKey(id)) {
			employees.remove(id);
			
			return ResponseEntity.ok(responseText);
		}
		
		return ResponseEntity.notFound().build();
	}
	 
	@SuppressWarnings("unused")
	@GetMapping("/salary/{salary}")
	 public ResponseEntity<List<EmployeeDto>> getAllEmployeBySalary(@PathVariable int salary) {
		 List<EmployeeDto> overSalaryDto = new ArrayList<EmployeeDto>();
		 for (Map.Entry<Long,EmployeeDto> hashmap : employees.entrySet()) {
			 	if(hashmap.getValue().getSalary() > salary) {
			 		overSalaryDto.add(hashmap.getValue());
			 	}
		 }
		 if(overSalaryDto != null) {
			 
			 return ResponseEntity.ok(overSalaryDto);
		 } else {
			 
			 return ResponseEntity.notFound().build();
		 }
	 }
	 
	 
	 
}
