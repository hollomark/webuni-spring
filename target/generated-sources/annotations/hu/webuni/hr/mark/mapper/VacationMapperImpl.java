package hu.webuni.hr.mark.mapper;

import hu.webuni.hr.mark.dto.VacationDto;
import hu.webuni.hr.mark.model.Vacation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T10:56:44+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.15 (Eclipse Adoptium)"
)
@Component
public class VacationMapperImpl implements VacationMapper {

    @Override
    public Vacation vacationDtoToVacation(VacationDto vacationDto) {
        if ( vacationDto == null ) {
            return null;
        }

        Vacation vacation = new Vacation();

        vacation.setStatus( vacationDto.getStatus() );
        vacation.setStartDate( vacationDto.getStartDate() );
        vacation.setToDate( vacationDto.getToDate() );
        vacation.setId( vacationDto.getId() );
        vacation.setApplicant( vacationDto.getApplicant() );
        vacation.setApprove( vacationDto.getApprove() );

        return vacation;
    }

    @Override
    public VacationDto vacationToVacationDto(Vacation vacation) {
        if ( vacation == null ) {
            return null;
        }

        VacationDto vacationDto = new VacationDto();

        vacationDto.setId( vacation.getId() );
        vacationDto.setApplicant( vacation.getApplicant() );
        vacationDto.setStatus( vacation.getStatus() );
        vacationDto.setStartDate( vacation.getStartDate() );
        vacationDto.setToDate( vacation.getToDate() );
        vacationDto.setApprove( vacation.getApprove() );

        return vacationDto;
    }

    @Override
    public List<VacationDto> vacationDtoListToVacationList(List<Vacation> list) {
        if ( list == null ) {
            return null;
        }

        List<VacationDto> list1 = new ArrayList<VacationDto>( list.size() );
        for ( Vacation vacation : list ) {
            list1.add( vacationToVacationDto( vacation ) );
        }

        return list1;
    }
}
