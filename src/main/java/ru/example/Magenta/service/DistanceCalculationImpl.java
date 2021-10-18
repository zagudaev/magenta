package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.exceptions.DistanceException;
import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityRepository;
import ru.example.Magenta.repository.DistanceRepository;
import ru.example.Magenta.util.CalculationType;
import ru.example.Magenta.util.CityAndDistanceHolder;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class DistanceCalculationImpl implements DistanceCalculation {
    private final DistanceRepository distanceRepository;


    @Override
    public double crowflight(CityDTO fromCity, CityDTO toCity) {
        final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
        double fromCityLat = fromCity.getLatitude();
        double fromCityLng = fromCity.getLongitude();
        double toCityLat = toCity.getLatitude();
        double toCityLng = toCity.getLongitude();

            double latDistance = Math.toRadians(fromCityLat - toCityLat);
            double lngDistance = Math.toRadians(fromCityLng - toCityLng);

            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(fromCityLat)) * Math.cos(Math.toRadians(toCityLat))
                    * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return  (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c*100)/100.00);
    }

    @Override
    public double distanceMatrix(CityDTO fromCity, CityDTO toCity) {
        if (!fromCity.equals(toCity)){
        Distance distance = distanceRepository.findByToCityAndFromCity(fromCity.getName(),toCity.getName()).orElse(null);
        DistanceException.distanceNotFound(distance,fromCity,toCity);
        return  distance.getDistance();}
        else {return 0;}
    }



    @Override
    public List<String> CalculateDistance(CalculationType calculationType, List<CityDTO> fromCityList, List<CityDTO> toCityList) {
        List<String> list = new ArrayList<>();

        switch (calculationType) {
            case crowflight: {
                for (CityDTO fromCity : fromCityList) {
                    for (CityDTO toCity : toCityList) {
                        list.add("Расстояние из " + fromCity.getName() + " до " + toCity.getName() + " по методу crowflight :  " + crowflight(fromCity, toCity));
                    }
                }
            }
            case distanceMatrix: {
                for (CityDTO fromCity : fromCityList) {
                    for (CityDTO toCity : toCityList) {
                        list.add("Расстояние из " + fromCity.getName() + " до " + toCity.getName() + " по методу distanceMatrix :  " + distanceMatrix(fromCity, toCity));
                    }
                }
            }
            case all: {
                for (CityDTO fromCity : fromCityList) {
                    for (CityDTO toCity : toCityList) {
                        list.add("Расстояние из " + fromCity.getName() + " до " + toCity.getName() + " по методу crowflight :  " + crowflight(fromCity, toCity)
                                + " по методу distanceMatrix :  " + distanceMatrix(fromCity, toCity));
                    }
                }
            }
        }
        return list;
    }











}

