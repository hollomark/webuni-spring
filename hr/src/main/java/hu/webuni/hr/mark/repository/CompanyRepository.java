package hu.webuni.hr.mark.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.webuni.hr.mark.model.AverageSalaryByPosition;
import hu.webuni.hr.mark.model.Company;


public interface CompanyRepository extends JpaRepository<Company, Long > { 
	
	@Query("SELECT DISTINCT c FROM Company c JOIN c.companyEmployees e WHERE e.salary >= :salaryLimit")
	Collection<Company> findBySalaryGreaterThanEqual(double salaryLimit);
	
	@Query("SELECT DISTINCT c FROM Company c WHERE SIZE(c.companyEmployees) > :employeeLimit")
	Collection<Company> findByEmployeCountGreaterThanEqual(int employeeLimit);
	
	@Query("SELECT AVG(e.salary) as salary, e.positions.name as position FROM Company c INNER JOIN c.companyEmployees e WHERE c.id = :id GROUP BY e.positions.name, e.positions.id ORDER BY AVG(e.salary) DESC")
	java.util.List<AverageSalaryByPosition> getAVGSalaryByPosOrderBy(long id);
	
	
	
}
