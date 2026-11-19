package org.example.web.controller;

import org.example.datasource.mapper.GameMapper;
import org.example.datasource.repository.GameRepository;
import org.example.domain.model.Game;
import org.example.domain.service.GameService;
import org.example.web.mapper.WebGameMapper;
import org.example.web.model.GameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;
    private final GameRepository repository;
    private final GameMapper datasourceMapper;
    private final WebGameMapper webMapper;

    public GameController(GameService gameService,
                          GameRepository repository,
                          GameMapper datasourceMapper,
                          WebGameMapper webMapper) {
        this.gameService = gameService;
        this.repository = repository;
        this.datasourceMapper = datasourceMapper;
        this.webMapper = webMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<GameDto> newGame() {
        Game game = new Game();
        repository.save(datasourceMapper.toEntity(game));
        return ResponseEntity.ok(webMapper.toDto(game));
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<?> makeMove(@PathVariable UUID gameId, @RequestBody GameDto requestDto) {
        var entityOpt = repository.findById(gameId);
        if (entityOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Game not found with id: " + gameId);
        }

        Game currentGame = datasourceMapper.toDomain(entityOpt.get());
        var newBoardFromUser = webMapper.toDomain(requestDto).getBoard();

        if (!gameService.validateMove(currentGame.getBoard(), newBoardFromUser)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid move detected! Only one 'X' can be placed per turn.");
        }

        currentGame.setBoard(newBoardFromUser);
        currentGame = gameService.checkGameStatus(currentGame);

        if (!currentGame.isGameOver()) {
            currentGame.setBoard(gameService.makeComputerMove(currentGame));
            currentGame = gameService.checkGameStatus(currentGame);
        }

        repository.save(datasourceMapper.toEntity(currentGame));
        GameDto responseDto = webMapper.toDto(currentGame);
        return ResponseEntity.ok(responseDto);
    }
}