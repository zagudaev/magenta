package ru.example.Magenta.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Data
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
