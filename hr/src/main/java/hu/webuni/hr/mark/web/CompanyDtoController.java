package hu.webuni.hr.mark.web;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.mapper.CompanyMapper;
import hu.webuni.hr.mark.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyDtoController {

	@Autowired
	CompanyService companyService; 
	
	@Autowired
	CompanyMapper companyMapper;
	
	
	@GetMapping
	 public Collection<CompanyDto> getAll(@RequestParam(defaultValue = "false") String full) {
		Collection<CompanyDto> companies;
		if(!full.equals("true")) {
			
			companies = companyMapper.companiesToCompanyDtoCollection(companyService.getAllCompaniesWithoutEmployees());

			return companies;
		}
		
		 return companies = companyMapper.companiesToCompanyDtoCollection(companyService.getAllCompaniesWithoutEmployees());
	 }
	
	@GetMapping("/{id}")
	 public ResponseEntity<CompanyDto> getCompanyById(@RequestParam long id) {
		CompanyDto company = companyMapper.CompanyToCompanyDto(companyService.getCompany(id));
		
		 if(company != null) {
			 return ResponseEntity.ok(company);
		 }
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	 }
	
	 @PostMapping
	 public CompanyDto createCompany(@RequestBody CompanyDto company) {
		 
		 CompanyDto companyDto = companyMapper.CompanyToCompanyDto(companyService.createCompany(companyMapper.companyDtoToCompany(company)));
		 
		 return companyDto;
	 }
	 
	 @PutMapping("{id}")
	 public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		 
		 
		 
		 CompanyDto company = companyMapper.CompanyToCompanyDto(companyService.findByIdOrRetErr(id));
		 
		 companyMapper.CompanyToCompanyDto(companyService.modifyCompany(companyMapper.companyDtoToCompany(company), id));

		 return ResponseEntity.ok(company);
	}
	
	 
	 @DeleteMapping("/{id}")
	 public void deleteCompany(@PathVariable long id) {

		companyService.deleteCompany(id);
	}
	
//	 @PostMapping("/{id}/emplyoees") 
//	 public ResponseEntity<CompanyDto> addEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
//		 CompanyDto company = companies.get(id);
//		 if(company == null)
//			{
//				return ResponseEntity.notFound().build();
//			}
//		company.getCompanyEmployees().add(employeeDto);
//			
//		return ResponseEntity.ok(companies.get(id));		
//	 }
//
//	 
//	 @DeleteMapping("/{id}/employeeEdit/{employeeId}")
//	 public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long id,@PathVariable long employeeId) {
//		
//		 CompanyDto company = findByIdOrRetErr(id);
//		 company.getCompanyEmployees().removeIf(e -> (e.getId() == employeeId));
//		
//		 return ResponseEntity.ok(companies.get(id));		
//	}
//	 
//	 
//	 
//	 @PutMapping("/{id}/employees")
//	 public ResponseEntity<CompanyDto> updateEmployees(@RequestBody List<EmployeeDto> employeeDto, @PathVariable Long id) {
//		
//		 CompanyDto company = findByIdOrRetErr(id);
//		 
//		 company.setCompanyEmployees(employeeDto);
//		 
//		 return ResponseEntity.ok(companies.get(id));				
//	}
}
