package hu.webuni.hr.mark.mapper;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.model.Employee;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-17T10:56:43+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.15 (Eclipse Adoptium)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto employeeToDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setName( employee.getName() );
        employeeDto.setSalary( employee.getSalary() );
        employeeDto.setStartDate( employee.getStartDate() );
        employeeDto.setCompanyDto( employeeToCompanyDto( employee ) );

        return employeeDto;
    }

    @Override
    public Employee dtoToEmployee(EmployeeDto employee) {
        if ( employee == null ) {
            return null;
        }

        Employee employee1 = new Employee();

        employee1.setId( employee.getId() );
        employee1.setName( employee.getName() );
        employee1.setSalary( employee.getSalary() );
        employee1.setStartDate( employee.getStartDate() );

        return employee1;
    }

    @Override
    public List<Employee> employeeDtoListToEmployeeList(List<EmployeeDto> employeeDtos) {
        if ( employeeDtos == null ) {
            return null;
        }

        List<Employee> list = new ArrayList<Employee>( employeeDtos.size() );
        for ( EmployeeDto employeeDto : employeeDtos ) {
            list.add( dtoToEmployee( employeeDto ) );
        }

        return list;
    }

    @Override
    public Collection<EmployeeDto> collectionEmployeeDtoListToEmployeeList(Collection<Employee> employeeDtos) {
        if ( employeeDtos == null ) {
            return null;
        }

        Collection<EmployeeDto> collection = new ArrayList<EmployeeDto>( employeeDtos.size() );
        for ( Employee employee : employeeDtos ) {
            collection.add( employeeToDto( employee ) );
        }

        return collection;
    }

    protected CompanyDto employeeToCompanyDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        return companyDto;
    }
}
