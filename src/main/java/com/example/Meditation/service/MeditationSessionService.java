package com.example.Meditation.service;

import com.example.Meditation.models.MeditationSession;
import com.example.Meditation.repository.MeditationSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeditationSessionService {


    @Autowired
    MeditationSessionRepository meditationSessionRepository;

    public List<MeditationSession> getAllSessions() {
        return meditationSessionRepository.findAll();
    }



    public List<MeditationSession> filterByCategory(String category) {
    return meditationSessionRepository.findByCategoryContainingIgnoreCase(category);
    }

    public List<MeditationSession> filterByDuration(int maxMinutes) {
        return meditationSessionRepository.findByDurationInMinutesLessThanEqual(maxMinutes);
    }

    public MeditationSession addSession(MeditationSession session) {
        return meditationSessionRepository.save(session);
    }

    public MeditationSession updateSession(Long id, MeditationSession updated) {
    MeditationSession s=meditationSessionRepository.findById(id).orElseThrow();
    s.setTitle(updated.getTitle());
    s.setDescription(updated.getDescription());
    s.setAudioUrl(updated.getAudioUrl());
    s.setCategory(updated.getCategory());
    s.setDurationInMinutes(updated.getDurationInMinutes());
    return meditationSessionRepository.save(s);
    }

    public void deleteSession(Long id) {
        meditationSessionRepository.deleteById(id);
    }
}
