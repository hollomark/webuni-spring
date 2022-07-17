package hu.webuni.hr.mark.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	@Mapping(target="companyDto.companyEmployees", ignore = true)
	EmployeeDto employeeToDto(Employee employee);
	
	Employee dtoToEmployee(EmployeeDto employee);
	
	List<Employee> employeeDtoListToEmployeeList(List<EmployeeDto> employeeDtos);
	
	Collection<EmployeeDto> collectionEmployeeDtoListToEmployeeList(Collection<Employee> employeeDtos);

}
