package spring.react.jwt.service;

import spring.react.jwt.model.entities.City;
import spring.react.jwt.model.service.CityAddServiceModel;

import java.util.List;

public interface CityService {

    City findCityByName(String name);

    City addCityToDb(CityAddServiceModel cityAddServiceModel);

    List<City>getAllCities();
}
