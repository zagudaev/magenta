package ru.example.magenta.service;

import ru.example.magenta.dto.CityDTO;

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
