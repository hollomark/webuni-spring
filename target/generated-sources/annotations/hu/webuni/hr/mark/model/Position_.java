package hu.webuni.hr.mark.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Position.class)
public abstract class Position_ {

	public static volatile SingularAttribute<Position, Qualifications> qualifications;
	public static volatile SingularAttribute<Position, String> name;
	public static volatile SingularAttribute<Position, Long> id;
	public static volatile SingularAttribute<Position, Double> minSalary;
	public static volatile ListAttribute<Position, Employee> employees;

	public static final String QUALIFICATIONS = "qualifications";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String MIN_SALARY = "minSalary";
	public static final String EMPLOYEES = "employees";

}

