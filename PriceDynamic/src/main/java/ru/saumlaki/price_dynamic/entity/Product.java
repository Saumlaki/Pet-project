package ru.saumlaki.price_dynamic.entity;

import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

import javax.persistence.*;

@Entity(name = "product")
public class Product {

    @TableViewColumn(name = "Код")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @TableViewColumn(name = "Наименование")
    @Column
    private String description;
}
