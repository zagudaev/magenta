package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.magenta.dto.CityDto;
import ru.example.magenta.service.DistanceCalculation;
import ru.example.magenta.util.CalculationType;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/distance-calculation")
public class DistanceCalculationController {
    private final DistanceCalculation testExampleService;


    @PostMapping
    public List<String> calculateDistance(CalculationType calculationType, List<CityDto> fromCityList, List<CityDto> toCityList) {
        return testExampleService.calculateDistance(calculationType, fromCityList, toCityList);
    }
}
