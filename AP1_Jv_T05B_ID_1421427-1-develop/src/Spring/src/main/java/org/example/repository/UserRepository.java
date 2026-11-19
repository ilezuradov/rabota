package org.example.repository;

import org.example.model.GameUser;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<GameUser, UUID> {
    Optional<GameUser> findByLogin(String login);
    boolean existsByLogin(String login);
}