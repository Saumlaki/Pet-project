package ru.saumlaki.price_dynamic.entity;

import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "price")
public class Price {

    @TableViewColumn(name = "Код",  order = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @TableViewColumn(name = "Магазин",  order = 2)
    @OneToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @TableViewColumn(name = "Товар",  order = 3)
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @TableViewColumn(name = "Цена",  order = 4)
    @Column
    private Integer price;

    @TableViewColumn(name = "Дата", order = 1)
    @Column
    private Date date;

    @Override
    public String toString() {
        return shop + " - " + product;
    }
}
