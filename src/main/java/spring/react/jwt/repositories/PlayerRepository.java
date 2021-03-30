package spring.react.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.react.jwt.model.entities.Player;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player AS p WHERE p.email = :email")
    Optional<Player> findByEmail(@Param(value = "email") String email);

    @Query("SELECT p FROM Player AS p WHERE p.id = :id")
    Player findPlayerById(@Param(value = "id") Long id);

    @Query("SELECT p FROM Player as p WHERE p.city = :city ORDER BY p.rank")
    List<Player> getAllPlayersByCity(@Param(value = "city") String city);

    @Query("SELECT p FROM Player AS p")
    List<Player>getAllPlayers();
}
