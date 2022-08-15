package hu.webuni.hr.mark.service;

import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.model.HrUser;
import hu.webuni.hr.mark.model.Vacation;
import hu.webuni.hr.mark.repository.EmployeeRepository;
import hu.webuni.hr.mark.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class VacationService {

    @Autowired
    VacationRepository vacationRepository;
    @Autowired
    EmployeeRepository employeeRepository;


    public Page<Vacation> listAllPage(Pageable page){
        return vacationRepository.findAllPage(page);
    }

    public List<Vacation> listAll(){
        return vacationRepository.findAll();
    }
    @Transactional
    public Vacation addNew(Long employeeId, Vacation dayOff) {
        Employee emp = employeeRepository.getById(employeeId);
        dayOff.setApplicant(emp);
        vacationRepository.save(dayOff);

        return dayOff;
    }


    @Transactional
    public void delete(Long id) throws AccessDeniedException {
        Vacation vacation = vacationRepository.getById(id);
        checkOwnerOfHolidayRequest(vacation);

        if (vacation.getStatus().equals("pending")) {
            vacationRepository.delete(vacation);
        }
    }

    private void checkOwnerOfHolidayRequest(Vacation vacation) throws AccessDeniedException {
        HrUser user = getCurrentUser();
        Vacation vacation1 = vacation.getApplicant().getId();
        if(!vacation1.getId().equals(user.getEmployee().getId())) {
            throw new AccessDeniedException("reguest is not owned by current user");
        }
    }

    private HrUser getCurrentUser() {

        return (HrUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Vacation approveVacation(Long id, boolean status) throws Exception {

        Vacation vacation= vacationRepository.findById(id).get();
        Vacation approverId= getCurrentUser().getEmployee().getId();

        //find the correct leader
        if (vacation.getApplicant().getLeader().getId() != approverId.getApprove().getId()){
            throw new AccessDeniedException("Approve os allowed only by the mng of the employee");
        }
        vacation.setStatus("approved");
        vacation.setApprove(approverId.getApprove());
        vacationRepository.save(vacation);

        return vacation;
    }

    public List<Vacation> findDayOff(Vacation dayOff) {
        Specification<Vacation> specification = Specification.where(null);

        if(dayOff.getId() != null)
        {
            specification = specification.and(VacationSpecification.searchById(dayOff.getId()));
        }
        if(dayOff.getStatus() != null)
        {
            specification = specification.and(VacationSpecification.searchByStatus(dayOff.getStatus()));
        }
        if(dayOff.getApplicant() != null)
        {
            specification = specification.and(VacationSpecification.searchByName(dayOff.getApplicant().getName()));
        }
        if(dayOff.getApprove() != null)
        {
            specification = specification.and(VacationSpecification.searchByApprover(dayOff.getApprove().getName()));
        }

        if(dayOff.getStartDate() != null)
        {
            specification = specification.and(VacationSpecification.searchByStartDate(dayOff.getStartDate(), dayOff.getToDate()))
                    .and(VacationSpecification.searchByEndDate(dayOff.getStartDate(), dayOff.getToDate()));
        }

        return vacationRepository.findAll(specification, Sort.by("id"));

    }


}