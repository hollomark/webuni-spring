package hu.webuni.hr.mark.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vacation.class)
public abstract class Vacation_ {

	public static volatile SingularAttribute<Vacation, Employee> approve;
	public static volatile SingularAttribute<Vacation, LocalDate> toDate;
	public static volatile SingularAttribute<Vacation, Long> id;
	public static volatile SingularAttribute<Vacation, LocalDate> startDate;
	public static volatile SingularAttribute<Vacation, Employee> applicant;
	public static volatile SingularAttribute<Vacation, String> status;

	public static final String APPROVE = "approve";
	public static final String TO_DATE = "toDate";
	public static final String ID = "id";
	public static final String START_DATE = "startDate";
	public static final String APPLICANT = "applicant";
	public static final String STATUS = "status";

}

