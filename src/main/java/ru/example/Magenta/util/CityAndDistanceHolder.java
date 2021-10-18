package ru.example.Magenta.util;


import ru.example.Magenta.model.City;
import ru.example.Magenta.model.Distance;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class CityAndDistanceHolder {

    private List<City> valuesCity = new ArrayList<>();
    private List<Distance> valuesDistance = new ArrayList<>();
    @XmlElement
    public List<City> getValuesT() {
        return valuesCity;
    }
    @XmlElement
    public List<Distance> getValuesH(){
        return valuesDistance;
    }


}