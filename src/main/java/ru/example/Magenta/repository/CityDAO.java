package ru.example.Magenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.Magenta.model.City;

import java.util.Optional;

public interface CityDAO extends JpaRepository<City,Long> {

    Optional<City> findByName (String name);


    Optional<City> findByLatitudeAndLongitude( double latitude, double longitude);

    Optional<City> findById (Long id);
}
