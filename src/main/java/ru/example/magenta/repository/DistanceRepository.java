package ru.example.magenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.example.magenta.model.Distance;

import java.util.Optional;

public interface DistanceRepository extends JpaRepository<Distance,Long> {
    @Query("select d from Distance d where d.toCity.name = :toCity and  d.fromCity.name = :fromCity")
    Optional<Distance> findByToCityAndFromCity (@Param("toCity") String toCity ,@Param("fromCity") String fromCity);
}
