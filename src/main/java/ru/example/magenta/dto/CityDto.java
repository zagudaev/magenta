package ru.example.magenta.dto;

import lombok.Data;
import ru.example.magenta.model.City;

@Data
public class CityDto {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
    }

    public CityDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City update(City city) {
        city.setName(this.name);
        city.setLatitude(this.latitude);
        city.setLongitude(this.longitude);
        return city;
    }

    public City toCity() {
        City city = new City();
        city = update(city);
        return city;
    }
}
