package hu.webuni.hr.mark.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CompanyType {

	@Id
	@GeneratedValue
	private long id;
	
	private String TypeName;
	public CompanyType(String name) {
		this.TypeName = name;
	}
	public CompanyType() {}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTypeName() {
		return TypeName;
	}

	public void setTypeName(String TypeName) {
		this.TypeName = TypeName;
	}

	
	
	
	
}
