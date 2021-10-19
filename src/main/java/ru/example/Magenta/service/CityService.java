package ru.example.Magenta.service;

import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.model.City;

import java.util.List;
/**
 * Service for working with the City model
 */
public interface CityService {
    Long  create (CityDTO cityDTO);
    void update(CityDTO cityDTO);
    void  delete(Long id);
    CityDTO findById(Long id);
    List<CityDTO> findAll();
}
