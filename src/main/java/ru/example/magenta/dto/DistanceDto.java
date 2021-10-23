package ru.example.magenta.dto;

import lombok.Data;
import ru.example.magenta.model.City;
import ru.example.magenta.model.Distance;
import ru.example.magenta.repository.CityRepository;

@Data
public class DistanceDto {
    private Long id;

    private String fromCity;

    private String toCity;
    private double distance;

    public DistanceDto(Distance distance) {

        this.distance = distance.getDistance();
        this.fromCity = distance.getFromCity().getName();
        this.toCity = distance.getToCity().getName();
    }

    public Distance update(Distance distance, City fromCity , City toCity) {

        distance.setDistance(this.distance);
        distance.setFromCity(fromCity);
        distance.setToCity(toCity);
        return distance;
    }

    public Distance toDistance(City fromCity , City toCity) {
        Distance distance = new Distance();

        distance = update(distance, fromCity,toCity);
        return distance;
    }

    public Distance toDistanceRevers(City fromCity , City toCity) {
        Distance distance = new Distance();

        distance.setDistance(this.distance);
        distance.setFromCity(toCity);
        distance.setToCity(fromCity);
        return distance;
    }
}
