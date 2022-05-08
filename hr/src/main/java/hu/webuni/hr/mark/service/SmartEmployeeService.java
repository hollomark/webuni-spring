package hu.webuni.hr.mark.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.mark.model.Employee;

@Service
public class SmartEmployeeService implements EmployeeService{
	
		@Value("${hr.salary.smart.jun}")
		private int junior;

		@Value("${hr.salary.smart.med}")
		private int medior;

		@Value("${hr.salary.smart.sen}")
		private int senior;
		
		@Value("${hr.salary.smart.default}")
		private int def;
		
		@Value("${hr.salary.smart.sendate}")
		private int sendate;
		
		@Value("${hr.salary.smart.meddate}")
		private int meddate;
		
		@Value("${hr.salary.smart.jundate}")
		private int jundate;
	
	@Override
	public double getPayRaisePercent(Employee employee) {
		LocalDate current_date = LocalDate.now();
		int currentYear = current_date.getYear();
		if(employee.getStartDate().getYear() <= currentYear-sendate) {
			return senior;
		} else if( employee.getStartDate().getYear() > currentYear-meddate) {
			return	medior;
		} else if( employee.getStartDate().getYear() > currentYear-jundate) {
			return junior;
		}
		return def;
	}
}
