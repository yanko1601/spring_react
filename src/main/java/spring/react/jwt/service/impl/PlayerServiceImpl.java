package spring.react.jwt.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.City;
import spring.react.jwt.model.entities.Player;
import spring.react.jwt.model.entities.Role;
import spring.react.jwt.model.service.CityAddServiceModel;
import spring.react.jwt.model.service.PlayerRegisterServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.model.view.PlayerForChallengeListView;
import spring.react.jwt.model.view.PlayerGetFromDbView;
import spring.react.jwt.model.view.PlayerOutputView;
import spring.react.jwt.repositories.PlayerRepository;
import spring.react.jwt.service.ChallengeService;
import spring.react.jwt.service.CityService;
import spring.react.jwt.service.GameService;
import spring.react.jwt.service.PlayerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CityService cityService;
    private final ChallengeService challengeService;
    private final GameService gameService;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CityService cityService, ChallengeService challengeService, GameService gameService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.cityService = cityService;
        this.challengeService = challengeService;
        this.gameService = gameService;
    }


    @Override
    public OutputMessageView playerRegister(PlayerRegisterServiceModel playerRegisterServiceModel) {

        OutputMessageView outputMessageView = new OutputMessageView();

        if(!playerRegisterServiceModel.getPassword().equals(playerRegisterServiceModel.getConfirmPassword())) {
            outputMessageView.setSuccess(false);
            outputMessageView.setMessage("Двете пароли не съвпадат");
        } else if(this.playerRepository.findByEmail(playerRegisterServiceModel.getEmail()).isPresent()) {
            outputMessageView.setSuccess(false);
            outputMessageView.setMessage("Съществува потребител с този мейл");
        } else {

            Player player = this.modelMapper.map(playerRegisterServiceModel, Player.class);

            City city = this.cityService.findCityByName(playerRegisterServiceModel.getCity());
            if(city != null) {
                player.setCity(city);
            }else {
                this.cityService.addCityToDb(new CityAddServiceModel(playerRegisterServiceModel.getCity()));
                player.setCity(this.cityService.findCityByName(""));
            }

            if(this.playerRepository.getAllPlayersByCity(playerRegisterServiceModel.getCity()).size() < 1) {
                player.setRole(Role.ADMIN);
                player.setRank(1);
                player.setPassword(this.passwordEncoder.encode(playerRegisterServiceModel.getPassword()));
                player.setPoints(0);

                outputMessageView.setSuccess(true);
                outputMessageView.setMessage("Успешно се регистрира играч " + player.getName() + " " + player.getLastName());

                this.playerRepository.save(player);

            } else {
                player.setRole(Role.USER);

                player.setRank(this.playerRepository.getAllPlayersByCity(player.getCity().getName()).size() + 1);
                player.setPassword(this.passwordEncoder.encode(playerRegisterServiceModel.getPassword()));
                player.setPoints(0);

                outputMessageView.setSuccess(true);
                outputMessageView.setMessage("Успешно се регистрира играч " + player.getName() + " " + player.getLastName());

                this.playerRepository.save(player);
            }
        }
        return outputMessageView;
    }

    @Override
    public List<PlayerOutputView> getAllPlayers() {
        List<PlayerOutputView>allPlayers = new ArrayList<>();
        List<Player>allPlayerFromDb = this.playerRepository.getAllPlayers();

        allPlayerFromDb.forEach(pl -> {
            allPlayers.add(this.modelMapper.map(pl, PlayerOutputView.class));
        });

        return allPlayers;
    }

    @Override
    public PlayerOutputView getPlayerByEmail(String email) {
        Player player = this.playerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", email)));

        return this.modelMapper.map(player, PlayerOutputView.class);
    }

    @Override
    public PlayerGetFromDbView getPlayerById(Long id) {
        Player player = this.playerRepository.findPlayerById(id);

        return this.modelMapper.map(player, PlayerGetFromDbView.class);
    }

    @Override
    public Player findPlayerById(Long id) {

        return this.playerRepository.findPlayerById(id);
    }

    @Override
    public List<Player> getAllPlayersFromCity(String city) {

        return this.playerRepository.getAllPlayersByCity(city);
    }

    @Override
    public List<PlayerForChallengeListView> getPlayersForChallenge(Long id) {

        Player player = this.playerRepository.findPlayerById(id);

        List<Player>playersFromCity = this.getAllPlayersFromCity(player.getCity().getName())
                .stream()
                .sorted((a, b) -> {return a.getRank()-b.getRank();})
                .collect(Collectors.toList());

        List<PlayerForChallengeListView>challengeList = new ArrayList<>();
        int rank = player.getRank();
        
        if (rank == 1) {
            for ( int i=1; i<=5; i++) {

                PlayerForChallengeListView pl = new PlayerForChallengeListView();
                pl.setId(playersFromCity.get(i).getId());
                pl.setRank(playersFromCity.get(i).getRank());
                pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                setbooleansForPlayer(pl, id, pl.getId());
                challengeList.add(pl);
            }
        }else if (rank == playersFromCity.size()) {
            for (int i=playersFromCity.size()-6; i<=playersFromCity.size()-2; i++) {

                PlayerForChallengeListView pl = new PlayerForChallengeListView();
                pl.setId(playersFromCity.get(i).getId());
                pl.setRank(playersFromCity.get(i).getRank());
                pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                setbooleansForPlayer(pl, id, pl.getId());
                challengeList.add(pl);
            }

            
        }else if (rank < playersFromCity.size() && rank > playersFromCity.size() - 5) {
            for (int i=rank-6; i<=rank-2; i++) {
                if (i >= 0) {
                    PlayerForChallengeListView pl = new PlayerForChallengeListView();
                    pl.setId(playersFromCity.get(i).getId());
                    pl.setRank(playersFromCity.get(i).getRank());
                    pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                    setbooleansForPlayer(pl, id, pl.getId());
                    challengeList.add(pl);
                }
            }

            for (int i= rank; i<= playersFromCity.size()-1; i++) {

                PlayerForChallengeListView pl = new PlayerForChallengeListView();
                pl.setId(playersFromCity.get(i).getId());
                pl.setRank(playersFromCity.get(i).getRank());
                pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                setbooleansForPlayer(pl, id, pl.getId());
                challengeList.add(pl);
            }
            
        } else if (rank > 1 && rank <= 5) {
            for (int i=0; i<rank-1; i++) {
                PlayerForChallengeListView pl = new PlayerForChallengeListView();
                pl.setId(playersFromCity.get(i).getId());
                pl.setRank(playersFromCity.get(i).getRank());
                pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                setbooleansForPlayer(pl, id, pl.getId());
                challengeList.add(pl);
            }

            for (int i=rank; i<=rank+4; i++) {
                if (i < playersFromCity.size()) {
                    PlayerForChallengeListView pl = new PlayerForChallengeListView();
                    pl.setId(playersFromCity.get(i).getId());
                    pl.setRank(playersFromCity.get(i).getRank());
                    pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                    setbooleansForPlayer(pl, id, pl.getId());
                    challengeList.add(pl);
                }
            }
            
        }else {
            for (int i=rank-6; i<=rank-2; i++) {
                PlayerForChallengeListView pl = new PlayerForChallengeListView();
                pl.setId(playersFromCity.get(i).getId());
                pl.setRank(playersFromCity.get(i).getRank());
                pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                setbooleansForPlayer(pl, id, pl.getId());
                challengeList.add(pl);
            }

            for (int i=rank; i<= rank+4; i++) {
                PlayerForChallengeListView pl = new PlayerForChallengeListView();
                pl.setId(playersFromCity.get(i).getId());
                pl.setRank(playersFromCity.get(i).getRank());
                pl.setFullName(String.format("%s %s", playersFromCity.get(i).getName(), playersFromCity.get(i).getLastName()));
                setbooleansForPlayer(pl, id, pl.getId());
                challengeList.add(pl);
            }
        }

        return challengeList;
    }

    private void setbooleansForPlayer(PlayerForChallengeListView pl, Long id, Long id1) {
        if (playersHaveMatch(id, pl.getId())) {
            pl.setHaveMatch(true);
            pl.setChallenged(false);
            pl.setChallengedYou(false);
        }else if (isPlayerChallengedMe(id, pl.getId())) {
            pl.setHaveMatch(false);
            pl.setChallengedYou(true);
            pl.setChallenged(false);
        }else if (isPlayerChallengedByMe(id, pl.getId())) {
            pl.setHaveMatch(false);
            pl.setChallengedYou(false);
            pl.setChallenged(true);
        } else {
            pl.setHaveMatch(false);
            pl.setChallengedYou(false);
            pl.setChallenged(false);
        }
    }

    private boolean playersHaveMatch(Long firstId, Long secondId) {
        if (this.gameService.findGameByPlayersIdAndNotFinished(firstId, secondId) == null) {
            return false;
        }else {
            return true;
        }
    }

    private boolean isPlayerChallengedMe(Long firstId, Long secondId) {
        return this.challengeService.findChallengeByFirstPlayerChallenged(firstId, secondId) != null;
    }

    private boolean isPlayerChallengedByMe(Long firstId, Long secondId) {
        return this.challengeService.findChallengeBySecondPlayerChallenged(firstId, secondId) != null;
    }
}
