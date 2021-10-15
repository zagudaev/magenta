package ru.example.Magenta.DTO;

import lombok.Data;
import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityDAO;

@Data
public class DistanceDTO {
    private  Long id;

    private String fromCity;

    private String toCity;
    private double  distance;

    public Distance update(Distance distance, CityDAO cityDAO){

        distance.setDistance(this.distance);
        distance.setFromCity(cityDAO.findByName(this.fromCity).orElse(null));
        distance.setToCity(cityDAO.findByName(this.toCity).orElse(null));
        return distance;
    }
    public Distance toDistance (CityDAO cityDAO){
        Distance distance = new Distance();
        distance = update(distance, cityDAO );
        return distance;
    }

    public DistanceDTO (Distance distance){

        this.distance = distance.getDistance();
        this.fromCity = distance.getFromCity().getName();
        this.toCity = distance.getToCity().getName();
    }
}
