package spring.react.jwt.Web.Controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.dtos.PlayerRegisterDto;
import spring.react.jwt.model.service.PlayerRegisterServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
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

//    @CrossOrigin
//    @GetMapping("/loggedPlayer")
//    public PlayerOutputView getPlayerFromDbView() {
//        String secretKey = "securitysecuritysecuritysecurity";
//
//        String token = FeignClientInterceptor.getBearerTokenHeader();
//        String email = Jwts.parser()
//                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
//                .parseClaimsJws(token.replace("Bearer ", ""))
//                .getBody()
//                .getSubject();
//
//        return this.playerService.getPlayerByEmail(email);
//    }

    @CrossOrigin
    @PostMapping("/register")
    public OutputMessageView outputMessageView(@RequestBody PlayerRegisterDto playerRegisterDto) {
        return this.playerService.playerRegister(this.modelMapper.map(playerRegisterDto, PlayerRegisterServiceModel.class));
    }
}
