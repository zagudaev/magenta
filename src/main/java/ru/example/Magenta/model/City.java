package ru.example.Magenta.model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@XmlRootElement
public class City extends ModelEntity {


    private String name;

    private double latitude;

    private double	longitude;

}
