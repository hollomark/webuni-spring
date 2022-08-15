package hu.webuni.hr.mark.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import hu.webuni.hr.mark.configuration.ConfigProperties;
import hu.webuni.hr.mark.model.Employee;
import hu.webuni.hr.mark.model.HrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JWTService {

    private static final String MANAGED_EMPLOYEE_USERNAMES = "managed_employee_usernames";
    private static final String MANAGED_EMPLOYEE_IDS = "managed_employee_ids";
    private static final String FULLNAME = "fullname";
    private static final String ID = "id";
    private static final String MANAGER = "manager";
    private static final String AUTH = "auth";
    private static final String MANAGER_USERNAME = "username";
    private Algorithm alg;
    private String issuer;
    @Autowired
    private ConfigProperties config;


    public String creatJwtToken(UserDetails principal) {
        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(principal.getUsername()).withArrayClaim(AUTH,
                principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new));

        Employee employee = ((HrUser) principal).getEmployee();
        Employee manager = employee.getLeader();
        List<Employee> managedEmployees = employee.getManagedEmployees();

        jwtBuilder.withClaim(FULLNAME, employee.getName());
        jwtBuilder.withClaim(ID, employee.getId());

        if (manager != null) {
            jwtBuilder.withClaim(MANAGER, Map.of(ID, manager.getId(), MANAGER_USERNAME, manager.getName()));
        }

        if (managedEmployees != null && !managedEmployees.isEmpty()) {
            jwtBuilder
                    .withArrayClaim(MANAGED_EMPLOYEE_IDS,
                            managedEmployees.stream().map(Employee::getId).toArray(Long[]::new))
                    .withArrayClaim(MANAGED_EMPLOYEE_USERNAMES,
                            managedEmployees.stream().map(Employee::getUsername).toArray(String[]::new));
        }

        return jwtBuilder.withExpiresAt(new Date(System.currentTimeMillis() + config.getJwt().getDuration().toMillis()))
                .withIssuer(issuer).sign(alg);

    }

    public UserDetails parseJwt(String jwtToken) {

        DecodedJWT decodedJwt = JWT.require(alg).withIssuer(issuer).build().verify(jwtToken);

        Employee employee = new Employee();
        employee.setId(decodedJwt.getClaim(ID).asLong());
        employee.setName(decodedJwt.getClaim(FULLNAME).asString());
        employee.setUsername(decodedJwt.getSubject());
        Claim managerClaim = decodedJwt.getClaim(MANAGER);
        if (managerClaim != null) {
            Map<String, Object> managerData = managerClaim.asMap();
            if (managerData != null) {
                Employee manager = new Employee();
                employee.setLeader(manager);
                manager.setId(((Integer) managerData.get(ID)).longValue());
                manager.setUsername(managerData.get(MANAGER_USERNAME).toString());
            }
        }

        Claim managedEmployeeUsernamesClaim = decodedJwt.getClaim(MANAGED_EMPLOYEE_USERNAMES);
        if (managedEmployeeUsernamesClaim != null) {
            employee.setManagedEmployees(new ArrayList<>());
            List<String> managedEmployeeUsernames = managedEmployeeUsernamesClaim.asList(String.class);
            if (managedEmployeeUsernames != null && !managedEmployeeUsernames.isEmpty()) {
                List<Long> managedEmployeeIds = decodedJwt.getClaim(MANAGED_EMPLOYEE_IDS).asList(Long.class);
                for (int i = 0; i < managedEmployeeIds.size(); i++) {
                    Employee managedEmployee = new Employee();
                    managedEmployee.setUsername(managedEmployeeUsernames.get(i));
                    managedEmployee.setId(managedEmployeeIds.get(i));
                    employee.getManagedEmployees().add(managedEmployee);
                }
            }
        }

        return new HrUser(decodedJwt.getSubject(), "test", decodedJwt.getClaim(AUTH).asList(String.class).stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList()), employee);
    }
}
