package org.example.domain.service;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Board;
import org.example.domain.model.Game;

public class GameServiceImpl implements GameService {

    private final GameRepository repository;
    private final GameMapper mapper;

    public GameServiceImpl(GameRepository repository, GameMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Board makeComputerMove(Game game) {
        Board board = game.getBoard();
        int bestScore = Integer.MIN_VALUE;
        int bestRow = -1;
        int bestCol = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getCell(i, j) == Board.EMPTY) {
                    board.setCell(i, j, Board.PLAYER_O);
                    int score = minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    board.setCell(i, j, Board.EMPTY);

                    if (score > bestScore) {
                        bestScore = score;
                        bestRow = i;
                        bestCol = j;
                    }
                }
            }
        }

        if (bestRow != -1 && bestCol != -1) {
            board.setCell(bestRow, bestCol, Board.PLAYER_O);
        }
        return board;
    }

    private int minimax(Board board, int depth, boolean isMaximizing, int alpha, int beta) {
        int winner = checkWinner(board);
        if (winner == Board.PLAYER_O) return 10 - depth;
        if (winner == Board.PLAYER_X) return depth - 10;
        if (board.isFull()) return 0;

        if (isMaximizing) {
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getCell(i, j) == Board.EMPTY) {
                        board.setCell(i, j, Board.PLAYER_O);
                        int score = minimax(board, depth + 1, false, alpha, beta);
                        board.setCell(i, j, Board.EMPTY);
                        bestScore = Math.max(bestScore, score);
                        alpha = Math.max(alpha, score);
                        if (beta <= alpha) break;
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getCell(i, j) == Board.EMPTY) {
                        board.setCell(i, j, Board.PLAYER_X);
                        int score = minimax(board, depth + 1, true, alpha, beta);
                        board.setCell(i, j, Board.EMPTY);
                        bestScore = Math.min(bestScore, score);
                        beta = Math.min(beta, score);
                        if (beta <= alpha) break;
                    }
                }
            }
            return bestScore;
        }
    }

    private int checkWinner(Board board) {
        int[][] grid = board.getGrid();

        for (int i = 0; i < 3; i++) {
            if (grid[i][0] != 0 && grid[i][0] == grid[i][1] && grid[i][1] == grid[i][2]) {
                return grid[i][0];
            }
            if (grid[0][i] != 0 && grid[0][i] == grid[1][i] && grid[1][i] == grid[2][i]) {
                return grid[0][i];
            }
        }

        if (grid[0][0] != 0 && grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2]) {
            return grid[0][0];
        }
        if (grid[0][2] != 0 && grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0]) {
            return grid[0][2];
        }

        return 0;
    }

    @Override
    public boolean validateMove(Board oldBoard, Board newBoard) {
        int diffCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int oldVal = oldBoard.getCell(i, j);
                int newVal = newBoard.getCell(i, j);

                if (oldVal != newVal) {
                    if (oldVal == Board.EMPTY && newVal == Board.PLAYER_X) {
                        diffCount++;
                    } else {
                        return false;
                    }
                }
            }
        }
        return diffCount == 1;
    }

    @Override
    public Game checkGameStatus(Game game) {
        Board board = game.getBoard();
        int winner = checkWinner(board);

        if (winner == Board.PLAYER_X) {
            game.setGameOver(true);
            game.setWinner("X");
        } else if (winner == Board.PLAYER_O) {
            game.setGameOver(true);
            game.setWinner("O");
        } else if (board.isFull()) {
            game.setGameOver(true);
            game.setWinner("Draw");
        } else {
            game.setGameOver(false);
            game.setWinner(null);
        }

        return game;
    }
}