package hu.webuni.hr.mark.model;

import java.time.LocalDateTime;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class Employee {
	public Employee(long id, String name, String position, int salary, LocalDateTime startDate) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}
	
	public Employee() {}
	
	private long id;
	private String name;
	private String position;
	private double salary; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDate;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double d) {
		this.salary = d;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	
}
