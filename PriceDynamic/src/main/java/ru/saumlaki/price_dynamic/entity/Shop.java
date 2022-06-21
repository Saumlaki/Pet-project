package ru.saumlaki.price_dynamic.entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.price_dynamic.entity.annotatons.NotEmpty;
import ru.saumlaki.price_dynamic.entity.annotatons.NotNull;
import ru.saumlaki.price_dynamic.entity.annotatons.TableViewColumn;
import ru.saumlaki.price_dynamic.supporting.Helper;

import javax.persistence.*;
import java.io.IOException;
import java.net.URL;
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

    @TableViewColumn(name = "Наименование", order = 1)
    @Column
    @NotEmpty
    @NotNull
    @Getter
    @Setter
    private String description;

    @TableViewColumn(name = "",order = 0)
    @Transient
    private Image image;

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

    public ImageView getImage() {

        URL iconURL = Helper.getResourcesURLForPropertyName("StringIcon");
        try {
            image = new Image(iconURL.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageView imageView = new ImageView(image);
        return imageView;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

