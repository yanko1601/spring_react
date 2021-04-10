package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.Challenge;
import spring.react.jwt.repositories.ChallengeRepository;
import spring.react.jwt.repositories.PlayerRepository;
import spring.react.jwt.service.ChallengeService;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public ChallengeServiceImpl(ChallengeRepository challengeRepository, PlayerRepository playerRepository) {
        this.challengeRepository = challengeRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Challenge findChallengeByFirstPlayerChallenged(Long firstId, Long secondId) {
        return this.challengeRepository.findChallengeByFirstPlayerChallenged(firstId, secondId);
    }

    @Override
    public Challenge findChallengeBySecondPlayerChallenged(Long firstId, Long secondId) {

        return this.challengeRepository.findChallengeBySecondPlayerChallenged(firstId, secondId);
    }

    @Override
    public void setChallenge(Long challengingPlayerId, Long challengedPlayerId) {
        Challenge challenge = new Challenge();
        challenge.setChallangingPlayer(this.playerRepository.findPlayerById(challengingPlayerId));
        challenge.setChallangedPlayer(this.playerRepository.findPlayerById(challengedPlayerId));
        challenge.setActive(true);

        this.challengeRepository.save(challenge);
    }
}
