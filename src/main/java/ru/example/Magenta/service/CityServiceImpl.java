package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.exceptions.CityException;
import ru.example.Magenta.model.City;
import ru.example.Magenta.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.example.Magenta.exceptions.CityException.*;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    @Transactional
    public Long create(CityDTO cityDTO) {
        coordinatesAreBusy(cityDTO,cityRepository);
        City city = cityDTO.toCity();
        return  cityRepository.save(city).getId();
    }

    @Override
    @Transactional
    public void update(CityDTO cityDTO) {
        City city = cityRepository.findById(cityDTO.getId()).orElse(null);
        cityIDNotFound(city,cityDTO.getId());
        city = cityDTO.update(city);
       cityRepository.save(city);

    }
    @Override
    public List<CityDTO> findAll() {
        List<CityDTO> cityDTOList = new ArrayList<>();
        List<City> cityList = cityRepository.findAll();
        for (City city : cityList){
            cityDTOList.add(new CityDTO(city.getId(),city.getName()));
        }
        return  cityDTOList;
    }
    @Override
    @Transactional
    public void delete(Long id) {
    cityIDNotFound(cityRepository,id);
    cityRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {

        City city = cityRepository.findById(id).orElse(null);
        cityIDNotFound(city,id);


        return  new CityDTO(city);
    }


}
