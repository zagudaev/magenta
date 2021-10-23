package ru.example.magenta.util;


import ru.example.magenta.model.City;
import ru.example.magenta.model.Distance;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class CityAndDistanceHolder {

    private List<City> valuesCity = new ArrayList<>();
    private List<Distance> valuesDistance = new ArrayList<>();
    @XmlElement
    public List<City> getValuesCity() {
        return valuesCity;
    }
    @XmlElement
    public List<Distance> getValuesDistance(){
        return valuesDistance;
    }


}