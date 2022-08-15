package hu.webuni.hr.mark.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.mapper.CompanyMapper;
import hu.webuni.hr.mark.model.AverageSalaryByPosition;
import hu.webuni.hr.mark.model.Company;
import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.repository.CompanyRepository;
import hu.webuni.hr.mark.repository.EmployeeRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyMapper companyMapper;
	
	public Collection<Company> getAllCompanies() {
		
		return companyRepository.findAll();
	}
	
	
	public Company getCompany(long id) {
		
		return companyRepository.findOneWithEmployees(id);
		
	}
	
	public Collection<Company> getAllCompaniesWithoutEmployees() {
		
		return companyRepository.findAll();
	}
	
	
	//EZT
	@Transactional
	public Company createCompany(Company company) {
		
		companyRepository.save(company);
		return company;
	}
	//EZT
	@Transactional
	public Company modifyCompany(Company company, long id) {
		
		
		companyRepository.save(company);
		
		return company;
	}

	//EZT
	public void deleteCompany(long id) {
		
		companyRepository.deleteById(id);
	}
	
	
	
	
	
	public Optional<Company> findByIdOrRetErr(long id) {
		 Optional<Company> company = companyRepository.findById(id);
		 
		return company;
	 }
	public Collection<Company> getCompaniesBySalaryGreaterThan(double salary) {
		
		return companyRepository.findBySalaryGreaterThanEqual(salary);
	}
	
	public Collection<Company> getCompaniesByEmployeeCountGreaterThan(int count) {
		
		return companyRepository.findByEmployeCountGreaterThanEqual(count);
	}
	
	@Transactional
	public Company editEmployee(long id, Employee employee) {
		
		Company company = companyRepository.findOneWithEmployees(id);
		company.addEmployee(employee);
		
		employeeRepository.save(employee);
		return company;
	}
	
	public java.util.List<AverageSalaryByPosition> getCompAVGSalary(long id) {
		
		java.util.List<AverageSalaryByPosition> company = companyRepository.getAVGSalaryByPosOrderBy(id);
		return company;
	}
	
	public Company removeEmployee(long id, long employeeId) {
		
		Company company = companyRepository.findOneWithEmployees(id);
		Employee employee = employeeRepository.findById(employeeId).get();
		employee.setCompany(null);
		company.getCompanyEmployees().remove(employee);
		
		employeeRepository.save(employee);
		companyRepository.save(company);
		
		return company;
	}
	
	public Company editEmployeeList(List<Employee> employeeList, long id) {
		
		Company company = companyRepository.findOneWithEmployees(id);
		
		company.getCompanyEmployees().forEach(a -> a.setCompany(null));
		company.getCompanyEmployees().clear();
		
		for (Employee employee : employeeList) 
		{	
			company.addEmployee(employee);
			employeeRepository.save(employee);
		}
		
		return company;
	}
	
}
