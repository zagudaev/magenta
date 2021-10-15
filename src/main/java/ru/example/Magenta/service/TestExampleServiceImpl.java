package ru.example.Magenta.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.example.Magenta.DTO.CityDTO;
import ru.example.Magenta.DTO.DistanceDTO;
import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;
import ru.example.Magenta.repository.CityDAO;
import ru.example.Magenta.repository.DistanceDAO;
import ru.example.Magenta.util.CalculationType;
import ru.example.Magenta.util.Lists;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TestExampleServiceImpl implements TestExampleService{
    private final CityDAO cityDAO;
    private final DistanceDAO distanceDAO;
    private final CityService cityService;
    private  final DistanceService distanceService;

    @Override
    public double crowflight(CityDTO fromCity, CityDTO toCity) {
        final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
        double fromCityLat = fromCity.getLatitude();
        double fromCityLng = fromCity.getLongitude();
        double toCityLat = toCity.getLatitude();
        double toCityLng = toCity.getLongitude();

            double latDistance = Math.toRadians(fromCityLat - toCityLat);
            double lngDistance = Math.toRadians(fromCityLng - toCityLng);

            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(fromCityLat)) * Math.cos(Math.toRadians(toCityLat))
                    * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return  (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c*100)/100.00);
    }

    @Override
    public double distanceMatrix(CityDTO fromCity, CityDTO toCity) {
        if (!fromCity.equals(toCity)){
        Distance distance =distanceDAO.findByToCityAndFromCity(fromCity.getName(),toCity.getName()).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Направление  не найдено : "+
                 fromCity.getName()  + " -> "+toCity.getName()));
        return (double) distance.getDistance();}
        else {return 0;}
    }

    @Override
    public List<CityDTO> findAll() {
        List<CityDTO> cityDTOList = new ArrayList<>();
        List<City> cityList = cityDAO.findAll();
        for (City city : cityList){
            cityDTOList.add(new CityDTO(city.getId(),city.getName()));
        }
        return  cityDTOList;
    }

    @Override
    public List<String> CalculateDistance(CalculationType calculationType, List<CityDTO> fromCityList, List<CityDTO> toCityList) {
        List<String> list = new ArrayList<>();

        if (calculationType == CalculationType.crowflight){
            for(CityDTO fromCity: fromCityList){
                for (CityDTO toCity: toCityList){
                    list.add("Расстояние из "+ fromCity.getName() + " до "+toCity.getName() + crowflightToString(fromCity,toCity));
                }
            }
        }else if (calculationType == CalculationType.distanceMatrix){
            for(CityDTO fromCity: fromCityList){
                for (CityDTO toCity: toCityList){
                    list.add("Расстояние из "+ fromCity.getName() + " до "+toCity.getName() + distanceMatrixString(fromCity,toCity));
                }
            }
        }else if (calculationType == CalculationType.all){
            for(CityDTO fromCity: fromCityList){
                for (CityDTO toCity: toCityList){
                    list.add("Расстояние из "+ fromCity.getName() + " до "+toCity.getName() + distanceMatrixString(fromCity,toCity)
                            + distanceMatrixString(fromCity,toCity));
                }
            }
        }
        return list;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> addXLMinDB(File file) {
        Lists lists = new Lists();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Lists.class, Distance.class, City.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            lists = (Lists) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        for (City city : lists.getValuesT()){
            cityService.create(new CityDTO(city));
        }

        for(Distance distance : lists.getValuesH()){
            if (cityDAO.findByLatitudeAndLongitude(distance.getFromCity().getLatitude(),distance.getFromCity().getLongitude()).orElse(null) == null){
                City fromCity = new City();
                fromCity.setName(distance.getFromCity().getName());
                fromCity.setLongitude(distance.getFromCity().getLongitude());
                fromCity.setLatitude(distance.getFromCity().getLatitude());
                cityDAO.save(fromCity);
                distance.setFromCity(fromCity);
            }
            if (cityDAO.findByLatitudeAndLongitude(distance.getToCity().getLatitude(),distance.getToCity().getLongitude()).orElse(null) == null){
                City toCity = new City();
                toCity.setName(distance.getToCity().getName());
                toCity.setLongitude(distance.getToCity().getLongitude());
                toCity.setLatitude(distance.getToCity().getLatitude());
                cityDAO.save(toCity);
                distance.setFromCity(toCity);
            }
            distanceService.create(new DistanceDTO(distance));
        }

        return ResponseEntity.ok().build();
    }



    public String crowflightToString (CityDTO fromCity, CityDTO toCity){
        String s = " по методу crowflight :  " + crowflight(fromCity,toCity);
        return s;
    }

    public String distanceMatrixString(CityDTO fromCity, CityDTO toCity){
        String s = " по методу distanceMatrix :  " + distanceMatrix(fromCity,toCity);;
        return s;
    }
    @Transactional
    public void Test2(){
        List<City> cityList = cityDAO.findAll();
        int size =cityList.size();
        City fromCity = cityList.get( (int)  (Math.random() * size));
        City toCity = cityList.get( (int)  (Math.random() * size));
        if (fromCity.equals(toCity)){ toCity = cityList.get( (int)  (Math.random() * ++size));}
        Distance distance = new Distance();
        distance.setFromCity(fromCity);
        distance.setToCity(toCity);
        distance.setDistance(crowflight(new CityDTO(fromCity),new CityDTO(toCity)));
      //  distanceDAO.save(distance);
        distanceService.create(new DistanceDTO(distance));
    }

    @Transactional
    public void Test3(){
        List<CityDTO> cityDTOList = new ArrayList<>();
        List<City> cityList = cityDAO.findAll();
        List<Distance> distances = distanceDAO.findAll();
        for (City city : cityList){
            cityDTOList.add(new CityDTO(city));
        }
        List<String> strings = CalculateDistance(CalculationType.all,cityDTOList,cityDTOList);
        System.out.println(strings);
        ResponseEntity<Object> objectResponseEntity = addXLMinDB(new File("my.xml"));
        System.out.println(objectResponseEntity);

    }

    @Transactional
    public void Test1(){
        City c = new City();
        Random random = new Random();
        c.setName(String.valueOf(random.nextInt()));
        c.setLongitude(random.nextDouble());
        c.setLatitude(random.nextDouble());

        City z = new City();

        z.setName(String.valueOf(random.nextInt()));
        z.setLongitude(random.nextDouble());
        z.setLatitude(random.nextDouble());

        Long l;
        l = cityService.create(new CityDTO(c));
        l = cityService.create(new CityDTO(z));

    }



}

