package ru.example.magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.magenta.dto.DistanceDto;
import ru.example.magenta.exceptions.DistanceIsBusyException;
import ru.example.magenta.exceptions.DistanceNotFoundException;
import ru.example.magenta.model.City;
import ru.example.magenta.model.Distance;
import ru.example.magenta.repository.CityRepository;
import ru.example.magenta.repository.DistanceRepository;

@Service
@AllArgsConstructor
public class DistanceServiceImpl implements DistanceService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;


    @Override
    @Transactional
    public Long create(DistanceDto distanceDto) {
        if (!distanceRepository.findByToCityAndFromCity(distanceDto.getToCity(), distanceDto.getFromCity()).isPresent()) {
            throw new DistanceIsBusyException(distanceDto);
        }
        City fromCity = cityRepository.findByName(distanceDto.getFromCity()).get();
        City toCity = cityRepository.findByName(distanceDto.getToCity()).get();
        Distance distance = distanceDto.toDistance(fromCity,toCity);
        Distance distanceRevers = distanceDto.toDistanceRevers(fromCity,toCity);

        distanceRepository.save(distanceRevers);
        return distanceRepository.save(distance).getId();
    }

    @Override
    @Transactional
    public void update(DistanceDto distanceDto) {
        Distance distance = findByIdNN(distanceDto.getId());
        City fromCity = cityRepository.findByName(distanceDto.getFromCity()).get();
        City toCity = cityRepository.findByName(distanceDto.getToCity()).get();

        distance = distanceDto.update(distance, fromCity,toCity);

        distanceRepository.save(distance);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        distanceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DistanceDto findById(Long id) {
        return new DistanceDto(findByIdNN(id));
    }

    public Distance findByIdNN(Long id) {
        return distanceRepository.findById(id).orElseThrow(() -> new DistanceNotFoundException(id));
    }
}
