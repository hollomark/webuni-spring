package hu.webuni.hr.mark.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.dto.EmployeeDto;

@RestController
@RequestMapping("/api/companies")
public class CompanyDtoController {

	

	private Map<Long, CompanyDto> companies = new HashMap<>();
	
	
	@GetMapping
	 public Collection<CompanyDto> getAll(@RequestParam(defaultValue = "false") String full) {
		
		if(!full.equals("true")) {
			
			return companies.values()
					.stream()
					.map( c -> new CompanyDto(c.getId(), c.getCompanyNumber(), c.getCompanyName(), c.getCompanyAddress(),Collections.emptyList())).collect(Collectors.toList());
			
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
	 
	 @PutMapping("{id}")
	 public ResponseEntity<Map<Long, CompanyDto>> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		 CompanyDto company = findByIdOrRetErr(id);
		 companies.put(id, companyDto);
		 
		 return ResponseEntity.ok(companies);
	}
	
	 
	 @DeleteMapping("/{id}")
	 public ResponseEntity<String> deleteCompany(@PathVariable long id) {

		CompanyDto company = findByIdOrRetErr(id);
		
		companies.remove(id);
		return ResponseEntity.notFound().build();
	}
	
	 @PostMapping("/{id}/emplyoees") 
	 public ResponseEntity<CompanyDto> addEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
		 CompanyDto company = companies.get(id);
		 if(company == null)
			{
				return ResponseEntity.notFound().build();
			}
		company.getCompanyEmployees().add(employeeDto);
			
		return ResponseEntity.ok(companies.get(id));		
	 }

	 
	 @DeleteMapping("/{id}/employeeEdit/{employeeId}")
	 public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long id,@PathVariable long employeeId) {
		
		 CompanyDto company = findByIdOrRetErr(id);
		 company.getCompanyEmployees().removeIf(e -> (e.getId() == employeeId));
		
		 return ResponseEntity.ok(companies.get(id));		
	}
	 
	 private CompanyDto findByIdOrRetErr(long id) {
		 CompanyDto company = companies.get(id);
		 if(company == null)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		 
		return company;
	 }
	 
	 @PutMapping("/{id}/employees")
	 public ResponseEntity<CompanyDto> updateEmployees(@RequestBody List<EmployeeDto> employeeDto, @PathVariable Long id) {
		
		 CompanyDto company = findByIdOrRetErr(id);
		 
		 company.setCompanyEmployees(employeeDto);
		 
		 return ResponseEntity.ok(companies.get(id));				
	}
}
