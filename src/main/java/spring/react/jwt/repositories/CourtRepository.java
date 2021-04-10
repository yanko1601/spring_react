package spring.react.jwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.react.jwt.model.entities.Court;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {


}
