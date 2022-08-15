package hu.webuni.hr.mark;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.service.InitDbService;
import hu.webuni.hr.mark.service.SalaryService;

@SpringBootApplication
public class HrApplication implements CommandLineRunner{

	@Autowired
	SalaryService salaryService;
	
	@Autowired
	InitDbService dbService;
	
	public static void main(String[] args) {
		SpringApplication.run(HrApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Employee employ0 = new Employee("Józsi", 100000, LocalDateTime.parse("2021-01-13T17:09:42.411"),null);
		salaryService.setFinalSalary(employ0);
		System.out.println(employ0.getSalary());
		
		Employee employ1 = new Employee("Imre", 10000, LocalDateTime.parse("2018-01-13T17:09:42.411"),null);
		salaryService.setFinalSalary(employ1);
		System.out.println(employ1.getSalary());
		
		Employee employ2 = new Employee("Lajos", 10000, LocalDateTime.parse("2000-01-13T17:09:42.411"),null);
		salaryService.setFinalSalary(employ2);
		System.out.println(employ2.getSalary());
		
		dbService.clearDb();
		dbService.insertTestData();
		
	}

}
