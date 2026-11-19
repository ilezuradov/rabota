package org.example.datasource.repository;

import org.example.datasource.model.GameEntity;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameRepository {
    private final Map<UUID, GameEntity> storage = new ConcurrentHashMap<>();

    public void save(GameEntity game) {
        storage.put(game.getId(), game);
    }

    public Optional<GameEntity> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }
}