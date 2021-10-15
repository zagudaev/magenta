package ru.example.Magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.service.TestExampleServiceImpl;
import ru.example.Magenta.util.CalculationType;

import java.io.File;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/testexample")
public class TestExampleController {
    private final TestExampleServiceImpl testExampleService;

    @GetMapping("/all")
    private List<CityDTO> findAll () { return  testExampleService.findAll();}

    private List<String> CalculateDistance (@RequestBody CalculationType calculationType, List<CityDTO> fromCityList, List<CityDTO> toCityList)
    {return testExampleService.CalculateDistance(calculationType,fromCityList,toCityList);}

    private ResponseEntity<Object> addXLMinDB(@RequestParam("file") File file){
        return testExampleService.addXLMinDB(file);
    }
    @GetMapping("/test2")
    public void Test2(){
        testExampleService.Test2();
    }
    @GetMapping("/test3")
    public void Test3(){
        testExampleService.Test3();
    }
    @GetMapping("/test1")
    public void Test1(){
        testExampleService.Test1();
    }
}
