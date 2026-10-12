package org.security.springsecurityjwt.controller;

import org.security.springsecurityjwt.dto.LoginRequest;
import org.security.springsecurityjwt.dto.RegisterRequest;
import org.security.springsecurityjwt.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService=authService;
    }
     @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        String message=authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/login")
 public ResponseEntity login(@RequestBody LoginRequest request){
        String token = authService.login(request);
        return ResponseEntity.ok(token);

 }

}
