package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityDAO;
import ru.example.Magenta.repository.DistanceDAO;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class DistanceServiceImpl implements DistanceService {
    private final CityDAO cityDAO;
    private final DistanceDAO distanceDAO;


    @Override
    @Transactional
    public Long create(DistanceDTO distanceDTO) {
        if (distanceDAO.findByToCityAndFromCity(distanceDTO.getToCity(), distanceDTO.getFromCity()).orElse(null) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "данное направление занято : " + distanceDTO.getFromCity() + ":" +
                    distanceDTO.getToCity());
        }
        Distance distance = distanceDTO.toDistance(cityDAO);
        DistanceDTO distanceDTORevers = new DistanceDTO(distance);
       distanceDTORevers.setFromCity(distance.getToCity().getName());
       distanceDTORevers.setToCity(distance.getFromCity().getName());
       distanceDTORevers.setDistance(distanceDTO.getDistance());
       Distance distanceRevers = distanceDTORevers.toDistance(cityDAO);
       distanceDAO.save(distanceRevers);
        return distanceDAO.save(distance).getId();
    }

    @Override
    @Transactional
    public void update(DistanceDTO distanceDTO) {
        Distance distance = distanceDAO.findById(distanceDTO.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Направление с таким ID не найдено : " +
                +distanceDTO.getId()));
        distance = distanceDTO.update(distance, cityDAO);
        distanceDAO.save(distance);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (distanceDAO.findById(id).orElse(null) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Направление с таким ID не найдено : " + id);
        }
        distanceDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public DistanceDTO read(Long id) {
        Distance distance = distanceDAO.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Направление с таким ID не найдено : " + +id));
        DistanceDTO distanceDTO = new DistanceDTO(distance);
        return distanceDTO;
    }


}
