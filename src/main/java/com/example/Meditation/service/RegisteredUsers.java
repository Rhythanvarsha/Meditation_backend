package com.example.Meditation.service;




import com.example.Meditation.models.RegisterDetails;
//import com.example.springbootfirst.repository.EmployeeRepository;
import com.example.Meditation.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegisteredUsers {

    @Autowired
    RegisterDetailsRepository regRepo;




    public List<RegisterDetails> getAllEmployees(){
        return regRepo.findAll  ();
    }

    public Optional<RegisterDetails> getEmployeeById(int id){
        return regRepo.findByEmpId(id);
    }

    public String addEmployee(RegisterDetails registerDetails){
        regRepo.save(registerDetails);
        return "Employee added successfully";
    }

    public String updateEmployee(int id , RegisterDetails registerDetails){
        regRepo.save(registerDetails);
        return "Employee updated successfully";
    }

    public String deleteEmployeeById(int id) {

        Optional<RegisterDetails> optional = regRepo.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Employee not found with id: " + id);
        }

        RegisterDetails employee = optional.get();
        employee.getRoles().clear();
        regRepo.save(employee);

        regRepo.deleteById(id);
        return "Employee deleted successfully";
    }

    public String deleteEmployees(){
        regRepo.deleteAll();
        return "All employee deleted successfully";
    }
}
