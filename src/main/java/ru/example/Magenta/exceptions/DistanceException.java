package ru.example.Magenta.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityRepository;
import ru.example.Magenta.repository.DistanceRepository;

public class DistanceException extends ResponseStatusException {
    public DistanceException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public static void distanceIsBusyException(DistanceRepository distanceRepository, DistanceDTO distanceDTO){
        if (distanceRepository.findByToCityAndFromCity(distanceDTO.getToCity(), distanceDTO.getFromCity()).orElse(null) != null) {
            throw new DistanceException(HttpStatus.BAD_REQUEST, "данное направление занято : " + distanceDTO.getFromCity() + ":" +
                    distanceDTO.getToCity());
        }
    }

    public static void distanceIDNotFoundException(DistanceRepository distanceRepository, Long id){
        if (distanceRepository.findById(id).orElse(null) == null){
            throw  new DistanceException(HttpStatus.BAD_REQUEST, "Направление с таким ID не найдено : "+
                    id);
        }
    }
    public static void distanceIDNotFoundException(Distance distance , Long id){
        if (distance.getId() == null)
            throw new   DistanceException(HttpStatus.BAD_REQUEST, "Направление с таким ID не найдено : "+ id);

    }


    public static void distanceNotFoundException(Distance distance , CityDTO fromCity, CityDTO toCity){
        if (distance == null)
            throw new   DistanceException(HttpStatus.BAD_REQUEST, "Направление  не найдено : "+
                    fromCity.getName()  + " -> "+toCity.getName());

    }
}
