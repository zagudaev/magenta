package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.magenta.dto.DistanceDTO;
import ru.example.magenta.service.DistanceService;

@RestController
@AllArgsConstructor
@RequestMapping("/distance")
public class DistanceController {
    private final DistanceService distanceService;

    @PostMapping("")
    public Long create (@RequestBody DistanceDTO distanceDTO){return distanceService.create(distanceDTO);}

    @PutMapping("")
    public void update(@RequestBody DistanceDTO distanceDTO){  distanceService.update(distanceDTO);}

    @GetMapping("/findbyid/{id}")
    public DistanceDTO read (@PathVariable Long id) { return  distanceService.findById(id);}

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){ distanceService.delete(id);}


}
