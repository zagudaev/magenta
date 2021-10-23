package ru.example.magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.magenta.dto.CityDto;
import ru.example.magenta.exceptions.CityCoordinatesAreBusyException;
import ru.example.magenta.exceptions.CityNotFoundException;
import ru.example.magenta.model.City;
import ru.example.magenta.repository.CityRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    @Transactional
    public Long create(CityDto cityDto) {
        if (!cityRepository.findByLatitudeAndLongitude(cityDto.getLatitude(), cityDto.getLongitude()).isPresent()) {
            throw new CityCoordinatesAreBusyException(cityDto);
        }

        return cityRepository.save(cityDto.toCity()).getId();
    }

    @Override
    @Transactional
    public void update(CityDto cityDto) {
        City city = findByIdNN(cityDto.getId());

        city = cityDto.update(city);

        cityRepository.save(city);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityDto> findAll() {
        return cityRepository.findAll().stream()
                .map(city -> new CityDto(city.getId(), city.getName()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CityDto findById(Long id) {
        return new CityDto(findByIdNN(id));
    }

    public City findByIdNN(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(id));
    }

}
