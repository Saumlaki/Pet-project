package ru.saumlaki.pricedynamics.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "citys")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    @Getter
    @Setter
    String name;
}
