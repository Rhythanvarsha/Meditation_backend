package com.example.Meditation.service;

import com.example.Meditation.repository.MeditationHistoryRepository;
import com.example.Meditation.repository.MeditationSessionRepository;
import com.example.Meditation.repository.RegisterDetailsRepository;
import com.example.Meditation.stats.UsageStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsageStatsService {

    @Autowired
    private RegisterDetailsRepository userRepository;

    @Autowired
    private MeditationSessionRepository sessionRepository;

    @Autowired
    private MeditationHistoryRepository historyRepository;

    public UsageStatsResponse getUsageStats() {
        long totalUsers = userRepository.count();
        long totalSessions = sessionRepository.count();
        long totalCompletions = historyRepository.count();

        return new UsageStatsResponse(totalUsers, totalSessions, totalCompletions);
    }}