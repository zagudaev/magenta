package ru.example.magenta.service;

import ru.example.magenta.dto.CityDto;
import ru.example.magenta.model.City;

import java.util.List;

/**
 * Service for working with the City model
 */
public interface CityService {
    Long create(CityDto cityDTO);

    void update(CityDto cityDTO);

    void delete(Long id);

    CityDto findById(Long id);

    List<CityDto> findAll();

    City findByIdNN(Long id);
}
