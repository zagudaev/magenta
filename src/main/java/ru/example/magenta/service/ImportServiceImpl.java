package ru.example.magenta.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.magenta.exceptions.FileParseException;
import ru.example.magenta.model.City;
import ru.example.magenta.model.Distance;
import ru.example.magenta.repository.CityRepository;
import ru.example.magenta.repository.DistanceRepository;
import ru.example.magenta.util.CityAndDistanceHolder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Slf4j
@Service
@AllArgsConstructor
public class ImportServiceImpl implements ImportService {
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;


    @Override
    @Transactional
    public void importFile(File file) {
        CityAndDistanceHolder holder = parseFile(file);

        cityRepository.saveAll(holder.getValuesCity());
        distanceRepository.saveAll(holder.getValuesDistance());
    }

    private CityAndDistanceHolder parseFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(CityAndDistanceHolder.class, Distance.class, City.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            return  (CityAndDistanceHolder) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            log.error(e.getLocalizedMessage());

            throw new FileParseException(e);
        }
    }
}
