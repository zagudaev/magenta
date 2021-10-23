package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.magenta.dto.CityDTO;
import ru.example.magenta.service.CityService;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;


    @GetMapping("/all")
    public List<CityDTO> findAll () { return  cityService.findAll();}

    @PostMapping("")
    public Long create (@RequestBody CityDTO cityDTO){return cityService.create(cityDTO);}

    @PutMapping("")
    public void update(@RequestBody CityDTO cityDTO){  cityService.update(cityDTO);}

    @GetMapping("/findbyid/{id}")
    public CityDTO read (@PathVariable Long id) { return  cityService.findById(id);}

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){ cityService.delete(id);}

}
