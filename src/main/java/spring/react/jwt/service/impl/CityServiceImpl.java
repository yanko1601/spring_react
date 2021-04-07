package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.City;
import spring.react.jwt.model.service.CityAddServiceModel;
import spring.react.jwt.repositories.CityRepository;
import spring.react.jwt.service.CityService;

import java.util.List;


@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findCityByName(String name) {
        return this.cityRepository.findByName(name);
    }

    @Override
    public City addCityToDb(CityAddServiceModel cityAddServiceModel) {
        City newCity = new City();
        newCity.setName(cityAddServiceModel.getName());
        return this.cityRepository.save(newCity);
    }

    @Override
    public List<City> getAllCities() {
        return this.cityRepository.findAll();
    }
}
