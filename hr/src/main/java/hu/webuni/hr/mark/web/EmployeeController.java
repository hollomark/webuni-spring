package hu.webuni.hr.mark.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.hr.mark.model.Employee;

@Controller
public class EmployeeController {
	private List<Employee> allEmployee = new ArrayList<>();

	{
		allEmployee.add(new Employee(2,"Józsi","CTO",45500,LocalDateTime.parse("2021-01-13T17:09:42.411")));
		allEmployee.add(new Employee(3,"Mari","BTO",65500,LocalDateTime.parse("2011-01-13T17:09:42.411")));
		
	}
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/employees")
	public String listEmployees(Map<String, Object> model) {
		model.put("employees",allEmployee);
		model.put("newEmployee", new Employee());
		
		return "employees";
	}
	@PostMapping("/employees")
	public String addEmployee(Employee employee) {
		allEmployee.add(employee);
		
		return "redirect:employees";
		
	}


}
