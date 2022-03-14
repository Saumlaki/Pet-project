package ru.saumlaki.price_dynamic.view.jfx.shop;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controller.jfx.shop.ShopListViewController;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.view.interfaces.Erroreble;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;

import java.io.IOException;

@Component
public class ShopListView implements Showable {

    @Autowired
    private ShopService shopService;

    @Override
    public void show(Stage stage) {

        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/jfxView/shop/shopListView.fxml"));
        VBox root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            ((Erroreble)Main.applicationContext.getBean("errorMessageView")).error("Ошибка загрузки формы списка магазинов",
                    e.getMessage());
        }
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        TableView<Shop> tableView = (TableView<Shop>) scene.lookup("#table");

        creteColumn(tableView);
        fillTable(tableView);

        stage.setScene(scene);
        stage.show();
    }

    /////////////////////////////////////////////////////////////////
    //Создание таблицы

    private void creteColumn(TableView<Shop> tableView) {

        TableColumn<Shop, String> nameColumn = new TableColumn<>("Наименование");
        nameColumn.setCellValueFactory(shopStringCellDataFeatures -> new SimpleStringProperty(shopStringCellDataFeatures.getValue().getName()));
        tableView.getColumns().add(nameColumn);
    }
    //Создание таблицы
    /////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////
    //Наполнение формы
    private void fillTable(TableView<Shop> tableView) {

        shopService.getAll().stream().forEach(shop -> tableView.getItems().add(shop));
    }
    //Наполнение формы
    /////////////////////////////////////////////////////////////////


}
