package hu.webuni.hr.mark.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.model.Company;
import hu.webuni.hr.mark.model.Employee;

@Service
public class CompanyService {

	private Map<Long, Company> companies = new HashMap<>();
	
	
	public Collection<Company> getAllCompanies() {
		
		return companies.values();
	}
	
	
	public Company getCompany(long id) {
		
		if(companies.containsKey(id) ) {
			return companies.get(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
	
	public Collection<Company> getAllCompaniesWithoutEmployees() {
		
		Map<Long, Company> companyCollection = companies.entrySet().stream()
				.collect(
						Collectors.toMap(a -> (Long)a.getKey(), a -> {a.getValue().setCompanyEmployees(Collections.emptyList()); return (Company) a;}));
				
		
		return companyCollection.values();

	}
	
	
	public Company createCompany(Company company) {
		
		
		companies.put((long) company.getId(), company);

		return company;
	}
	
	public Company modifyCompany(Company company, long id) {
		
		if(!companies.containsKey(id) ) {
			companies.put((long) company.getId(), company);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return company;
	}
	
	
	public void deleteCompany(long id) {
		
		companies.remove(id);
	}
	
	public Company findByIdOrRetErr(long id) {
		 Company company = companies.get(id);
		 if(company == null)
			{
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			}
		 
		return company;
	 }
	
}
