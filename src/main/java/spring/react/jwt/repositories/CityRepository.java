package spring.react.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.react.jwt.model.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);
}
