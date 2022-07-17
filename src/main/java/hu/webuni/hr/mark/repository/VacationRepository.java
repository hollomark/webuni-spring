package hu.webuni.hr.mark.repository;

import hu.webuni.hr.mark.model.Vacation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface VacationRepository extends JpaRepository<Vacation, Long>, JpaSpecificationExecutor<Vacation> {

    @Query("SELECT d FROM Vacation d")
    Page<Vacation> findAllPage(Pageable page);
}
