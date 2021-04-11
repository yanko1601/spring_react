package spring.react.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.react.jwt.model.entities.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game AS g WHERE (g.firstPlayer.id = :firstId AND g.secondPlayer.id = :secondId AND g.finished = false) OR (g.firstPlayer.id = :secondId AND g.secondPlayer.id = :firstId AND g.finished = false)")
    Game findGameByPlayersIdAndNotFinished(@Param("firstId") Long firstId, @Param("secondId") Long secondId);

    @Query("SELECT g FROM Game AS g WHERE g.finished=false ")
    List<Game> getAllGamesNotFinished();

    @Query("SELECT g FROM Game AS g WHERE g.finished=true")
    List<Game> getAllGamesFinished();

    @Query("SELECT g FROM Game AS g WHERE g.id = :id")
    Game findGameByid(@Param("id") Long id);
}
