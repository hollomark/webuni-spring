package hu.webuni.hr.mark.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;

//@NamedEntityGraph(
//		name = "Company.full",
//		attributeNodes =@NamedAttributeNode("companyEmployees")
//)

@NamedEntityGraph(name = "Company.full", 
attributeNodes = {
	@NamedAttributeNode(value = "companyEmployees", subgraph = "employeesGraph") },
	subgraphs = { @NamedSubgraph(name = "employeesGraph", attributeNodes = @NamedAttributeNode("positions")) 
})
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Company {
	
	@Id
	@GeneratedValue
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	private String companyNumber;
	private String companyName;
	private String companyAddress;
	
	@ManyToOne
	private CompanyType companyType;
	
	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}
	@OneToMany(mappedBy = "company")
	private List<Employee> companyEmployees = new ArrayList<>();
	
	public Company(String companyNumber, String companyName, String companyAddress,List<Employee> companyEmployees) {
		this.companyNumber = companyNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyEmployees = companyEmployees;
	}
	
	
	public Company() {}
	
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public List<Employee> getCompanyEmployees() {
		return companyEmployees;
	}
	public void setCompanyEmployees(List<Employee> companyEmployees) {
		this.companyEmployees = companyEmployees;
	}
	
	public void addEmployee(Employee employee) {
		
		employee.setCompany(this);
	}
}
