package ru.example.Magenta.service;

import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.model.City;

public interface CityService {
    Long  create (CityDTO cityDTO);
    void update(CityDTO cityDTO);
    void  delete(Long id);
    CityDTO read(Long id);
}
