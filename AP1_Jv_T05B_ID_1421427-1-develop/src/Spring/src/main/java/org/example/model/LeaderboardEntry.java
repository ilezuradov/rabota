package org.example.model;

import java.util.UUID;

public class LeaderboardEntry {
    private UUID userId;
    private String login;
    private double winRate;

    public LeaderboardEntry(UUID userId, String login, double winRate) {
        this.userId = userId;
        this.login = login;
        this.winRate = winRate;
    }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public double getWinRate() { return winRate; }
    public void setWinRate(double winRate) { this.winRate = winRate; }
}
