package ru.saumlaki.pricedynamics.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    @Getter
    @Setter
    String name;

    public Shop() {
    }

    public Shop(String name) {
        this.name = name;
    }
}
