package BadgeAndMembership.controller;

import BadgeAndMembership.service.AuthenticationService;
import contracts.dto.CustomErrorMessage;
import contracts.dto.auth.AuthLoginDto;
import contracts.dto.auth.AuthRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthLoginDto authLoginDto) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(authLoginDto));
        }
        catch (AuthenticationException ex){
            return new ResponseEntity<>(new CustomErrorMessage("Invalid credentials"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRegisterDto authRegisterDto) {
        return ResponseEntity.ok(authenticationService.register(authRegisterDto));
    }

    @GetMapping("/")
    public String test3() {
        return "asd";

    }
}
