package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.exceptions.CityCoordinatesAreBusyException;
import ru.example.Magenta.exceptions.CityIdNotFoundException;
import ru.example.Magenta.model.City;
import ru.example.Magenta.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    @Transactional
    public Long create(CityDTO cityDTO) {
        if (cityRepository.findByLatitudeAndLongitude(cityDTO.getLatitude(),cityDTO.getLongitude()).orElse(null) != null)
        { throw  new CityCoordinatesAreBusyException(cityDTO);}
        City city = cityDTO.toCity();
        return  cityRepository.save(city).getId();
    }

    @Override
    @Transactional
    public void update(CityDTO cityDTO) {
        City city = cityRepository.findById(cityDTO.getId()).orElseThrow(()-> new CityIdNotFoundException(cityDTO.getId()));
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
        cityRepository.findById(id).orElseThrow(()-> new CityIdNotFoundException(id));
    cityRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {

        City city = cityRepository.findById(id).orElseThrow(()-> new CityIdNotFoundException(id));



        return  new CityDTO(city);
    }



}
