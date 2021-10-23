package ru.example.magenta.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
@XmlRootElement
public class City extends ModelEntity {

    private String name;

    private double latitude;

    private double longitude;

}
