package spring.react.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.react.jwt.model.entities.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    @Query("select c FROM Challenge AS c WHERE c.challangedPlayer.id = :firstId AND c.challangingPlayer.id = :secondId AND c.active = true")
    Challenge findChallengeByFirstPlayerChallenged(@Param("firstId") Long firstId, @Param("secondId") Long secondId);

    @Query("select c FROM Challenge AS c WHERE c.challangingPlayer.id = :firstId AND c.challangedPlayer.id = :secondId AND c.active = true")
    Challenge findChallengeBySecondPlayerChallenged(@Param("firstId") Long firstId, @Param("secondId") Long secondId);
}
