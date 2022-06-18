package hu.webuni.hr.mark.mapper;

import hu.webuni.hr.mark.dto.CompanyDto;
import hu.webuni.hr.mark.dto.EmployeeDto;
import hu.webuni.hr.mark.model.Company;
import hu.webuni.hr.mark.model.Employee;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-18T20:34:05+0200",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public Company companyDtoToCompany(CompanyDto company) {
        if ( company == null ) {
            return null;
        }

        Company company1 = new Company();

        company1.setId( company.getId() );
        company1.setCompanyNumber( company.getCompanyNumber() );
        company1.setCompanyName( company.getCompanyName() );
        company1.setCompanyAddress( company.getCompanyAddress() );
        company1.setCompanyEmployees( employeeDtoListToEmployeeList( company.getCompanyEmployees() ) );

        return company1;
    }

    @Override
    public CompanyDto CompanyToCompanyDto(Company company) {
        if ( company == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setId( company.getId() );
        companyDto.setCompanyNumber( company.getCompanyNumber() );
        companyDto.setCompanyName( company.getCompanyName() );
        companyDto.setCompanyAddress( company.getCompanyAddress() );
        companyDto.setCompanyEmployees( employeeListToEmployeeDtoList( company.getCompanyEmployees() ) );

        return companyDto;
    }

    @Override
    public Collection<CompanyDto> companiesToCompanyDtoCollection(Collection<Company> collection) {
        if ( collection == null ) {
            return null;
        }

        Collection<CompanyDto> collection1 = new ArrayList<CompanyDto>( collection.size() );
        for ( Company company : collection ) {
            collection1.add( CompanyToCompanyDto( company ) );
        }

        return collection1;
    }

    protected Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if ( employeeDto == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDto.getId() );
        employee.setName( employeeDto.getName() );
        employee.setSalary( employeeDto.getSalary() );
        employee.setStartDate( employeeDto.getStartDate() );

        return employee;
    }

    protected List<Employee> employeeDtoListToEmployeeList(List<EmployeeDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Employee> list1 = new ArrayList<Employee>( list.size() );
        for ( EmployeeDto employeeDto : list ) {
            list1.add( employeeDtoToEmployee( employeeDto ) );
        }

        return list1;
    }

    protected EmployeeDto employeeToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setId( employee.getId() );
        employeeDto.setName( employee.getName() );
        employeeDto.setSalary( employee.getSalary() );
        employeeDto.setStartDate( employee.getStartDate() );

        return employeeDto;
    }

    protected List<EmployeeDto> employeeListToEmployeeDtoList(List<Employee> list) {
        if ( list == null ) {
            return null;
        }

        List<EmployeeDto> list1 = new ArrayList<EmployeeDto>( list.size() );
        for ( Employee employee : list ) {
            list1.add( employeeToEmployeeDto( employee ) );
        }

        return list1;
    }
}
