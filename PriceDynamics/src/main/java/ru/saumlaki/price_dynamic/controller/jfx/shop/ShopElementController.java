package ru.saumlaki.price_dynamic.controller.jfx.shop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ru.saumlaki.price_dynamic.PriceDynamics;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.Service;
import ru.saumlaki.price_dynamic.view.interfaces.Erroreble;

import java.util.ArrayList;
import java.util.List;


public class ShopElementController implements Refreshable {

    //Элемент с которым работаем в настоящий момент
    private Shop shop;

    //Основное окно формы
    private Stage stage;

    //Список зависимых форм
    List<Refreshable> refreshableList = new ArrayList<>();

    @FXML
    private TextField name;

    @FXML
    void cancel(ActionEvent event) {

        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void close(ActionEvent event) {

        stage.close();
    }

    @FXML
    void save(ActionEvent event) {

        if (name.getText().isEmpty()) {
            ((Erroreble) PriceDynamics.applicationContext.getBean("errorMessageView")).error("Ошибка заполнения",
                    "Наименование магазина не может быть пустым");
        } else {
            Service<Shop> service = (Service<Shop>) PriceDynamics.applicationContext.getBean("shopServiceImpl");
            shop.setName(name.getText());
            service.add(shop);

            sendRefreshCommand();
            stage.close();
        }
    }

    //*************************************************
    //Сеттеры

    public void setShop(Shop shop) {
        this.shop = shop == null ? new Shop() : shop;
        this.name.setText(this.shop.getName());
    }

    public void setStage(Stage stage) {

        this.stage = stage;
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
       name.setText(shop.getName());
    }

    @Override
    public void sendRefreshCommand() {

        refreshableList.stream().forEach(element->element.refresh());
    }
}

