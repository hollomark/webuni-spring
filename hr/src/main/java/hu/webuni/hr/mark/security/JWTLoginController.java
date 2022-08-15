package hu.webuni.hr.mark.security;

import hu.webuni.hr.mark.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class JWTLoginController {
    @Autowired
    AuthenticationManager autheticationManager;

    @Autowired
    JWTService jwtService;


    @PostMapping("/api/login")
    public String login(@RequestBody LoginDto login) {

        Authentication authentication = autheticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        return jwtService.creatJwtToken((UserDetails)authentication.getPrincipal());
    }
}
