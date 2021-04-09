package spring.react.jwt.service;

import spring.react.jwt.model.entities.Challenge;

public interface ChallengeService {

    Challenge findChallengeByFirstPlayerChallenged(Long firstId, Long secondId);

    Challenge findChallengeBySecondPlayerChallenged(Long firstId, Long secondId);
}
