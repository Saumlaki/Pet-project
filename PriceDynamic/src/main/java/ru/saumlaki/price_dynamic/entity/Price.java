package ru.saumlaki.price_dynamic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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
    @Setter
    @Getter
    private Shop shop;

    @TableViewColumn(name = "Товар",  order = 3)
    @OneToOne
    @JoinColumn(name = "product_id")
    @Setter
    @Getter
    private Product product;

    @TableViewColumn(name = "Цена",  order = 4)
    @Column
    @Setter
    @Getter
    private Integer price;

    @TableViewColumn(name = "Дата", order = 1)
    @Column
    @Setter
    @Getter
    private LocalDate date;

    @Override
    public String toString() {
        return shop + " - " + product;
    }

    public String getDescription() {
        return shop + " " + product + " " + price;
    }


}
