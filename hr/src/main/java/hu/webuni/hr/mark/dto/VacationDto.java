package hu.webuni.hr.mark.dto;

import hu.webuni.hr.mark.model.Employee;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VacationDto {


    Long id;
    Employee applicant;
    String status;
    LocalDate startDate;
    LocalDate toDate;
    Employee approve;

    public VacationDto(){

    }
    public VacationDto(Employee applicant,String status, LocalDate startDate, LocalDate toDate, Employee approve){
        this.applicant = applicant;
        this.status = status;
        this.startDate = startDate;
        this.toDate = toDate;
        this.approve = approve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getApplicant() {
        return applicant;
    }

    public void setApplicant(Employee applicant) {
        this.applicant = applicant;
    }

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

    public Employee getApprove() {
        return approve;
    }

    public void setApprove(Employee approver) {
        this.approve = approver;
    }
}
