package hu.webuni.hr.mark.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.hr.mark.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long > {

	List<Employee> findByPositions(String position);
	
	List<Employee> findByNameStartingWithIgnoreCase(String name);
	
	List<Employee> findByStartDateBetween(LocalDateTime from, LocalDateTime to);
}
