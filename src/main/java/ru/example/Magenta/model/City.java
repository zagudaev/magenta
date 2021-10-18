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
public class City extends ModelEntity {


    private String name;

    private double latitude;

    private double	longitude;

}
