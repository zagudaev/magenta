package ru.example.Magenta.service;

import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.model.City;

public interface DistanceService {

    Long  create (DistanceDTO distanceDTO);
    void update(DistanceDTO distanceDTO);
    void  delete(Long id);
    DistanceDTO findById(Long id);
}
