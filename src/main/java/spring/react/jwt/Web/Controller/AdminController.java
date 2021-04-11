package spring.react.jwt.Web.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.dtos.CityAddDto;
import spring.react.jwt.model.service.CityAddServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.repositories.ChallengeRepository;
import spring.react.jwt.repositories.GameRepository;
import spring.react.jwt.service.CityService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CityService cityService;
    private final ModelMapper modelMapper;
    private final ChallengeRepository challengeRepository;
    private final GameRepository gameRepository;

    @Autowired
    public AdminController(CityService cityService, ModelMapper modelMapper, ChallengeRepository challengeRepository, GameRepository gameRepository) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
        this.challengeRepository = challengeRepository;
        this.gameRepository = gameRepository;
    }

    @CrossOrigin
    @PostMapping("/city/add")
    public OutputMessageView addCity(@RequestBody CityAddDto city) {
        OutputMessageView outputMessageView = new OutputMessageView();
        if(this.cityService.findCityByName(city.getName()) != null) {
            outputMessageView.setSuccess(false);
            outputMessageView.setMessage("Този град вече съществува");
        }else {
            this.cityService.addCityToDb(this.modelMapper.map(city, CityAddServiceModel.class));
            outputMessageView.setSuccess(true);
            outputMessageView.setMessage(String.format("Добавихте град %s", city));
        }

        return outputMessageView;
    }

    @CrossOrigin
    @GetMapping("/endofleague")
    public void endOfLeague() {
        this.challengeRepository.deleteAll();
        this.gameRepository.deleteAll();
    }
}
