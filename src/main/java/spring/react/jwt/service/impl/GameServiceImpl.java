package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.City;
import spring.react.jwt.model.entities.Court;
import spring.react.jwt.model.entities.Game;
import spring.react.jwt.model.entities.Player;
import spring.react.jwt.repositories.ChallengeRepository;
import spring.react.jwt.repositories.CourtRepository;
import spring.react.jwt.repositories.GameRepository;
import spring.react.jwt.repositories.PlayerRepository;
import spring.react.jwt.service.GameService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ChallengeRepository challengeRepository;
    private final PlayerRepository playerRepository;
    private final CourtRepository courtRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ChallengeRepository challengeRepository, PlayerRepository playerRepository, CourtRepository courtRepository) {
        this.gameRepository = gameRepository;
        this.challengeRepository = challengeRepository;
        this.playerRepository = playerRepository;
        this.courtRepository = courtRepository;
    }

    @Override
    public Game findGameByPlayersIdAndNotFinished(Long firstId, Long secondId) {
        return this.gameRepository.findGameByPlayersIdAndNotFinished(firstId, secondId);
    }

    @Override
    public void gameCreate(Long firstPlayerId, Long secondPlayerId) {
        Player playerOne = this.playerRepository.findPlayerById(firstPlayerId);
        Player playerTwo = this.playerRepository.findPlayerById(secondPlayerId);
        City city = playerOne.getCity();
        List<Court>allCourtsInTheCity = this.courtRepository.findAllCourtsByCity(city.getName());
        Court court = allCourtsInTheCity.get(new Random().nextInt(allCourtsInTheCity.size()));
        Game game = new Game();
        game.setFirstPlayer(playerOne);
        game.setSecondPlayer(playerTwo);
        game.setWinnerGames(0);
        game.setLooserGames(0);
        game.setCourt(court);
        game.setFinished(false);
        game.setDateTime(LocalDateTime.now().plusDays(2));

        this.gameRepository.save(game);
    }
}
