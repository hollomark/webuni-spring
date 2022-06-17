package hu.webuni.hr.mark.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.repository.EmployeeRepository;

@Service
public class DefaultEmployeeService extends EmployeeService{

	
	@Value("${hr.salary.smart.default}")
	private int def;

	@Override
	public int getPayRaisePercent(Employee employee) {
		return def;
	}


}