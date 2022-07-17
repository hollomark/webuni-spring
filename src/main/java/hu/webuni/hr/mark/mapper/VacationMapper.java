package hu.webuni.hr.mark.mapper;

import hu.webuni.hr.mark.dto.VacationDto;
import hu.webuni.hr.mark.model.Vacation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VacationMapper {

    Vacation vacationDtoToVacation(VacationDto vacationDto);
    VacationDto vacationToVacationDto(Vacation vacation);
    List<VacationDto> vacationDtoListToVacationList(List<Vacation> list);

}
