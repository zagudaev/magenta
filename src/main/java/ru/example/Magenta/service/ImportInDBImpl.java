package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityRepository;
import ru.example.Magenta.repository.DistanceRepository;
import ru.example.Magenta.util.CityAndDistanceHolder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
@Service
@AllArgsConstructor
public class ImportInDBImpl implements ImportInDB {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;
    @Override
    @Transactional
    public ResponseEntity<Object> importXLMinDB(File file) {
        CityAndDistanceHolder holder = new CityAndDistanceHolder();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(CityAndDistanceHolder.class, Distance.class, City.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            holder = (CityAndDistanceHolder) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        cityRepository.saveAll(holder.getValuesT());

        for(Distance distance : holder.getValuesH()){
            if (cityRepository.findByLatitudeAndLongitude(distance.getFromCity().getLatitude(),distance.getFromCity().getLongitude()).orElse(null) == null){
                cityRepository.save(distance.getFromCity());
            }
            if (cityRepository.findByLatitudeAndLongitude(distance.getToCity().getLatitude(),distance.getToCity().getLongitude()).orElse(null) == null){
                cityRepository.save(distance.getToCity());
            }
            distanceRepository.save(distance);
        }

        return ResponseEntity.ok().build();
    }
}
