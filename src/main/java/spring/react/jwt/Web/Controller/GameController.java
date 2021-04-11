package spring.react.jwt.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.dtos.GameCreateDto;
import spring.react.jwt.model.dtos.ResultSetDto;
import spring.react.jwt.model.entities.Game;
import spring.react.jwt.model.view.GameOutputView;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.service.GameService;

import java.util.List;

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

    @CrossOrigin
    @GetMapping("/getall")
    public List<GameOutputView>getAllGames() {

        return this.gameService.getAllGamesNotFinished();
    }

    @CrossOrigin
    @PostMapping("/setresult")
    public OutputMessageView setResult(@RequestBody ResultSetDto resultSetDto) {

        this.gameService.setResult(resultSetDto.getGameId());

        OutputMessageView message = new OutputMessageView();
        message.setSuccess(true);
        message.setMessage("Резултата е въведен");

        return message;
    }
}
