package hu.webuni.hr.mark.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ {

	public static volatile SingularAttribute<Company, String> companyNumber;
	public static volatile SingularAttribute<Company, CompanyType> companyType;
	public static volatile SingularAttribute<Company, String> companyName;
	public static volatile SingularAttribute<Company, String> companyAddress;
	public static volatile SingularAttribute<Company, Long> id;
	public static volatile ListAttribute<Company, Employee> companyEmployees;

	public static final String COMPANY_NUMBER = "companyNumber";
	public static final String COMPANY_TYPE = "companyType";
	public static final String COMPANY_NAME = "companyName";
	public static final String COMPANY_ADDRESS = "companyAddress";
	public static final String ID = "id";
	public static final String COMPANY_EMPLOYEES = "companyEmployees";

}

