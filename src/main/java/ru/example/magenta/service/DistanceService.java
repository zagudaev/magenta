package ru.example.magenta.service;

import ru.example.magenta.dto.DistanceDTO;

/**
 * Service for working with the City model
 */
public interface DistanceService {

    Long  create (DistanceDTO distanceDTO);
    void update(DistanceDTO distanceDTO);
    void  delete(Long id);
    DistanceDTO findById(Long id);
}
