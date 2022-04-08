package ru.saumlaki.price_dynamic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "price")
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @TableViewColumn(name = "Код",  order = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
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

    public String getShop() {
        return shop.toString();
    }

    public String getProduct() {
        return product.toString();
    }

    public String getPrice() {
        return String.valueOf(price);
    }

    public String getDate() {
        return "date";
    }

    @Override
    public String toString() {
        return shop + " - " + product;
    }


    public String getDescription() {
        return shop + " " + product + " " + price;
    }
}
