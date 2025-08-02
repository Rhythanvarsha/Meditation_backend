package com.example.Meditation.repository;

import com.example.Meditation.models.MeditationSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MeditationSessionRepository extends JpaRepository<MeditationSession,Long> {
List<MeditationSession> findByCategoryContainingIgnoreCase(String category);
List<MeditationSession> findByDurationInMinutesLessThanEqual(int duration);

}
