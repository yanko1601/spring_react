package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.Game;
import spring.react.jwt.repositories.GameRepository;
import spring.react.jwt.service.GameService;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game findGameByPlayersIdAndNotFinished(Long firstId, Long secondId) {
        return this.gameRepository.findGameByPlayersIdAndNotFinished(firstId, secondId);
    }
}
