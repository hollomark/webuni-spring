package hu.webuni.hr.mark.web;

import hu.webuni.hr.mark.dto.VacationDto;
import hu.webuni.hr.mark.mapper.VacationMapper;
import hu.webuni.hr.mark.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/vacation")
public class VacationController {


    private static final int linePerPage = 5;

    @Autowired
    VacationService vacationService;

    @Autowired
    VacationMapper vacationMapper;

    @PostMapping("/list/filter")
    public List<VacationDto> findHoliday(@RequestBody VacationDto dayOff){

        return vacationMapper.vacationDtoListToVacationList(vacationService.findDayOff(vacationMapper.vacationDtoToVacation(dayOff)));
    }

    @GetMapping("/list/{page}/{orderer}")
    public List<VacationDto> getDayoffs(@PathVariable int page, @PathVariable String orderer) {

        Pageable pageable = PageRequest.of(page, linePerPage, Sort.by(orderer));
        return vacationMapper.vacationDtoListToVacationList(vacationService.listAllPage(pageable).getContent());
    }

    @GetMapping(value = "/approve/{dayOffId}/{approverId}", params = {"status"})
    @PreAuthorize("#vacation.applicant.id == authentication.principal.employee.Id")
    public VacationDto approve(@PathVariable Long dayOffId, boolean status) throws Exception {

        return vacationMapper.vacationToVacationDto(vacationService.approveVacation(dayOffId, status));
    }
    @PostMapping("/{employeeId}")
    @PreAuthorize("#vacation.applicant.id == authentication.principal.employee.Id")
    public VacationDto addNew(@RequestBody VacationDto dayOff, @PathVariable long employeeId) {
        return vacationMapper.vacationToVacationDto(
                (vacationService.addNew( employeeId, vacationMapper.vacationDtoToVacation(dayOff))
                ));
    }


    @DeleteMapping("/{dayOffId}")
    @PreAuthorize("#vacation.applicant.id == authentication.principal.employee.Id")
    public void deleteDayOff(@PathVariable long vacationId) throws AccessDeniedException {
        vacationService.delete(vacationId);
    }

}
