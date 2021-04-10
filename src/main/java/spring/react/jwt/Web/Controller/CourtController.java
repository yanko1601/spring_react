package spring.react.jwt.Web.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.dtos.CourtAddDto;
import spring.react.jwt.model.service.CourtAddServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.service.CourtService;

@RestController
@RequestMapping("/court")
public class CourtController {

    private final CourtService courtService;
    private final ModelMapper modelMapper;

    @Autowired
    public CourtController(CourtService courtService, ModelMapper modelMapper) {
        this.courtService = courtService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @PostMapping("/add")
    public OutputMessageView addCourt(@RequestBody CourtAddDto courtAddDto) {

        this.courtService.addCourt(this.modelMapper.map(courtAddDto, CourtAddServiceModel.class));

        OutputMessageView outputMessageView = new OutputMessageView();
        outputMessageView.setSuccess(true);
        outputMessageView.setMessage("Добавихте корт");

        return outputMessageView;
    }
}
