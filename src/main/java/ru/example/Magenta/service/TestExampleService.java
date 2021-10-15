package ru.example.Magenta.service;

import org.springframework.http.ResponseEntity;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.util.CalculationType;

import java.io.File;
import java.util.List;

public interface TestExampleService {
    double crowflight(CityDTO fromCity, CityDTO toCity);

    double distanceMatrix(CityDTO fromCity, CityDTO toCity);

    List<CityDTO> findAll();

    List<String> CalculateDistance(CalculationType calculationType, List<CityDTO> fromCityList, List<CityDTO> toCityList);

    ResponseEntity<Object> addXLMinDB(File file);
}