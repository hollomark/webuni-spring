package hu.webuni.hr.mark.model;

import java.util.ArrayList;
import java.util.List;


public class Company {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private String companyNumber;
	private String companyName;
	private String companyAddress;
	private List<Employee> companyEmployees = new ArrayList<>();
	
	public Company(int id,String companyNumber, String companyName, String companyAddress,
			List<Employee> companyEmployees) {
		this.id = id;
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
}
