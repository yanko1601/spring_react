package spring.react.jwt.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.Player;
import spring.react.jwt.model.entities.Role;
import spring.react.jwt.model.service.PlayerRegisterServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.model.view.PlayerGetFromDbView;
import spring.react.jwt.model.view.PlayerOutputView;
import spring.react.jwt.repositories.PlayerRepository;
import spring.react.jwt.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
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

            if(this.playerRepository.getAllPlayersByCity(playerRegisterServiceModel.getCity()).size() < 1) {
                player.setRole(Role.ADMIN);
                player.setRank(1);
                player.setPassword(this.passwordEncoder.encode(playerRegisterServiceModel.getPassword()));

                outputMessageView.setSuccess(true);
                outputMessageView.setMessage("Успешно се регистрира играч " + player.getName() + " " + player.getLastName());

                this.playerRepository.save(player);

            } else {
                player.setRole(Role.USER);
                player.setRank(this.playerRepository.getAllPlayersByCity(player.getCity()).size() + 1);
                player.setPassword(this.passwordEncoder.encode(playerRegisterServiceModel.getPassword()));

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
}
