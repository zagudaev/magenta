package ru.example.magenta.service;

import ru.example.magenta.dto.CityDTO;
import ru.example.magenta.util.CalculationType;

import java.util.List;
/**
 *Distance calculation service
 */
public interface DistanceCalculation {

    /**
     *Method for calculating the distance "in a straight line"
     * @param fromCity
     * @param toCity
     * @return distance "in a straight line"
     */
    double crowflight(CityDTO fromCity, CityDTO toCity);


    /**
     * Method increases distance from DB
     * @param fromCity
     * @param toCity
     * @return
     */
    double distanceMatrix(CityDTO fromCity, CityDTO toCity);


    /**
     * Depending on the type of calculation, the distance increases
     * @param calculationType
     * @param fromCityList
     * @param toCityList
     * @return
     */
    List<String> CalculateDistance(CalculationType calculationType, List<CityDTO> fromCityList, List<CityDTO> toCityList);


}