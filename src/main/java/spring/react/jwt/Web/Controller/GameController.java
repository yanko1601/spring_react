package spring.react.jwt.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.dtos.GameCreateDto;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @CrossOrigin
    @PostMapping("/create")
    public OutputMessageView createGame(@RequestBody GameCreateDto gameCreateDto) {

        this.gameService.gameCreate(gameCreateDto.getFirstPlayerId(), gameCreateDto.getSecondPlayerId());

        OutputMessageView message = new OutputMessageView();
        message.setSuccess(true);
        message.setMessage("Създаден е мач");

        return message;
    }
}
