package ru.example.Magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.service.DistanceServiceImpl;

@RestController
@AllArgsConstructor
@RequestMapping("/distance")
public class DistanceController {
    private final DistanceServiceImpl distanceService;

    @PostMapping("")
    private Long create (@RequestBody DistanceDTO distanceDTO){return distanceService.create(distanceDTO);}

    @PutMapping("")
    private void update(@RequestBody DistanceDTO distanceDTO){  distanceService.update(distanceDTO);}

    @GetMapping("/findbyid/{id}")
    private DistanceDTO read (@PathVariable Long id) { return  distanceService.findById(id);}

    @DeleteMapping("/delete/{id}")
    private void delete(@PathVariable Long id){ distanceService.delete(id);}


}
