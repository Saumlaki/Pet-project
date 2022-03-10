package ru.saumlaki.pricedynamics.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    @Getter
    @Setter
    String name;

    public Product(String name) {
        this.name = name;
    }
}
