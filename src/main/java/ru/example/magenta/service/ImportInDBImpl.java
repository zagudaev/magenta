package ru.example.magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.magenta.model.City;
import ru.example.magenta.model.Distance;
import ru.example.magenta.repository.CityRepository;
import ru.example.magenta.repository.DistanceRepository;
import ru.example.magenta.util.CityAndDistanceHolder;

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
    public void importXLMinDB(File file) {
        CityAndDistanceHolder holder = new CityAndDistanceHolder();
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(CityAndDistanceHolder.class, Distance.class, City.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            holder = (CityAndDistanceHolder) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        cityRepository.saveAll(holder.getValuesCity());

        for(Distance distance : holder.getValuesDistance()){
            if (cityRepository.findByLatitudeAndLongitude(distance.getFromCity().getLatitude(),distance.getFromCity().getLongitude()).orElse(null) == null){
                cityRepository.save(distance.getFromCity());
            }
            if (cityRepository.findByLatitudeAndLongitude(distance.getToCity().getLatitude(),distance.getToCity().getLongitude()).orElse(null) == null){
                cityRepository.save(distance.getToCity());
            }
            distanceRepository.save(distance);
        }

    }
}
