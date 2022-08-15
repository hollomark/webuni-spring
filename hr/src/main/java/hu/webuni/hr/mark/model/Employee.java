package hu.webuni.hr.mark.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

//@ConfigurationProperties(prefix = "hr")
//@Component
@Entity
public class Employee {

	@OneToMany(mappedBy = "leader")
	private List<Employee> managedEmployees;

	public Employee(String name, double salary, LocalDateTime startDate, Company company) {
		this.name = name;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String username;
	private String password;
	@ManyToOne
	@JoinColumn(name = "leader_id")
	private Employee leader;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDate;

	public Employee getLeader() {
		return leader;
	}

	public void setLeader(Employee leader) {
		this.leader = leader;
	}

	public Vacation getId() {
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
	public List<Employee> getManagedEmployees() {
		return managedEmployees;
	}

	public void setManagedEmployees(List<Employee> managedEmployees) {
		this.managedEmployees = managedEmployees;
	}

}