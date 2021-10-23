package ru.example.magenta.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;


@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
@XmlRootElement
public class Distance extends ModelEntity {

    @ManyToOne
    @JoinColumn(name = "fromCityid")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "toCityid")
    private City toCity;

    private double distance;
}
