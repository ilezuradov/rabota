package org.example.datasource.model;

import java.util.UUID;

public class GameEntity {
    private UUID id;
    private int[][] boardGrid;
    private boolean gameOver;
    private String winner;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int[][] getBoardGrid() {
        return boardGrid;
    }

    public void setBoardGrid(int[][] boardGrid) {
        this.boardGrid = boardGrid;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}