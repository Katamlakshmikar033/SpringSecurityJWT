package org.security.springsecurityjwt.service;

import org.security.springsecurityjwt.dto.LoginRequest;
import org.security.springsecurityjwt.dto.RegisterRequest;
import org.security.springsecurityjwt.entity.User;
import org.security.springsecurityjwt.repository.Userrepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final Userrepository userrepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(Userrepository userrepository,
                       PasswordEncoder passwordEncoder,JwtService jwtService){
        this.userrepository=userrepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }

    public String register(RegisterRequest request){
        if(userrepository.findByUsername(request.getUsername()).isPresent()){
            throw new IllegalArgumentException("user already exists");
        }
        User user=new User();
        user.setUsername(request.getUsername());

        // Hash the password before storing it
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole("USER");
         userrepository.save(user);

       return "User registered successfully";
    }

    public String login(LoginRequest request){
        User user = userrepository
                .findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Invalid username or password"));

        if (!passwordEncoder.matches(
                request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException(
                    "Invalid username or password");
        }

        return jwtService.generateToken(user.getUsername());
    }
}
