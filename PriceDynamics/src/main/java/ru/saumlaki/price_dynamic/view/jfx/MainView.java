package ru.saumlaki.price_dynamic.view.jfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.controller.jfx.MainViewController;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;

import javax.swing.text.TableView;
import java.io.IOException;
import java.util.List;

/**
 * Компонент отвечающий за инициализацию главной формы приложения
 */
@Component
public class MainView implements Showable {

    @Autowired
    ShopService shopService;

    @Override
    public void show(Stage stage, Refreshable refreshable) {

        //1. Подключение формы
        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/jfxView/mainView.fxml"));
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
