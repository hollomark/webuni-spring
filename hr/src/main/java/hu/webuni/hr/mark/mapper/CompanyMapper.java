package hu.webuni.hr.mark.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

	
	Company companyDtoToCompany(CompanyDto company);
	
	CompanyDto CompanyToCompanyDto(Company company);
	
	Collection<CompanyDto> companiesToCompanyDtoCollection(Collection<Company> collection);
	
}
