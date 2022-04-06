package ru.saumlaki.price_dynamic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

import javax.persistence.*;

@Entity(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @TableViewColumn(name = "Код")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @TableViewColumn(name = "Наименование")
    @Column
    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        return description;
    }
}
