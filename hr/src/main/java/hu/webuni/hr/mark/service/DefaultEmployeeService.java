package hu.webuni.hr.mark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import hu.webuni.hr.mark.model.Employee;

@Service
public class DefaultEmployeeService implements EmployeeService{
	
	@Value("${hr.salary.smart.default}")
	private int def;

	@Override
	public double getPayRaisePercent(Employee employee) {
		return def;
	}


}