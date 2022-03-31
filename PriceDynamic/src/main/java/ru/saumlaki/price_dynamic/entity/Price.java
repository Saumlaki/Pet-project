package ru.saumlaki.price_dynamic.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Date date;
}
