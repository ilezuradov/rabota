package org.example.repository;

import org.example.model.Game;
import org.example.model.GameStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface GameRepository extends CrudRepository<Game, UUID> {
    List<Game> findByStatus(GameStatus status);

    @Query("SELECT g FROM Game g WHERE (g.playerX = :userId OR g.playerO = :userId) " +
           "AND (g.status = 'WIN_X' OR g.status = 'WIN_O' OR g.status = 'DRAW')")
    List<Game> findCompletedGamesByUserId(@Param("userId") UUID userId);

    @Query(value = "SELECT u.id, u.login, " +
           "COALESCE(SUM(CASE WHEN (g.playerX = u.id AND g.status = 'WIN_X') OR " +
           "(g.playerO = u.id AND g.status = 'WIN_O') THEN 1 ELSE 0 END) * 100.0 / " +
           "NULLIF(COUNT(g.id), 0), 0) as win_rate " +
           "FROM users u " +
           "LEFT JOIN games g ON (g.playerX = u.id OR g.playerO = u.id) " +
           "AND g.status IN ('WIN_X', 'WIN_O', 'DRAW') " +
           "GROUP BY u.id, u.login " +
           "ORDER BY win_rate DESC LIMIT :n", nativeQuery = true)
    List<Object[]> findTopPlayers(@Param("n") int n);
}
