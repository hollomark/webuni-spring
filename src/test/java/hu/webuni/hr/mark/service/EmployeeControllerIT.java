package hu.webuni.hr.mark.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.mark.dto.EmployeeDto;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {

	private static final String BASE_URI="/api/employees";
	
	@Autowired
	WebTestClient client;
	

//	@Test
//	void addEmployeeTest() throws Exception {
//		EmployeeDto employeeDto = new EmployeeDto(1,"Józsi", "CTO", 12111, LocalDateTime.parse("2021-01-03T08:00:00"));
//		
//		List<EmployeeDto> befDTOs = getAllEmployees();
//		assertThat(!befDTOs.contains(employeeDto));
//		
//		addedEmployee(employeeDto);
//		
//		List<EmployeeDto> afDTOs = getAllEmployees();
//		assertThat(befDTOs.contains(employeeDto));
//
//	}
	
//	@Test
//	void addBadEmployeeTest() throws Exception {
//		EmployeeDto employeeDto = new EmployeeDto(1,"Józsi", "CTO", -12111, LocalDateTime.parse("2021-01-03T08:00:00"));
//		
//		addedBadEmployee(employeeDto);
//		
//		List<EmployeeDto> befDTOs = getAllEmployees();
//		assertThat(!befDTOs.contains(employeeDto));
//		
//	}
	
//	@Test
//	void updateEmployee() throws Exception {
//		EmployeeDto employeeDto = new EmployeeDto(2,"Józsi", "CTO", 12111, LocalDateTime.parse("2021-01-03T08:00:00"));
//		
//		
//		List<EmployeeDto> befDTOs = getAllEmployees();
//		
//		EmployeeDto respEmpDto = updateEmployeeFunc(employeeDto);
//		
//		List<EmployeeDto> aftDTOs = getAllEmployees();
//		
//		
//		assertThat(!befDTOs.contains(respEmpDto));
//		assertThat(!befDTOs.contains(employeeDto));
//		assertThat(aftDTOs.contains(respEmpDto));
//		
//	}
	
//	@Test
//	void updateBadEmployee() throws Exception {
//		EmployeeDto employeeDto = new EmployeeDto(1,"Feri", "CTO", 12111, LocalDateTime.parse("2024-01-03T08:00:00"));
//		
//		List<EmployeeDto> befDTOs = getAllEmployees();
//		
//		EmployeeDto respEmpDto = updateBadEmployee(employeeDto);
//		
//		List<EmployeeDto> aftDTOs = getAllEmployees();
//		
//		
//		assertThat(!befDTOs.contains(respEmpDto));
//		
//		assertThat(!aftDTOs.contains(respEmpDto));
//		
//	}
//	
	
	private void addedEmployee(EmployeeDto emploDto) throws Exception {
		client
		.post()
		.uri(BASE_URI)
		.bodyValue(emploDto)
		.exchange()
		.expectStatus()
		.isOk();
	}
	

	private void addedBadEmployee(EmployeeDto emploDto) throws Exception {
		client
		.post()
		.uri(BASE_URI)
		.bodyValue(emploDto)
		.exchange()
		.expectStatus()
		.isBadRequest();
	}
	
	private EmployeeDto updateEmployeeFunc(EmployeeDto employeDto) {
		
		return client
					.put()
					.uri(BASE_URI + "/" + employeDto.getId())
					.bodyValue(employeDto)
					.exchange()
					.expectStatus()
					.isOk()
					.expectBody(EmployeeDto.class)
					.returnResult()
					.getResponseBody();
	}
	
	private EmployeeDto updateBadEmployee(EmployeeDto employeDto) {
		
		return client
					.put()
					.uri(BASE_URI + "/" + employeDto.getId())
					.bodyValue(employeDto)
					.exchange()
					.expectStatus()
					.isBadRequest()
					.expectBody(EmployeeDto.class)
					.returnResult()
					.getResponseBody();
	}
	
	private List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> responseList = client
			.get()
			.uri(BASE_URI)
			.exchange()
			.expectStatus().isOk()
			.expectBodyList(EmployeeDto.class)
			.returnResult().getResponseBody();
		
		
		return responseList;
	}
}

