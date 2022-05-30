package hu.webuni.hr.mark.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hu.webuni.hr.mark.dto.EmployeeDto;
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
	
	@GetMapping("employee{id}")
	public String getEmployee(Map<String, Object> model,@RequestParam String id) {
		
		for (Employee employees : allEmployee) {

			if(employees.getId()== Integer.parseInt(id)) {
				model.put("employees",employees);
				
			}
		}
		
		return "employee";
		
	}

	@PostMapping("/update")
	public String updateEmployee(Map<String, Object> model, Employee employee) {
		
		for (int i = 0; i < allEmployee.size(); i++) {
			if(employee.getId() == allEmployee.get(i).getId()) {
				
				allEmployee.get(i).setName(employee.getName());
				allEmployee.get(i).setPosition(employee.getPosition());
				allEmployee.get(i).setSalary(employee.getSalary());
				allEmployee.get(i).setStartDate(employee.getStartDate());
			}
		}
		 
		 return "redirect:employees";
	}
	
	
	@GetMapping("/delete{id}")
	public String deleteEmployee(@RequestParam String id) {
		
		for (int i = 0; i < allEmployee.size(); i++) {
			if(allEmployee.get(i).getId() == Integer.parseInt(id)) {
				allEmployee.remove(i);
				
			}
		}
		return "redirect:employees";
		
	}

}
