package ru.example.Magenta.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;


@Getter
@Setter
@NoArgsConstructor
@Entity
@XmlRootElement
public class Distance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "fromCityid")
    private City fromCity;
    @ManyToOne
    @JoinColumn(name = "toCityid")
    private City toCity;
    private double  distance;
}
