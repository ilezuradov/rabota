package org.example.web.mapper;

import org.example.domain.model.Board;
import org.example.domain.model.Game;
import org.example.web.model.BoardDto;
import org.example.web.model.GameDto;

public class WebGameMapper {

    public GameDto toDto(Game domain) {
        GameDto dto = new GameDto();
        dto.setId(domain.getId());
        dto.setGameOver(domain.isGameOver());
        dto.setWinner(domain.getWinner());

        BoardDto boardDto = new BoardDto();
        boardDto.setGrid(domain.getBoard().getGrid());
        dto.setBoard(boardDto);

        return dto;
    }

    public Game toDomain(GameDto dto) {
        Game game = new Game();
        game.setId(dto.getId());
        game.setBoard(new Board(dto.getBoard().getGrid()));
        game.setGameOver(dto.isGameOver());
        game.setWinner(dto.getWinner());
        return game;
    }
}