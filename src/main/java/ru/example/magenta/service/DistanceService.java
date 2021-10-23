package ru.example.magenta.service;

import ru.example.magenta.dto.DistanceDto;
import ru.example.magenta.model.Distance;

/**
 * Service for working with the City model
 */
public interface DistanceService {

    Long create(DistanceDto distanceDTO);

    void update(DistanceDto distanceDTO);

    void delete(Long id);

    DistanceDto findById(Long id);

    Distance findByIdNN(Long id);
}
