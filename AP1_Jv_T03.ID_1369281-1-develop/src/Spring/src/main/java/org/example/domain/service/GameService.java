package org.example.domain.service;

import org.example.domain.model.Board;
import org.example.domain.model.Game;

public interface GameService {
    Board makeComputerMove(Game game);
    boolean validateMove(Board oldBoard, Board newBoard);
    Game checkGameStatus(Game game);
}