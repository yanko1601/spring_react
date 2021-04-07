package spring.react.jwt.Web.Controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.react.jwt.model.view.CityOutputView;
import spring.react.jwt.service.CityService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;
    private final ModelMapper modelMapper;

    @Autowired
    public CityController(CityService cityService, ModelMapper modelMapper) {
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping("/getAll")
    public List<CityOutputView>getAllCities() {
        List<CityOutputView>allCities = new ArrayList<>();

        this.cityService.getAllCities().forEach(c -> {
            allCities.add(this.modelMapper.map(c, CityOutputView.class));
        });

        return allCities;
    }
}
