package hu.webuni.hr.mark.service;

import hu.webuni.hr.mark.model.Vacation;
import hu.webuni.hr.mark.model.Vacation_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class VacationSpecification {

    public static Specification<Vacation> searchById(Long id){


        return (root, cq,cb) -> cb.equal(root.get(Vacation_.id),id);
    }


    public static Specification<Vacation> searchByStatus(String status) {

        return (root, cq, cb) -> cb.equal(root.get(Vacation_.status), status);
    }

    public static Specification<Vacation> searchByName(String name) {

        return (root, cq, cb) -> cb.equal(root.get(Vacation_.applicant.getName()), name);
    }

    public static Specification<Vacation> searchByApprover(String name) {

        return (root, cq, cb) -> cb.equal(root.get(Vacation_.approve.getName()), name);
    }
    public static Specification<Vacation> searchByStartDate(LocalDate startDate, LocalDate toDate) {

        return (root, cq, cb) -> cb.between(root.get(Vacation_.startDate), startDate, toDate);
    }

    public static Specification<Vacation> searchByEndDate(LocalDate startDate, LocalDate toDate) {

        return (root, cq, cb) -> cb.between(root.get(Vacation_.toDate), startDate, toDate);
    }

}
