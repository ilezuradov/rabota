package org.example.domain.model;

import java.util.UUID;

public class Game {
    private UUID id;
    private Board board;
    private boolean gameOver;
    private String winner;

    public Game() {
        this.id = UUID.randomUUID();
        this.board = new Board();
        this.gameOver = false;
        this.winner = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
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