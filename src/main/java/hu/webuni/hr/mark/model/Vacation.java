package hu.webuni.hr.mark.model;

import jdk.vm.ci.meta.Local;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Vacation {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee applicant;

    String status;

    LocalDate startDate;

    LocalDate toDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "approve_id")
    Employee approve;

    public Employee getApplicant() {
        return applicant;
    }

    public void setApplicant(Employee employee) {
        this.applicant = employee;
    }

    public Employee getApprove() {
        return approve;
    }

    public void setApprove(Employee approve) {
        this.approve = approve;
    }

    public Vacation(){}

    public Vacation(Employee applicant, String status, LocalDate startDate, LocalDate toDate, Employee approve){
        this.applicant = applicant;
        this.approve=approve;
        this.status=status;
        this.startDate=startDate;
        this.toDate=toDate;
    }


}
