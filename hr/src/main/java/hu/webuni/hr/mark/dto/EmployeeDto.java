package hu.webuni.hr.mark.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

public class EmployeeDto{
	
	public EmployeeDto(long id, String name, String position, int salary, LocalDateTime startDate) {
		this.id = id;
		this.name = name;
		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
	}
	
	public EmployeeDto() {}
	
	private long id;
	 @NotBlank(message = "Name is mandatory")
	private String name;
	
	
	@NotBlank(message = "Name is mandatory")
	private String position;
	
	@Positive
	private double salary; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@Past
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
