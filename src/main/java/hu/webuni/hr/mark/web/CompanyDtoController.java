package hu.webuni.hr.mark.web;

import java.util.Collection;
import java.util.List;

import hu.webuni.hr.mark.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import hu.webuni.hr.mark.mapper.CompanyMapper;
import hu.webuni.hr.mark.mapper.EmployeeMapper;
import hu.webuni.hr.mark.model.AverageSalaryByPosition;
import hu.webuni.hr.mark.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyDtoController {

	@Autowired
	CompanyService companyService; 
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
	CompanyRepository companyRepository;

	
	
	
	@GetMapping
	 public Collection<CompanyDto> getAll(@RequestParam(defaultValue = "false") String full) {
		Collection<CompanyDto> companies = null;

		if(!full.equals("true")) {
			companies = companyMapper.companiesToCompanyDtoCollection(companyService.getAllCompanies());
			return companies;

		}
		companies = companyMapper.companiesToCompanyDtoCollection(companyRepository.findAllWithEmployees());
		 return companies;
	 }
	
	
	
	
	@GetMapping("/getid/{id}")
	 public CompanyDto getCompanyById(@PathVariable long id) {
		CompanyDto companies  = companyMapper.CompanyToCompanyDto(companyRepository.findOneWithEmployees(id));
		 return companies;
		
	 }
	 
	 //EZT
	 @PostMapping
	 public CompanyDto createCompany(@RequestBody CompanyDto company) {
		 
		 CompanyDto companyDto = companyMapper.CompanyToCompanyDto(companyService.createCompany(companyMapper.companyDtoToCompany(company)));
		 
		 return companyDto;
	 }
	 //EZT
	 @PutMapping("{id}")
	 public ResponseEntity<CompanyDto> modifyCompany(@PathVariable long id, @RequestBody CompanyDto companyDto) {
		 
		 companyDto.setId(id);
		  CompanyDto company = companyMapper.CompanyToCompanyDto(companyService.modifyCompany(companyMapper.companyDtoToCompany(companyDto), id));

		 return ResponseEntity.ok(company);
	}
	 //EZT
	 @DeleteMapping("/{id}")
	 public void deleteCompany(@PathVariable long id) {

		companyService.deleteCompany(id);
	}
	 
	 
	 
	 @GetMapping("find/{salary}")
	 public Collection<CompanyDto> getCompaniesBySalary(@PathVariable double salary){
		 
		 Collection<CompanyDto> companies = companyMapper.companiesToCompanyDtoCollection(companyService.getCompaniesBySalaryGreaterThan(salary));
		return companies;
		 
	 }
	 
	 @GetMapping("findbycount/{count}")
	 public Collection<CompanyDto> getCompaniesByEmployeeCount(@PathVariable int count){
		 
		 Collection<CompanyDto> companies = companyMapper.companiesToCompanyDtoCollection(companyService.getCompaniesByEmployeeCountGreaterThan(count));
		return companies;
	 }
	 
	 @GetMapping("findbysalary/{id}")
	 public java.util.List<AverageSalaryByPosition> getCompEmployeeAVGSalary(@PathVariable long id){
		 
		 java.util.List<AverageSalaryByPosition> company = companyService.getCompAVGSalary(id);
		return company;
	 }
	 
	 
	 @PostMapping("/{id}/emplyoees") 
	 public ResponseEntity<CompanyDto> addEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable Long id) {
		 
		return ResponseEntity.ok(
				companyMapper.CompanyToCompanyDto(
							companyService.editEmployee(
									id,
									employeeMapper.dtoToEmployee(employeeDto)
							)
				)
		);
	 }

	 
	 @DeleteMapping("/{id}/employeeEdit/{employeeId}")
	 public ResponseEntity<CompanyDto> deleteEmployee(@PathVariable long id,@PathVariable long employeeId) {
		
		 CompanyDto company = companyMapper.CompanyToCompanyDto(companyService.removeEmployee(id, employeeId));
		 
		 return ResponseEntity.ok(company);		
	}
	 
	 
	 
	 @PutMapping("/{id}/employees")
	 public ResponseEntity<CompanyDto> updateEmployees(@RequestBody List<EmployeeDto> employeeDto, @PathVariable Long id) {
			
		 
	     CompanyDto company = companyMapper.CompanyToCompanyDto(companyService.editEmployeeList(employeeMapper.employeeDtoListToEmployeeList(employeeDto), id));
		
	     if(company.equals(null))
			{
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(company);
	}
}