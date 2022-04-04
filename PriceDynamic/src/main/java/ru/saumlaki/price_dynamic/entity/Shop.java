package ru.saumlaki.price_dynamic.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

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
    @Getter
    @Setter
    private String description;

    public Shop(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
