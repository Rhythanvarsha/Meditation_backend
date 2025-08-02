package com.example.Meditation.repository;

import com.example.Meditation.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

import java.util.Optional;

public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails, Integer> {
    Optional<RegisterDetails> findByUserName(String userName);
    Optional<RegisterDetails> findByEmpId(int empid);

}
