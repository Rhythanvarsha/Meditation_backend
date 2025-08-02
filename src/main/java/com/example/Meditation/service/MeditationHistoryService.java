package com.example.Meditation.service;

import com.example.Meditation.models.MeditationHistory;
import com.example.Meditation.models.MeditationSession;
import com.example.Meditation.repository.MeditationHistoryRepository;
import com.example.Meditation.repository.MeditationSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MeditationHistoryService {

    @Autowired
    private MeditationHistoryRepository historyRepository;

    @Autowired
    private MeditationSessionRepository sessionRepository;

    public MeditationHistory saveSession(String username, Long sessionId) {
        MeditationSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        MeditationHistory history = new MeditationHistory();
        history.setUsername(username);
        history.setSession(session);
        history.setDateCompleted(LocalDate.now());

        return historyRepository.save(history);
    }

    public List<MeditationHistory> getUserHistory(String username) {
        return historyRepository.findByUsername(username);
    }

    public long getCompletedCount(String username) {
        return historyRepository.countByUsername(username);
    }

    public List<MeditationHistory> getService() {
        return historyRepository.findAll();
    }
}
