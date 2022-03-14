package ru.saumlaki.price_dynamic.controller.jfx.shop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.service.interfaces.Service;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;
import ru.saumlaki.price_dynamic.view.jfx.shop.ShopElementView;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class ShopListViewController implements Refreshable {

    //Список зависимых форм
    List<Refreshable> refreshableList = new ArrayList<>();

    //Основная таблица формы "Продукты"
    @FXML
    private TableView<Shop> table;

    //*************************************************
    //Обработчики событий
    //Кнопки
    @FXML
    void add(ActionEvent event) {

        ShopElementView shopElementView = (ShopElementView) Main.applicationContext.getBean("shopElementView");
        shopElementView.setShop(new Shop());
        shopElementView.show(null, this);
    }

    @FXML
    void change(ActionEvent event) {

        Shop shop = table.getSelectionModel().getSelectedItem();
        ShopElementView shopElementView = (ShopElementView) Main.applicationContext.getBean("shopElementView");
        shopElementView.setShop(shop);
        shopElementView.show(null, this);
    }

    @FXML
    void copy(ActionEvent event) {

        Shop shop = table.getSelectionModel().getSelectedItem();
        Shop newShop = new Shop(shop.getName());
        ShopElementView shopElementView = (ShopElementView) Main.applicationContext.getBean("shopElementView");
        shopElementView.setShop(newShop);
        shopElementView.show(null, this);
    }

    @FXML
    void remove(ActionEvent event) {

        Shop shop = table.getSelectionModel().getSelectedItem();
        Service<Shop> service = (Service) Main.applicationContext.getBean("shopServiceImpl");
        service.remove(shop);

        refresh();
    }


    //*************************************************
    //Реализация интерфейса Refreshable

    @Override
    public void addRefreshable(Refreshable refreshable) {

        refreshableList.add(refreshable);
    }

    @Override
    public void removeRefreshable(Refreshable refreshable) {

        refreshableList.remove(refreshable);
    }

    @Override
    public void refresh() {

        table.getItems().clear();
        List<Shop> shopList = ((Service<Shop>)Main.applicationContext.getBean("shopServiceImpl")).getAll();
        table.getItems().addAll(shopList);
    }

    @Override
    public void sendRefreshCommand() {

        refreshableList.stream().forEach(element->element.refresh());
    }
}

