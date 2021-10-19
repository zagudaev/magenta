package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.exceptions.DistanceIDNotFoundException;
import ru.example.Magenta.exceptions.DistanceIsBusyException;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityRepository;
import ru.example.Magenta.repository.DistanceRepository;

@Service
@AllArgsConstructor
public class DistanceServiceImpl implements DistanceService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;


    @Override
    @Transactional
    public Long create(DistanceDTO distanceDTO) {
       if ( distanceRepository.findByToCityAndFromCity(distanceDTO.getToCity(), distanceDTO.getFromCity()).orElse(null)!= null){
           throw  new DistanceIsBusyException(distanceDTO);};
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
