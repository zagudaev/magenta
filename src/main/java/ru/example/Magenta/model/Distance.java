package ru.example.Magenta.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Setter
@Getter
@EqualsAndHashCode
@Entity
@XmlRootElement
public class Distance extends ModelEntity {


    @ManyToOne
    @JoinColumn(name = "fromCityid")
    private City fromCity;
    @ManyToOne
    @JoinColumn(name = "toCityid")
    private City toCity;
    private double  distance;


}
