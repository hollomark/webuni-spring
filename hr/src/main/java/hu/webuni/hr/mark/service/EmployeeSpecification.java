package hu.webuni.hr.mark.service;

import org.springframework.data.jpa.domain.Specification;

import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.model.Company_;
import hu.webuni.hr.mark.model.Employee_;
import hu.webuni.hr.mark.model.Position_;
import java.time.LocalDateTime;

public class EmployeeSpecification {
	
	
	
	public static Specification<Employee> hasId(Long id) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.id), id);
	}
	
	public static Specification<Employee> nameStartingWithIgnoreCase(String name) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.name)), name.toLowerCase() + "%");
	}

	public static Specification<Employee> hasPosition(String position) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.positions).get(Position_.name), position);
	}

	public static Specification<Employee> hasSalary(double salary) {
		double salaryPlus5percent = salary + (salary/100)*5;
		double salaryMin5percent = salary - (salary/100)*5;

		return (root, cq, cb) -> cb.between(root.get(Employee_.salary), salaryMin5percent, salaryPlus5percent);
	}

	public static Specification<Employee> startWorkingDate(LocalDateTime startDate) {
		return (root, cq, cb) -> cb.equal(root.get(Employee_.startDate), startDate);
	}

	public static Specification<Employee> hasCompany(String company) {
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Employee_.company).get(Company_.companyName)), company.toLowerCase() + "%");
	}
}
