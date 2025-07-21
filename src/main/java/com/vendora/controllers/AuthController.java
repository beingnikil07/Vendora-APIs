package com.vendora.controllers;

import com.vendora.models.AuthUsers;
import com.vendora.repository.AuthUserRepository;
import com.vendora.services.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private CustomUserDetailsService  authService;

    //To register a user
    @PostMapping(value ="/register",
    consumes = "application/json")
    @Operation(
            summary = "Register the user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully registered"),
                    @ApiResponse(responseCode = "401", description = "please provide correct input")
            }
    )
    public ResponseEntity<String> register(@Valid @RequestBody AuthUsers user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authUserRepository.save(user);
        return ResponseEntity.ok().body("user registered Successfully");
    }


    // To Login a user
    @PostMapping(value = "/login",
    consumes = "application/json")
    @Operation(
            summary = "Login the user for the authorization",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully logged in"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - invalid credentials")
            }
    )

    public ResponseEntity<String> login(@RequestBody AuthUsers user){
        boolean isAuthenticated= authService.authenticateUser(user.getUsername(),user.getPassword());
        if(isAuthenticated){
            return ResponseEntity.ok().body("Successfully logged in");

        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
