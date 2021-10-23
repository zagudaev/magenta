package ru.example.magenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.magenta.model.City;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City,Long> {

    Optional<City> findByName (String name);


    Optional<City> findByLatitudeAndLongitude( double latitude, double longitude);

    Optional<City> findById (Long id);
}
