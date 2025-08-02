package com.example.Meditation.stats;





public class UsageStatsResponse {
    private long totalUsers;
    private long totalSessions;
    private long totalCompletedSessions;

    public UsageStatsResponse(long totalUsers, long totalSessions, long totalCompletedSessions) {
        this.totalUsers = totalUsers;
        this.totalSessions = totalSessions;
        this.totalCompletedSessions = totalCompletedSessions;
    }

    // Getters and Setters
    public long getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }

    public long getTotalSessions() {
        return totalSessions;
    }

    public void setTotalSessions(long totalSessions) {
        this.totalSessions = totalSessions;
    }

    public long getTotalCompletedSessions() {
        return totalCompletedSessions;
    }

    public void setTotalCompletedSessions(long totalCompletedSessions) {
        this.totalCompletedSessions = totalCompletedSessions;
    }
}
