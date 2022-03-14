package ru.saumlaki.price_dynamic.controller.jfx.shop;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.saumlaki.price_dynamic.PriceDynamics;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.service.interfaces.Service;
import ru.saumlaki.price_dynamic.view.jfx.shop.ShopElement;

import java.util.ArrayList;
import java.util.List;

public class ShopListController implements Refreshable {

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

        ShopElement shopElement = (ShopElement) PriceDynamics.applicationContext.getBean("shopElement");
        shopElement.setShop(new Shop());
        shopElement.show(null, this);
    }

    @FXML
    void change(ActionEvent event) {

        Shop shop = table.getSelectionModel().getSelectedItem();
        ShopElement shopElement = (ShopElement) PriceDynamics.applicationContext.getBean("shopElement");
        shopElement.setShop(shop);
        shopElement.show(null, this);
    }

    @FXML
    void copy(ActionEvent event) {

        Shop shop = table.getSelectionModel().getSelectedItem();
        Shop newShop = new Shop(shop.getName());
        ShopElement shopElement = (ShopElement) PriceDynamics.applicationContext.getBean("shopElement");
        shopElement.setShop(newShop);
        shopElement.show(null, this);
    }

    @FXML
    void remove(ActionEvent event) {

        Shop shop = table.getSelectionModel().getSelectedItem();
        Service<Shop> service = (Service) PriceDynamics.applicationContext.getBean("shopServiceImpl");
        service.remove(shop);

        refresh();
    }


    private void creteColumn() {

        table.getColumns().removeAll();

        TableColumn<Shop, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(shopStringCellDataFeatures -> new SimpleStringProperty(shopStringCellDataFeatures.getValue().getName()));
        table.getColumns().add(nameColumn);
    }
    //Создание таблицы
    /////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////
    //Наполнение формы
    private void fillTable() {

        ShopServiceImpl shopService = (ShopServiceImpl) PriceDynamics.applicationContext.getBean("shopServiceImpl");
        shopService.getAll().stream().forEach(shop -> table.getItems().add(shop));
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

        creteColumn();
        fillTable();

        table.getItems().clear();
        List<Shop> shopList = ((Service<Shop>) PriceDynamics.applicationContext.getBean("shopServiceImpl")).getAll();
        table.getItems().addAll(shopList);
    }

    @Override
    public void sendRefreshCommand() {

        refreshableList.stream().forEach(element->element.refresh());
    }
}

