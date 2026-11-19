package org.example.web;

import org.example.model.Game;
import org.example.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> createGame(Authentication auth) {
        UUID userId = UUID.fromString(auth.getName());
        return ResponseEntity.ok(gameService.createGame(userId));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Game>> getAvailableGames() {
        return ResponseEntity.ok(gameService.getAvailableGames());
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<Game> joinGame(@PathVariable UUID id, Authentication auth) {
        UUID userId = UUID.fromString(auth.getName());
        return ResponseEntity.ok(gameService.joinGame(id, userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable UUID id) {
        return ResponseEntity.ok(gameService.getGame(id));
    }

    @PostMapping("/{id}/move")
    public ResponseEntity<Game> makeMove(@PathVariable UUID id,
                                         @RequestBody MoveRequest move,
                                         Authentication auth) {
        UUID userId = UUID.fromString(auth.getName());
        return ResponseEntity.ok(gameService.makeMove(id, userId, move.getRow(), move.getCol()));
    }

    static class MoveRequest {
        private int row;
        private int col;
        public int getRow() { return row; }
        public void setRow(int row) { this.row = row; }
        public int getCol() { return col; }
        public void setCol(int col) { this.col = col; }
    }
}