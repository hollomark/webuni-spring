package hu.webuni.hr.mark.dto;

import java.util.ArrayList;
import java.util.List;



public class CompanyDto {
	

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
	private List<EmployeeDto> companyEmployees = new ArrayList<>();
	
	public CompanyDto(int id,String companyNumber, String companyName, String companyAddress,
			List<EmployeeDto> companyEmployees) {
		this.id = id;
		this.companyNumber = companyNumber;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyEmployees = companyEmployees;
	}
	
	public CompanyDto() {}
	
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
	public List<EmployeeDto> getCompanyEmployees() {
		return companyEmployees;
	}
	public void setCompanyEmployees(List<EmployeeDto> companyEmployees) {
		this.companyEmployees = companyEmployees;
	}
	

	
	
	
}
