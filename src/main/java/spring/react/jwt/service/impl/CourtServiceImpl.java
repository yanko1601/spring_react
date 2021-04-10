package spring.react.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.react.jwt.model.entities.City;
import spring.react.jwt.model.entities.Court;
import spring.react.jwt.model.service.CourtAddServiceModel;
import spring.react.jwt.repositories.CityRepository;
import spring.react.jwt.repositories.CourtRepository;
import spring.react.jwt.service.CourtService;

@Service
public class CourtServiceImpl implements CourtService {

    private final CourtRepository courtRepository;
    private final CityRepository cityRepository;

    @Autowired
    public CourtServiceImpl(CourtRepository courtRepository, CityRepository cityRepository) {
        this.courtRepository = courtRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void addCourt(CourtAddServiceModel courtAddServiceModel) {
        Court court = new Court();
        City city = this.cityRepository.findByName(courtAddServiceModel.getCity());

        court.setCity(city);
        court.setComplex(courtAddServiceModel.getComplex());
        court.setCourtNumber(courtAddServiceModel.getNumber());

        this.courtRepository.save(court);
    }
}
