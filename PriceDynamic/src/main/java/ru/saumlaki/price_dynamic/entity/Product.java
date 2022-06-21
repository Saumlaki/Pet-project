package ru.saumlaki.price_dynamic.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.price_dynamic.controllers.listGroup.Imageeble;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;
import ru.saumlaki.price_dynamic.supporting.Helper;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

@Entity(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Imageeble {

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

    @TableViewColumn(name = "Наименование", order = 1)
    @Column
    @Getter
    @Setter
    private String description;

    @TableViewColumn(name = "", order = 0)
    @Transient
    private Image image;

    public Product(int id, Product parent, boolean isGroup, String description) {
        this.id = id;
        this.parent = parent;
        this.isGroup = isGroup;
        this.description = description;
    }

    public ImageView getImage() {
        URL iconURL = null;

        if (isGroup())
            iconURL = Helper.getResourcesURLForPropertyName("GroupIcon");
        else
            iconURL = Helper.getResourcesURLForPropertyName("StringIcon");

        try {
            image = new Image(iconURL.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView imageView = new ImageView(image);
        return imageView;
    }

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
