package spring.react.jwt.service;

import spring.react.jwt.model.service.PlayerRegisterServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.model.view.PlayerGetFromDbView;
import spring.react.jwt.model.view.PlayerOutputView;

import java.util.List;

public interface PlayerService {

    OutputMessageView playerRegister(PlayerRegisterServiceModel playerRegisterServiceModel);

    List<PlayerOutputView> getAllPlayers();

    PlayerOutputView getPlayerByEmail(String email);

    PlayerGetFromDbView getPlayerById(Long id);
}
