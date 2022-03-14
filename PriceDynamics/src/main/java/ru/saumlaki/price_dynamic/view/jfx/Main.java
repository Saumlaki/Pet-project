package ru.saumlaki.price_dynamic.view.jfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.PriceDynamics;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.controller.jfx.MainViewController;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;

import java.io.IOException;

/**
 * Компонент отвечающий за инициализацию главной формы приложения
 */
@Component
public class Main implements Showable {

    @Autowired
    ShopService shopService;

    @Override
    public void show(Stage stage, Refreshable refreshable) {

        //1. Подключение формы
        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(PriceDynamics.class.getResource("/jfxView/mainView.fxml"));
        VBox root = null;
        try {
            root = fxmlLoader.load();
            MainViewController viewController = fxmlLoader.getController();
        } catch (IOException e) {
            System.out.println("-->Ошибка");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        stage.setScene(scene);
        stage.show();

        //Инициализируем заполнение формы
        ((Refreshable)fxmlLoader.getController()).refresh();
    }
}
