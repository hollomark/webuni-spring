package hu.webuni.hr.mark.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.mark.model.Employee;


@Service
public class SmartEmployeeService extends EmployeeService{

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
	public int getPayRaisePercent(Employee employee) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		if(employee.getStartDate().until(currentDateTime, ChronoUnit.MONTHS) >= sendate) {
			return senior;
		} else if( employee.getStartDate().until(currentDateTime, ChronoUnit.MONTHS) >= meddate) {
			return	medior;
		} else if( employee.getStartDate().until(currentDateTime, ChronoUnit.MONTHS) >= jundate) {
			return junior;
		}
		return def;
	}
}
