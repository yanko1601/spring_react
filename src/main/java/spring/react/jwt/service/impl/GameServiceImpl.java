package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.City;
import spring.react.jwt.model.entities.Court;
import spring.react.jwt.model.entities.Game;
import spring.react.jwt.model.entities.Player;
import spring.react.jwt.model.view.GameOutputView;
import spring.react.jwt.repositories.ChallengeRepository;
import spring.react.jwt.repositories.CourtRepository;
import spring.react.jwt.repositories.GameRepository;
import spring.react.jwt.repositories.PlayerRepository;
import spring.react.jwt.service.GameService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

    @Override
    public List<GameOutputView> getAllGamesNotFinished() {
        List<Game>allGames = this.gameRepository.getAllGamesNotFinished();
        List<GameOutputView>resultGames = new ArrayList<>();

        allGames.forEach(g -> {
            GameOutputView outputGame = new GameOutputView();
            outputGame.setId(g.getId());
            outputGame.setFirstPlayerFullName(String.format("%s %s", g.getFirstPlayer().getName(), g.getFirstPlayer().getLastName()));
            outputGame.setSecondPlayerFullName(String.format("%s %s", g.getSecondPlayer().getName(), g.getSecondPlayer().getLastName()));
            outputGame.setCity(g.getFirstPlayer().getCity().getName());
            outputGame.setPlace(String.format("%s - %d", g.getCourt().getComplex(), g.getCourt().getCourtNumber()));
            outputGame.setTime(String.format("%d-%d-%d %d:00"
                                                ,g.getDateTime().getDayOfMonth()
                                                ,g.getDateTime().getMonthValue()
                                                ,g.getDateTime().getYear()
                                                ,g.getDateTime().getHour()));
            resultGames.add(outputGame);
        });
        return resultGames;
    }

    @Override
    public void setResult(Long id) {

        Game game = this.gameRepository.findGameByid(id);
        if(new Random().nextInt(2) > 0) {
            game.setWinner(game.getSecondPlayer());
            game.setLooser(game.getFirstPlayer());
            game.setWinnerGames(6);
            game.setLooserGames(new Random().nextInt(5));
            game.setFinished(true);
            handleWinner(game.getSecondPlayer(), game.getFirstPlayer());
        }else {
            game.setWinner(game.getFirstPlayer());
            game.setLooser(game.getSecondPlayer());
            game.setWinnerGames(6);
            game.setLooserGames(new Random().nextInt(5));
            game.setFinished(true);
            handleWinner(game.getFirstPlayer(), game.getSecondPlayer());
        }

        this.gameRepository.save(game);
    }

    private void handleWinner(Player winner, Player looser) {
        if(winner.getRank() > looser.getRank()) {
            int temp = winner.getRank();
            winner.setRank(looser.getRank());
            looser.setRank(temp);
            this.playerRepository.save(winner);
            this.playerRepository.save(looser);
        }
    }
}
