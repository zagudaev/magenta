package ru.example.Magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.service.DistanceCalculationImpl;
import ru.example.Magenta.util.CalculationType;

import java.io.File;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/testexample")
public class DistanceCalculationController {
    private final DistanceCalculationImpl testExampleService;


    @GetMapping("/calculatedistance")
    private List<String> CalculateDistance (@RequestBody CalculationType calculationType, List<CityDTO> fromCityList, List<CityDTO> toCityList)
    {return testExampleService.CalculateDistance(calculationType,fromCityList,toCityList);}


}
