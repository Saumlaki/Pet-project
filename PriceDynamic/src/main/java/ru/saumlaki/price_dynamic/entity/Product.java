package ru.saumlaki.price_dynamic.entity;

import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Getter
    @Setter
    private Product parent;

    @Column
    @Getter
    @Setter
    private boolean isGroup;

    @TableViewColumn(name = "Наименование")
    @Column
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
        Product product = (Product) o;
        return id == product.id && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
