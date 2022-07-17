package hu.webuni.hr.mark.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, Employee> leader;
	public static volatile SingularAttribute<Employee, String> name;
	public static volatile SingularAttribute<Employee, Company> company;
	public static volatile SingularAttribute<Employee, Position> positions;
	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, Double> salary;
	public static volatile SingularAttribute<Employee, LocalDateTime> startDate;

	public static final String LEADER = "leader";
	public static final String NAME = "name";
	public static final String COMPANY = "company";
	public static final String POSITIONS = "positions";
	public static final String ID = "id";
	public static final String SALARY = "salary";
	public static final String START_DATE = "startDate";

}

