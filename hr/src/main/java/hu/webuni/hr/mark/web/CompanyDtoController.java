package hu.webuni.hr.mark.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyDtoController {

	

	private List<EmployeeDto> employeesTelekom = new ArrayList<>();
	
	{
		employeesTelekom.add(new EmployeeDto(1,"Józsi","CTO",45500,LocalDateTime.parse("2021-01-13T17:09:42.411")));
		employeesTelekom.add(new EmployeeDto(2,"Mari","BTO",65500,LocalDateTime.parse("2011-01-13T17:09:42.411")));
		
	}
	
	private List<EmployeeDto> employeesDPD = new ArrayList<>();
	
	{
		employeesDPD.add(new EmployeeDto(1,"Elek","CTO",45500,LocalDateTime.parse("2020-01-13T17:09:42.411")));
		employeesDPD.add(new EmployeeDto(2,"Imre","BTO",75500,LocalDateTime.parse("2016-01-13T17:09:42.411")));
		
	}
	
	private Map<Long,CompanyDto> companies = new HashMap<>();
	
	
	{
		companies.put(1L,new CompanyDto(1,"132142N33","Telekom","1111 Budapest Andrassy u",employeesTelekom));
		companies.put(2L,new CompanyDto(2,"13eq42N33","DPD","1148 Budapest Ilka u",employeesDPD));
	}
	
	
	@GetMapping("{full}")
	 public List<CompanyDto> getAll(@RequestParam(defaultValue = "false") Boolean full) {
		
		if(!full.equals("true")) {
			 for (Entry<Long, CompanyDto> entry : companies.entrySet()) {
				 entry.getValue().setCompanyEmployees(Collections.emptyList());
		          
		    }
		}
		
		 return new ArrayList<>(companies.values());
	 }
	
	@GetMapping("/{id}")
	 public ResponseEntity<CompanyDto> getCompanyById(@RequestParam long id) {
		CompanyDto company = companies.get(id);
		 if(company != null) {
			 return ResponseEntity.ok(company);
		 }
		 return ResponseEntity.notFound().build();
	 }
	
	 @PostMapping
	 public CompanyDto createCompany(@RequestBody CompanyDto company) {
		 companies.put((long) company.getId(), company);
		 
		 return company;
	 }
	 
	 @PutMapping("/edit/{id}")
	 public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		 if(!companies.containsKey(companyDto.getId())) {
			 
			 return ResponseEntity.notFound().build();
		 }
		 companyDto.setId((int) id);
		 companies.put(id, companyDto);
		 
		 return ResponseEntity.ok(companyDto);
	}
	
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteCompany(@PathVariable long id) {
		String responseText = "Company with id" + id + " removed from the local storage"; 
		if(companies.containsKey(id)) {
			companies.remove(id);
			
			return ResponseEntity.ok(responseText);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	 @PostMapping("/addEmployee/{id}") 
	 public ResponseEntity<CompanyDto> addNewEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
		 
		 if(!companies.containsKey(id))
			{
				return ResponseEntity.notFound().build();
			}
		 List<EmployeeDto> currentEmployees = companies.get(id).getCompanyEmployees();
		currentEmployees.add(employeeDto);
		companies.get(id).setCompanyEmployees(currentEmployees);
			
		return ResponseEntity.ok(companies.get(id));		
	 }

	 
	 @DeleteMapping("/employeeEdit/{id}/{employeeId}")
	 public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long id,@PathVariable long employeeId) {
		String responseText = "Employee with id" + employeeId + " removed from the companyNumber"; 
		
		if(!companies.containsKey(id))
		{
			return ResponseEntity.notFound().build();
		}
		
		companies.get(id).getCompanyEmployees().removeIf(e -> (e.getId() == employeeId));
		
		return ResponseEntity.ok(companies.get(id));		
	}
	 
	 
	 public ResponseEntity<CompanyDto> updateEmployees(@RequestBody List<EmployeeDto> employeeDto, @PathVariable Long id) {
		
		 if(!companies.containsKey(id))
			{
				return ResponseEntity.notFound().build();
			}
		 
		 companies.get(id).setCompanyEmployees(employeeDto);
		 
		 return ResponseEntity.ok(companies.get(id));				
	}
}
