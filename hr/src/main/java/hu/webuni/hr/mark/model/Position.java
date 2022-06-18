package hu.webuni.hr.mark.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Position {
 
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(mappedBy="positions")
	private List<Employee> employees;
	
	
	private Qualifications qualifications;
	
	
	public List<Employee> getEmployees() {
		return employees;
	}
	public Qualifications getQualifications() {
		return qualifications;
	}
	public void setQualifications(Qualifications qualifications) {
		this.qualifications = qualifications;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	private String name;

	private double minSalary;
	public Position() {}

	public Position(Qualifications qualifications, String name) {
		this.qualifications = qualifications;
		this.name = name;
	}
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
	public double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}
	
	
	
	
	
	
}
