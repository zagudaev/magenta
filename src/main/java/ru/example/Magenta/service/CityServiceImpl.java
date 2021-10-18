package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.model.City;
import ru.example.Magenta.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.example.Magenta.exceptions.CityException.*;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;

    @Override
    @Transactional
    public Long create(CityDTO cityDTO) {
        coordinatesAreBusyException(cityDTO,cityRepository);
        City city = cityDTO.toCity();
        return  cityRepository.save(city).getId();
    }

    @Override
    @Transactional
    public void update(CityDTO cityDTO) {
        City city = cityRepository.findById(cityDTO.getId()).orElse(null);
        cityIDNotFoundException(city,cityDTO.getId());
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
    cityIDNotFoundException(cityRepository,id);
    cityRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO findById(Long id) {

        City city = cityRepository.findById(id).orElse(null);
        cityIDNotFoundException(city,id);


        return  new CityDTO(city);
    }


}
