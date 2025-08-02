package com.example.Meditation.repository;

import com.example.Meditation.models.MeditationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MeditationHistoryRepository extends JpaRepository<MeditationHistory,Long> {
    List<MeditationHistory> findByUsername(String username);
    long countByUsername(String username);}
