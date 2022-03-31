package ru.saumlaki.price_dynamic.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "shop")
@NoArgsConstructor
public class Shop {

    @TableViewColumn(name = "Код")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @TableViewColumn(name = "Наименование")
    @Column
    private String description;

    public Shop(String description) {
        this.description = description;
    }
}
