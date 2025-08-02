package com.example.Meditation.repository;

import com.example.Meditation.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {

    Optional<Roles> findByroleName(String roleName);
}
