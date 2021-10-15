package ru.example.Magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.service.CityService;
import ru.example.Magenta.service.CityServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final CityServiceImpl cityService;


    @GetMapping("/hello")
    public String hello(){
        return  "hello";
    }

    @PostMapping("")
    private Long create (@RequestBody CityDTO cityDTO){return cityService.create(cityDTO);}

    @PutMapping("")
    private void update(@RequestBody CityDTO cityDTO){  cityService.update(cityDTO);}

    @GetMapping("/read/{id}")
    private CityDTO read (@PathVariable Long id) { return  cityService.read(id);}

    @DeleteMapping("/delete/{id}")
    private void delete(@PathVariable Long id){ cityService.delete(id);}

}
