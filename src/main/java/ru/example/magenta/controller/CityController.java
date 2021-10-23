package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.magenta.dto.CityDto;
import ru.example.magenta.service.CityService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/city")
public class CityController {
    private final CityService cityService;


    @GetMapping("/all")
    public List<CityDto> findAll() {
        return cityService.findAll();
    }

    @PostMapping("")
    public Long create(@RequestBody CityDto cityDTO) {
        return cityService.create(cityDTO);
    }

    @PutMapping("")
    public void update(@RequestBody CityDto cityDTO) {
        cityService.update(cityDTO);
    }

    @GetMapping("/{id}")
    public CityDto read(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }

}
