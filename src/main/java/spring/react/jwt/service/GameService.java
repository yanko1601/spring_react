package spring.react.jwt.service;

import spring.react.jwt.model.entities.Game;
import spring.react.jwt.model.view.GameOutputView;

import java.util.List;

public interface GameService {

    Game findGameByPlayersIdAndNotFinished(Long firstId, Long secondId);

    void gameCreate(Long firstPlayerId, Long secondPlayerId);

    List<GameOutputView> getAllGamesNotFinished();
}
