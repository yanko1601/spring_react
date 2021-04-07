package spring.react.jwt.Web.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.react.jwt.model.dtos.CityAddDto;
import spring.react.jwt.model.service.CityAddServiceModel;
import spring.react.jwt.model.view.OutputMessageView;
import spring.react.jwt.service.CityService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final CityService cityService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

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
}
