package ru.example.magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.magenta.dto.DistanceDTO;
import ru.example.magenta.exceptions.DistanceIDNotFoundException;
import ru.example.magenta.exceptions.DistanceIsBusyException;
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
    public Long create(DistanceDTO distanceDTO) {
       if (!distanceRepository.findByToCityAndFromCity(distanceDTO.getToCity(), distanceDTO.getFromCity()).isPresent()){
           throw  new DistanceIsBusyException(distanceDTO);}
        Distance distance = distanceDTO.toDistance(cityRepository);
        Distance distanceRevers = distanceDTO.toDistanceRevers(cityRepository);
        distanceRepository.save(distanceRevers);
        return distanceRepository.save(distance).getId();
    }

    @Override
    @Transactional
    public void update(DistanceDTO distanceDTO) {
        Distance distance = distanceRepository.findById(distanceDTO.getId()).orElseThrow(()->new DistanceIDNotFoundException(distanceDTO.getId()));

        distance = distanceDTO.update(distance, cityRepository);
        distanceRepository.save(distance);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        distanceRepository.findById(id).orElseThrow(()->new DistanceIDNotFoundException(id));
        distanceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DistanceDTO findById(Long id) {
        Distance distance = distanceRepository.findById(id).orElseThrow(()->new DistanceIDNotFoundException(id));
        return new DistanceDTO(distance);
    }


}
