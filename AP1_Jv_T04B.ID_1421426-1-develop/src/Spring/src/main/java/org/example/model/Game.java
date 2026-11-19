package org.example.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID playerX;
    private UUID playerO;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private String board = "_________";
    private char turn = 'X';

    public Game() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getPlayerX() { return playerX; }
    public void setPlayerX(UUID playerX) { this.playerX = playerX; }

    public UUID getPlayerO() { return playerO; }
    public void setPlayerO(UUID playerO) { this.playerO = playerO; }

    public GameStatus getStatus() { return status; }
    public void setStatus(GameStatus status) { this.status = status; }

    public String getBoard() { return board; }
    public void setBoard(String board) { this.board = board; }

    public char getTurn() { return turn; }
    public void setTurn(char turn) { this.turn = turn; }
}