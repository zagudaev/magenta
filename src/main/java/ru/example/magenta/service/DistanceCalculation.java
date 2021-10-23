package ru.example.magenta.service;

import ru.example.magenta.dto.CityDto;
import ru.example.magenta.util.CalculationType;

import java.util.List;
/**
 *Distance calculation service
 */
public interface DistanceCalculation {

    double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

    /**
     *Method for calculating the distance "in a straight line"
     * @param fromCity
     * @param toCity
     * @return distance "in a straight line"
     */
    double crowFlight(CityDto fromCity, CityDto toCity);


    /**
     * Method increases distance from DB
     * @param fromCity
     * @param toCity
     * @return
     */
    double distanceMatrix(CityDto fromCity, CityDto toCity);


    /**
     * Depending on the type of calculation, the distance increases
     * @param calculationType
     * @param fromCityList
     * @param toCityList
     * @return
     */
    List<String> calculateDistance(CalculationType calculationType, List<CityDto> fromCityList, List<CityDto> toCityList);


}