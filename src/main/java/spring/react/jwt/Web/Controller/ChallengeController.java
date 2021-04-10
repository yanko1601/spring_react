package spring.react.jwt.Web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.dtos.ChallengeSetDto;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.service.ChallengeService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @CrossOrigin
    @PostMapping("/setchallenge")
    public OutputMessageView setChallenge(@RequestBody ChallengeSetDto challengeSetDto) {
        this.challengeService.setChallenge(challengeSetDto.getChallengingPlayerId(), challengeSetDto.getChallengedPlayerId());
        OutputMessageView message = new OutputMessageView();
        message.setSuccess(true);
        message.setMessage("Успешно предизвикателство");
        return message;
    }

    @CrossOrigin
    @PostMapping("/decline")
    public OutputMessageView declineChallenge(@RequestBody ChallengeSetDto challengeSetDto) {
        this.challengeService.declineChallenge(challengeSetDto.getChallengingPlayerId(), challengeSetDto.getChallengedPlayerId());
        OutputMessageView message = new OutputMessageView();
        message.setSuccess(true);
        message.setMessage("Предизвикателството е отказано");
        return message;
    }
}
