package hu.webuni.hr.mark.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.mapper.CompanyMapper;
import hu.webuni.hr.mark.mapper.EmployeeMapper;
import hu.webuni.hr.mark.model.Position;
import hu.webuni.hr.mark.model.Qualifications;
import hu.webuni.hr.mark.repository.CompanyRepository;
import hu.webuni.hr.mark.repository.PositionRepository;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class CompanyServiceIT {
	private static final String BASE_URI = "/api/companies";
	
	@Autowired
	WebTestClient webTestClient;
	
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	PositionRepository positionRepository;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	InitDbService dbService;
	
	@Autowired
	CompanyRepository companyRepository;
	
	
	
	
//	@Test
//	void testCreateCompany() throws Exception {
//		dbService.clearDb();
//		
//		//String companyNumber, String companyName, String companyAddress,List<Employee> companyEmployees
//		//(long id,String companyNumber, String companyName, String companyAddress,List<EmployeeDto> companyEmployees) {
//		CompanyDto companyDto = new CompanyDto("ASSD","Test Company", "József krt. 33", null);
//		CompanyDto saved = saveCompany(companyDto);
//		
//		assertThat(saved.equals(companyDto));
//		
//		Position pos = new Position(Qualifications.HIGH_SCOOL, "Titkár", 100000);
//		positionRepository.save(pos);
//		EmployeeDto employee = new EmployeeDto(1L, "Teszt Miklós", pos, 111110, LocalDateTime.parse("2020-01-01T08:00:00"), null);
//		saveEmp(employee);
//		addEmployee(saved, employee);
//		CompanyDto compSaved2 = getCompany(saved);
//		
//		assertThat(compSaved2.getCompanyEmployees().contains(employee));	
//		
//	}
	

	
	
//	@Test
//	void overwriteEmployeeOfCompany() throws Exception {
//		
//		dbService.clearDb();
//		
//		CompanyDto comp = new CompanyDto("111111", "Teszt cég", "Teszt 1 utca", null);
//		CompanyDto compSaved = saveCompany(comp);
//		
//		assertThat(compSaved.equals(comp));
//		
//		Position pos = new Position(Qualifications.HIGH_SCOOL, "Titkár", 100000);
//		positionRepository.save(pos);
//		
//		EmployeeDto employee = new EmployeeDto(1L, "Teszt Miklós", pos, 111110, LocalDateTime.parse("2020-01-01T08:00:00"), null);
//		saveEmp(employee);
//		
//		addEmployee(compSaved, employee);
//		CompanyDto compSaved2 = getCompany(compSaved);
//		
//		assertThat(compSaved2.getCompanyEmployees().contains(employee));
//		
//		updateEmployee(compSaved2, employee);
//		compSaved2 = getCompany(compSaved);
//		assertThat(compSaved2.getCompanyEmployees().contains(employee));
//		
//	}
//	
	@Test
	void deleteEmployeeFromCompany() throws Exception {
	
		dbService.clearDb();
		
		CompanyDto comp = new CompanyDto("111111", "Teszt cég", "Teszt 1 utca", null);
		CompanyDto compSaved = saveCompany(comp);
		
		assertThat(compSaved.equals(comp));
		
		
		Position pos = new Position(Qualifications.HIGH_SCOOL, "Titkár", 100000);
		positionRepository.save(pos);
		
		EmployeeDto employee = new EmployeeDto(1L, "Teszt Miklós", pos, 111110, LocalDateTime.parse("2020-01-01T08:00:00"), null);
		saveEmp(employee);
		
		addEmployee(compSaved, employee);
		CompanyDto compSaved2 = getCompany(compSaved);

		EmployeeDto savedEmp = (EmployeeDto) compSaved2.getCompanyEmployees();
		
		compSaved2 = deleteEmployee(compSaved2, savedEmp);
		assertThat(compSaved2.getCompanyEmployees() == null);
		
	}
	
	
	
	private CompanyDto deleteEmployee(CompanyDto company, EmployeeDto employee) {
		
		return webTestClient
					.delete()
					.uri(BASE_URI + "/" + company.getId() + "/employeeEdit/" + employee.getId())
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(CompanyDto.class)
					.returnResult()
					.getResponseBody();
	}

	
	
	
	
	private CompanyDto updateEmployee(CompanyDto comp, EmployeeDto emp) {
		
		List<EmployeeDto> em = new ArrayList<EmployeeDto>();
		em.add(emp);
		
		return webTestClient
				.put()
				.uri(BASE_URI + "/" + comp.getId() + "/employeeEdit")
				.bodyValue(em)
				.exchange()
				.expectBody(CompanyDto.class)
				.returnResult()
				.getResponseBody();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	private CompanyDto saveCompany(CompanyDto comp) {
		
		return webTestClient
					.post()
					.uri(BASE_URI)
					.bodyValue(comp)
					.exchange()
					.expectBody(CompanyDto.class)
					.returnResult()
					.getResponseBody();
					
	}
	
	private void saveEmp(EmployeeDto employee) {

		webTestClient
			.post()
			.uri("/api/employees")
			.bodyValue(employee)
			.exchange()
			.expectStatus()
			.isOk();			
		
	}
	
	private CompanyDto addEmployee(CompanyDto comp, EmployeeDto employeeDto) {
		
		List<EmployeeDto> employeeList = new ArrayList<EmployeeDto>();
		employeeList.add(employeeDto);
		
		return webTestClient
				.post()
				.uri(BASE_URI + "/" + comp.getId() + "/emplyoees")
				.bodyValue(employeeList)
				.exchange()
				.expectBody(CompanyDto.class)
				.returnResult()
				.getResponseBody();
		
	}
	
	private CompanyDto getCompany(CompanyDto comp) {
		
		return webTestClient
					.get()
					.uri(BASE_URI + "/getid/" + comp.getId())
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(CompanyDto.class)
					.returnResult()
					.getResponseBody();
	}
}
