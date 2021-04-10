package spring.react.jwt.service;

import spring.react.jwt.model.entities.Game;

public interface GameService {

    Game findGameByPlayersIdAndNotFinished(Long firstId, Long secondId);

    void gameCreate(Long firstPlayerId, Long secondPlayerId);
}
