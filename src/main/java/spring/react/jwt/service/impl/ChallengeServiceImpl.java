package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.Challenge;
import spring.react.jwt.repositories.ChallengeRepository;
import spring.react.jwt.service.ChallengeService;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeServiceImpl(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public Challenge findChallengeByFirstPlayerChallenged(Long firstId, Long secondId) {
        return this.challengeRepository.findChallengeByFirstPlayerChallenged(firstId, secondId);
    }

    @Override
    public Challenge findChallengeBySecondPlayerChallenged(Long firstId, Long secondId) {

        return this.challengeRepository.findChallengeBySecondPlayerChallenged(firstId, secondId);
    }
}
