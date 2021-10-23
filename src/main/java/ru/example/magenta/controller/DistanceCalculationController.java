package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.magenta.dto.CityDTO;
import ru.example.magenta.service.DistanceCalculation;
import ru.example.magenta.util.CalculationType;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/distancecalculation")
public class DistanceCalculationController {
    private final DistanceCalculation testExampleService;


    @PostMapping
    public List<String> calculateDistance ( CalculationType calculationType, List<CityDTO> fromCityList, List<CityDTO> toCityList)
    {return testExampleService.CalculateDistance(calculationType,fromCityList,toCityList);}


}
