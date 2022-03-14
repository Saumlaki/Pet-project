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
import ru.saumlaki.price_dynamic.controller.jfx.MainViewController;
import ru.saumlaki.price_dynamic.dao.interfaces.ShopDAO;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;

import javax.swing.text.TableView;
import java.io.IOException;
import java.util.List;

/**
 * Компонент отвечающий за инициализацию главной формы приложения
 */
@Component
public class MainView {

    @Autowired
    ShopService shopService;

    public void show(Stage stage) {

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

        //2. Настройка формы
        TreeTableView<Product> treeTableView = (TreeTableView<Product>) scene.lookup("#table");
        creteColumn(treeTableView);
    }


    /////////////////////////////////////////////////////////////////
    //Создание таблицы

    private void creteColumn(TreeTableView<Product> treeTableView) {

        createColumnProduct(treeTableView);
        createColumnShop(treeTableView);
        createColumnPrice(treeTableView);
    }

    private void createColumnProduct(TreeTableView<Product> treeTableView) {
        TreeTableColumn<Product, String> productColumn = new TreeTableColumn<>("Товары");
        productColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Product, String>("name"));

        productColumn.setPrefWidth(500);

        treeTableView.getColumns().add(productColumn);
    }

    private void createColumnShop(TreeTableView<Product> treeTableView) {
        List<Shop> shopList = shopService.getAll();

        TreeTableColumn<Product, String> shopColumnGroup = new TreeTableColumn<>("Магазины");

        for (Shop shop : shopList) {
            TreeTableColumn<Product, String> shopColumn = new TreeTableColumn<>(shop.getName());
            shopColumnGroup.getColumns().add(shopColumn);
        }

        treeTableView.getColumns().add(shopColumnGroup);
    }

    private void createColumnPrice(TreeTableView<Product> treeTableView) {
        TreeTableColumn<Product, String> priceColumnGroup = new TreeTableColumn<>("Цены");
        TreeTableColumn<Product, String> priceColumnBeginningYear = new TreeTableColumn<>("Начало года");
        TreeTableColumn<Product, String> priceColumnBeginningMonth = new TreeTableColumn<>("Прошлый мес.");
        TreeTableColumn<Product, String> priceColumnCurrentMonth = new TreeTableColumn<>("Текущий мес.");

        priceColumnBeginningYear.setPrefWidth(100);
        priceColumnBeginningMonth.setPrefWidth(100);
        priceColumnCurrentMonth.setPrefWidth(100);

        priceColumnGroup.getColumns().addAll(priceColumnBeginningYear, priceColumnBeginningMonth, priceColumnCurrentMonth);
        treeTableView.getColumns().add(priceColumnGroup);
    }

    //Создание таблицы
    /////////////////////////////////////////////////////////////////
}
