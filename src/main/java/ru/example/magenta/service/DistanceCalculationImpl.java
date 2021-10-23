package ru.example.magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.magenta.dto.CityDto;
import ru.example.magenta.exceptions.DistanceIsBusyException;
import ru.example.magenta.repository.DistanceRepository;
import ru.example.magenta.util.CalculationType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DistanceCalculationImpl implements DistanceCalculation {

    private final DistanceRepository distanceRepository;

    @Override
    public double crowFlight(CityDto fromCity, CityDto toCity) {
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

        return (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c * 100) / 100.00);
    }

    @Override
    public double distanceMatrix(CityDto fromCity, CityDto toCity) {
        if (fromCity.equals(toCity)) {
            return 0;
        }

        return distanceRepository.findByToCityAndFromCity(fromCity.getName(), toCity.getName())
                .orElseThrow(() -> new DistanceIsBusyException(fromCity, toCity))
                .getDistance();
    }


    @Override
    public List<String> calculateDistance(CalculationType calculationType, List<CityDto> fromCityList, List<CityDto> toCityList) {
        switch (calculationType) {
            case CROW_FLIGHT: {
                return calculateByCrowFlight(fromCityList, toCityList);
            }
            case DISTANCE_MATRIX: {
                return calculateByDistanceMatrix(fromCityList, toCityList);
            }
            case ALL: {
                return Collections.singletonList(calculateByCrowFlight(fromCityList, toCityList) + " " + calculateByDistanceMatrix(fromCityList, toCityList));
            }
        }

        throw new IllegalArgumentException(calculationType.name());
    }

    public List<String> calculateByCrowFlight(List<CityDto> fromCityList, List<CityDto> toCityList) {
        return fromCityList.stream()
                .flatMap(fromCity -> toCityList.stream()
                        .map(toCity -> "Расстояние из " + fromCity.getName() + " до " + toCity.getName() + " по методу crowflight :  " + crowFlight(fromCity, toCity)))
                .collect(Collectors.toList());
    }

    public List<String> calculateByDistanceMatrix(List<CityDto> fromCityList, List<CityDto> toCityList) {
        return fromCityList.stream()
                .flatMap(fromCity -> toCityList.stream()
                        .map(toCity -> "Расстояние из " + fromCity.getName() + " до " + toCity.getName() + " по методу distanceMatrix :  " + distanceMatrix(fromCity, toCity)))
                .collect(Collectors.toList());
    }
}

