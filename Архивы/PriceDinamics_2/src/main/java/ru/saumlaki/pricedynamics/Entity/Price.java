package ru.saumlaki.pricedynamics.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "season_id")
    Season season;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "city_id")
    City city;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "shop_id")
    Shop shop;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Getter
    @Setter
    @Column(name = "cost")
    double cost;
}
