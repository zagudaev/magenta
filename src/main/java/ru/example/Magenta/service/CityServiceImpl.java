package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.model.City;
import ru.example.Magenta.repository.CityDAO;

import java.util.Random;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService{

    private final CityDAO cityDAO;

    @Override
    @Transactional
    public Long create(CityDTO cityDTO) {
        if (cityDAO.findByLatitudeAndLongitude(cityDTO.getLatitude(),cityDTO.getLongitude()).orElse(null) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Данные координаты уже заняты  : " + cityDTO.getLatitude() + ":" +
                    cityDTO.getLongitude());
        }
        City city = cityDTO.toCity();
        return  cityDAO.save(city).getId();
    }

    @Override
    @Transactional
    public void update(CityDTO cityDTO) {
       City city = cityDAO.findById(cityDTO.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Город с таким ID не найден : "+
                cityDTO.getId()));
       city = cityDTO.update(city);
       cityDAO.save(city);

    }

    @Override
    @Transactional
    public void delete(Long id) {
    if (cityDAO.findById(id).orElse(null) == null){
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Город с таким ID не найден : "+ id );
    }
    cityDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDTO read(Long id) {

        City city = cityDAO.findById(id).orElseThrow(()->
             new ResponseStatusException(HttpStatus.BAD_REQUEST, "Город с таким ID не найден : " + id));


        return  new CityDTO(city);
    }


}
