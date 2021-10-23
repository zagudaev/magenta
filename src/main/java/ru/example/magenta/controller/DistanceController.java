package ru.example.magenta.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.example.magenta.dto.DistanceDto;
import ru.example.magenta.service.DistanceService;

@RestController
@AllArgsConstructor
@RequestMapping("/distance")
public class DistanceController {
    private final DistanceService distanceService;

    @PostMapping("")
    public Long create(@RequestBody DistanceDto distanceDTO) {
        return distanceService.create(distanceDTO);
    }

    @PutMapping("")
    public void update(@RequestBody DistanceDto distanceDTO) {
        distanceService.update(distanceDTO);
    }

    @GetMapping("/{id}")
    public DistanceDto read(@PathVariable Long id) {
        return distanceService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        distanceService.delete(id);
    }
}
