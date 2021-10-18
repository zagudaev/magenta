package ru.example.Magenta.DTO;

import lombok.Data;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityRepository;

@Data
public class DistanceDTO {
    private  Long id;

    private String fromCity;

    private String toCity;
    private double  distance;

    public Distance update(Distance distance, CityRepository cityRepository){

        distance.setDistance(this.distance);
        distance.setFromCity(cityRepository.findByName(this.fromCity).orElse(null));
        distance.setToCity(cityRepository.findByName(this.toCity).orElse(null));
        return distance;
    }
    public Distance toDistance (CityRepository cityRepository){
        Distance distance = new Distance();
        distance = update(distance, cityRepository);
        return distance;
    }

    public Distance Revers(Distance distance, CityRepository cityRepository){

        distance.setDistance(this.distance);
        distance.setFromCity(cityRepository.findByName(this.toCity).orElse(null));
        distance.setToCity(cityRepository.findByName(this.fromCity).orElse(null));
        return distance;
    }
    public Distance toDistanceRevers (CityRepository cityRepository){
        Distance distance = new Distance();
        distance = Revers(distance, cityRepository);
        return distance;
    }

    public DistanceDTO (Distance distance){

        this.distance = distance.getDistance();
        this.fromCity = distance.getFromCity().getName();
        this.toCity = distance.getToCity().getName();
    }
}
