package ru.saumlaki.price_dynamic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.price_dynamic.entity.annotatons.NotEmpty;
import ru.saumlaki.price_dynamic.entity.annotatons.NotNull;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "shop")
@NoArgsConstructor
@AllArgsConstructor
public class Shop {

    //@TableViewColumn(name = "Код")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @TableViewColumn(name = "Наименование")
    @Column
    @NotEmpty
    @NotNull
    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return id == shop.id && Objects.equals(description, shop.description);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
