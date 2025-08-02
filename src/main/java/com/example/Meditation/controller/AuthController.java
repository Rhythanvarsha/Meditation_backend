package com.example.Meditation.controller;

import com.example.Meditation.models.JwtResponse;
import com.example.Meditation.models.RegisterDetails;
import com.example.Meditation.models.UserDetailsDto;
import com.example.Meditation.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{empId}")
    public Optional<RegisterDetails> getByEmpIdRegisterDetails(@PathVariable int empId){
        return authService.getRegisterByIdDetails(empId);
    }


    @GetMapping
    public List<RegisterDetails> getRegisterDetails(){
        return authService.getRegisterDetails();
    }


    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDetailsDto register) {
        return authService.addNewUser(register);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody RegisterDetails login) {    //23CS124
        return authService.loginUser(login);
    }




    @GetMapping("/me")
    public RegisterDetails getCurrentUser(Authentication authentication) {
        String username = authentication.getName(); // Extracted from JWT by Spring Security
        return authService.findByUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }



    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/id/{id}")
    public String deleteEmployeeById(@PathVariable int id){
        return authService.deleteEmployeeById(id);
    }

    public String route() {
        return "hi";
    }
}


