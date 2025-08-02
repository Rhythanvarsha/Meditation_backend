package com.example.Meditation.service;

import com.example.Meditation.jwt.JwtTokenProvider;
import com.example.Meditation.models.JwtResponse;
import com.example.Meditation.models.RegisterDetails;
import com.example.Meditation.models.Roles;
import com.example.Meditation.models.UserDetailsDto;
import com.example.Meditation.repository.RegisterDetailsRepository;
//import com.example.springbootfirst.repository.RegisterRepository;
import com.example.Meditation.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthService {

//    @Autowired
//    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisterDetailsRepository regRepo;

    @Autowired
    private RolesRepository roleRepo;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<RegisterDetails> getRegisterDetails() {
        return regRepo.findAll();
    }

    public String addNewUser(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setEmpName(register.getName());
        registerDetails.setUserName(register.getUserName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));

        Set<Roles> roles = new HashSet<>();

        if (register.getRoleName() != null) {
            for (String roleName : register.getRoleName()) {
                Roles role = roleRepo.findByroleName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
        } else {
            throw new RuntimeException("Roles must not be null");
        }

        registerDetails.setRoles(roles);


        regRepo.save(registerDetails);
        return "User registered successfully!";
    }


    public JwtResponse loginUser(RegisterDetails login) {
        Authentication authentication =
                authenticationManager.authenticate(//it calls authenticate method in authenticatemanager interface which gets credentials as argumnt
                        new UsernamePasswordAuthenticationToken(
                                login.getUserName(), login.getPassword()
                        )
                );
        String token = jwtTokenProvider.generateToken(authentication); //23CS124

        // Extract username
        String username = login.getUserName();

        // Extract roles
        List<String> roles = authentication.getAuthorities().stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.toList());

        String joinedRoles = String.join(",", roles);

        return new JwtResponse(token, username, joinedRoles);

    }






    public String deleteEmployeeById(int id) {

        Optional<RegisterDetails> optional = regRepo.findByEmpId(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        RegisterDetails employee = optional.get();
        employee.getRoles().clear();
        regRepo.save(employee);

        regRepo.deleteById(id);
        return "Employee deleted successfully";
    }



    public Optional<RegisterDetails> findByUserByUsername(String userName) {
        return regRepo.findByUserName(userName);
    }

    public Optional<RegisterDetails> getRegisterByIdDetails(int empId) {
    return regRepo.findByEmpId(empId);
    }
}