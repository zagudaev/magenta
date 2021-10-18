package ru.example.Magenta.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.example.Magenta.model.City;

@Data
public class CityDTO {
    private  Long id;
    private String name;
    private double latitude;
    private double longitude;

    public City update(City city){
        city.setName(this.name);
        city.setLatitude(this.latitude);
        city.setLongitude(this.longitude);
        return city;
    }

    public City toCity(){
        City city = new City();
        city = update(city);
        return city;
    }

    public  CityDTO(City city){
        this.id = city.getId();
        this.name = city.getName();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();


    }

    public  CityDTO(Long id, String name){
        this.id = id;
        this.name = name;

    }

}
