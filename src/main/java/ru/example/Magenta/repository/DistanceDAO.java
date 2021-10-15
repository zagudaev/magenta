package ru.example.Magenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.example.Magenta.model.Distance;

import java.util.Optional;

public interface DistanceDAO extends JpaRepository<Distance,Long> {
    @Query("select d from Distance d where d.toCity.name = ?1 and  d.fromCity.name = ?2")
    Optional<Distance> findByToCityAndFromCity (String toCity , String fromCity);
}
