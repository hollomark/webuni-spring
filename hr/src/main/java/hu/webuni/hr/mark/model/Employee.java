package hu.webuni.hr.mark.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "hr")
//@Component
@Entity
public class Employee {
	public Employee(String name, double salary, LocalDateTime startDate, Company company) {
		this.name = name;
//		this.position = position;
		this.salary = salary;
		this.startDate = startDate;
		this.company = company;
	}
	
	public Employee() {}
	
	@ManyToOne
	private Company company;
	
	@ManyToOne
	private Position positions;
	
	public Position getPositions() {
		return positions;
	}

	public void setPositions(Position positions) {
		this.positions = positions;
	}

	@Id
	@GeneratedValue
	private long id;
	private String name;
//	private String position;
	private double salary; 
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDate;
	public long  getId() {
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
//	public String getPosition() {
//		return position;
//	}
//	public void setPosition(String position) {
//		this.position = position;
//	}
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
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
}
