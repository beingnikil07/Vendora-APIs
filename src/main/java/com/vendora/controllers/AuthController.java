package com.vendora.controllers;

import com.vendora.models.AuthUsers;
import com.vendora.repository.AuthUserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name="register/login controller",description ="Register the user for authentication")
public class AuthController {

    @Autowired
    private AuthUserRepository  authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping(value ="/register",
    consumes = "application/json")
    @Operation(summary ="Register the user for the authentication")
    public ResponseEntity<String> register(@RequestBody AuthUsers user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authUserRepository.save(user);
        return ResponseEntity.ok().body("user registered Successfully");
    }
}
