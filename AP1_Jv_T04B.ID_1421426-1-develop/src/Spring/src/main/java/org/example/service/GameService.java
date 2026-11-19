package org.example.service;

import org.example.model.Game;
import org.example.model.GameStatus;
import org.example.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game createGame(UUID userId) {
        Game game = new Game();
        game.setPlayerX(userId);
        game.setStatus(GameStatus.WAITING_FOR_PLAYER);
        return gameRepository.save(game);
    }

    public List<Game> getAvailableGames() {
        return gameRepository.findByStatus(GameStatus.WAITING_FOR_PLAYER);
    }

    public Game joinGame(UUID gameId, UUID userId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        if (game.getStatus() != GameStatus.WAITING_FOR_PLAYER) {
            throw new RuntimeException("Cannot join this game");
        }
        game.setPlayerO(userId);
        game.setStatus(GameStatus.IN_PROGRESS);
        return gameRepository.save(game);
    }

    public Game getGame(UUID gameId) {
        return gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));
    }

    public Game makeMove(UUID gameId, UUID userId, int row, int col) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        char playerSymbol;
        if (userId.equals(game.getPlayerX()) && game.getTurn() == 'X') {
            playerSymbol = 'X';
        } else if (userId.equals(game.getPlayerO()) && game.getTurn() == 'O') {
            playerSymbol = 'O';
        } else {
            throw new RuntimeException("Not your turn");
        }

        char[] board = game.getBoard().toCharArray();
        int pos = row * 3 + col;
        if (board[pos] != '_') {
            throw new RuntimeException("Cell already taken");
        }
        board[pos] = playerSymbol;
        game.setBoard(new String(board));

        if (checkWin(board, playerSymbol)) {
            game.setStatus(playerSymbol == 'X' ? GameStatus.WIN_X : GameStatus.WIN_O);
        } else if (!game.getBoard().contains("_")) {
            game.setStatus(GameStatus.DRAW);
        } else {
            game.setTurn(game.getTurn() == 'X' ? 'O' : 'X');
        }

        return gameRepository.save(game);
    }

    private boolean checkWin(char[] board, char s) {
        int[][] wins = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] w : wins) {
            if (board[w[0]] == s && board[w[1]] == s && board[w[2]] == s) {
                return true;
            }
        }
        return false;
    }
}
