package org.example.repository;

import org.example.model.Game;
import org.example.model.GameStatus;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
    List<Game> findByStatus(GameStatus status);
}