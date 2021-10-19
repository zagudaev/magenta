package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.exceptions.DistanceIsBusyException;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.DistanceRepository;
import ru.example.Magenta.util.CalculationType;


import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class DistanceCalculationImpl implements DistanceCalculation {
    private final DistanceRepository distanceRepository;
    final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    @Override
    public double crowflight(CityDTO fromCity, CityDTO toCity) {

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
        Distance distance = distanceRepository.findByToCityAndFromCity(fromCity.getName(),toCity.getName()).orElseThrow(()-> new DistanceIsBusyException(fromCity, toCity));
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

