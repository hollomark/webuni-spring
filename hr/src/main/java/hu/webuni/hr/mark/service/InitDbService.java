package hu.webuni.hr.mark.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.model.Company;
import hu.webuni.hr.mark.model.CompanyType;
import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.model.Position;
import hu.webuni.hr.mark.model.Qualifications;
import hu.webuni.hr.mark.repository.CompanyRepository;
import hu.webuni.hr.mark.repository.CompanyTypeRepository;
import hu.webuni.hr.mark.repository.EmployeeRepository;
import hu.webuni.hr.mark.repository.PositionRepository;


@Service
public class InitDbService {

	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CompanyTypeRepository companyTypeRepository;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Transactional
	public void clearDb() {
		employeeRepository.deleteAll();
		
		companyRepository.deleteAll();
		
		companyTypeRepository.deleteAll();
	}
	@Transactional
	public void insertTestData() { 
		
		Position dev1 = positionRepository.save(new Position(Qualifications.COLLEGE, "Front End Developer",4000));
		
		Position manager = positionRepository.save(new Position(Qualifications.COLLEGE, "Manager",4000));
		
		Employee employee = new Employee("Mekk Elek", 45587,LocalDateTime.parse("2000-01-13T17:09:42.411"),null);
		Employee employee2 = new Employee("Takk Elek",25587,LocalDateTime.parse("2010-01-13T17:09:42.411"),null);
		
		employee.setPositions(manager);
		employee2.setPositions(dev1);
		
		employeeRepository.save(employee);
		employeeRepository.save(employee2);
		
		Company company = new Company("123sad","József krt. 34","sad", null);
		Company company2 = new Company("123sad","József krt. 34","sad", null);
		
		List<Employee> employees = new ArrayList<Employee>();
		
		CompanyType bt = new CompanyType("bt");
		CompanyType kft = new CompanyType("kft");
		CompanyType zrt = new CompanyType("zrt");
		CompanyType nyrt = new CompanyType("nyrt");

		companyTypeRepository.save(bt);
		companyTypeRepository.save(kft);
		companyTypeRepository.save(zrt);
		companyTypeRepository.save(nyrt);
		
		company.setCompanyType(bt);
		company2.setCompanyType(nyrt);
		employees.add(employee);
		employees.add(employee2);
		
		employee.setCompany(company);
		employee2.setCompany(company);
		
		company.setCompanyEmployees(employees);
		company2.setCompanyEmployees(employees);
		companyRepository.save(company);
		companyRepository.save(company2);
	}
	
}
