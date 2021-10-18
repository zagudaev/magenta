package ru.example.Magenta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.model.City;
import ru.example.Magenta.repository.CityRepository;

public class CityException extends ResponseStatusException {


    public CityException(HttpStatus status, String reason) {
        super(status, reason);
    }
    public static void coordinatesAreBusy(CityDTO cityDTO, CityRepository cityRepository) {
        if (cityRepository.findByLatitudeAndLongitude(cityDTO.getLatitude(),cityDTO.getLongitude()).orElse(null) != null) {
            throw new CityException(HttpStatus.BAD_REQUEST, "Данные координаты уже заняты  : " + cityDTO.getLatitude() + ":" +
                    cityDTO.getLongitude());
        }
    }



    public static void cityIDNotFound (CityRepository cityRepository, Long id){
        if (cityRepository.findById(id).orElse(null) == null){
            throw  new CityException(HttpStatus.BAD_REQUEST, "Город с таким ID не найден : "+
                    id);
        }
    }
    public static void cityIDNotFound (City city , Long id){
        if (city == null)
              throw new   CityException(HttpStatus.BAD_REQUEST, "Город с таким ID не найден : "+ id);

    }


}
