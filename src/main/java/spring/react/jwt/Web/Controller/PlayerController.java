package spring.react.jwt.Web.Controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.dtos.PlayerRegisterDto;
import spring.react.jwt.model.entities.Player;
import spring.react.jwt.model.service.PlayerRegisterServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.model.view.PlayerForChallengeListView;
import spring.react.jwt.model.view.PlayerGetFromDbView;
import spring.react.jwt.model.view.PlayerOutputView;
import spring.react.jwt.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    private final ModelMapper modelMapper;

    public PlayerController(PlayerService playerService, ModelMapper modelMapper) {
        this.playerService = playerService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<PlayerOutputView> getAllPlayers() {
        return this.playerService.getAllPlayers();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public PlayerGetFromDbView getPlayerById(@PathVariable(value = "id") Long id) {
        return this.playerService.getPlayerById(id);
    }

    @CrossOrigin
    @PostMapping("/register")
    public OutputMessageView outputMessageView(@RequestBody PlayerRegisterDto playerRegisterDto) {
        return this.playerService.playerRegister(this.modelMapper.map(playerRegisterDto, PlayerRegisterServiceModel.class));
    }

    @CrossOrigin
    @GetMapping("/challengeList/{id}")
    public List<PlayerForChallengeListView> getChallengeList (@PathVariable(value = "id") Long id) {
        return this.playerService.getPlayersForChallenge(id);
    }
}
