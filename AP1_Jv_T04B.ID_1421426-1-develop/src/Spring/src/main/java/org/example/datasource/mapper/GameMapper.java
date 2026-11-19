package org.example.datasource.mapper;

import org.example.datasource.model.GameEntity;
import org.example.domain.model.Board;
import org.example.domain.model.Game;

public class GameMapper {

    public GameEntity toEntity(Game domain) {
        GameEntity entity = new GameEntity();
        entity.setId(domain.getId());

        Board domainBoard = domain.getBoard();
        int[][] gridCopy = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(domainBoard.getGrid()[i], 0, gridCopy[i], 0, 3);
        }
        entity.setBoardGrid(gridCopy);
        entity.setGameOver(domain.isGameOver());
        entity.setWinner(domain.getWinner());
        return entity;
    }

    public Game toDomain(GameEntity entity) {
        Game game = new Game();
        game.setId(entity.getId());
        game.setBoard(new Board(entity.getBoardGrid()));
        game.setGameOver(entity.isGameOver());
        game.setWinner(entity.getWinner());
        return game;
    }
}