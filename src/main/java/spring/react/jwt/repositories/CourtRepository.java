package spring.react.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.react.jwt.model.entities.Court;

import java.util.List;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {

    @Query("SELECT c from Court AS c WHERE c.city.name = :city")
    List<Court> findAllCourtsByCity(@Param("city") String city);
}
