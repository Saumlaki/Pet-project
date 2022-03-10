package ru.saumlaki.pricedynamics.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import ru.saumlaki.pricedynamics.entity.Product;
import ru.saumlaki.pricedynamics.entity.Shop;

import java.util.ArrayList;
import java.util.List;

public class MainViewController {

    @FXML
    private TreeTableView<Product> goodsTable;


    public void addElements() {

        //Настройка отображения столбцов таблицы
        //1. Добавление колонок с информацией по товарам
        TreeTableColumn<Product, String> productColumn = new TreeTableColumn<>("Товары");
        productColumn.setCellValueFactory(new TreeItemPropertyValueFactory<Product, String>("name"));

        //1. Добавляем колонки с информацией по магазинам
        List<Shop> shopList = new ArrayList<>();
        shopList.add(new Shop("Пятерочка"));
        shopList.add(new Shop("Дикси"));
        shopList.add(new Shop("Магнит"));

        TreeTableColumn<Product, String> shopColumnGroup = new TreeTableColumn<>("Магазины");
        for (Shop shop : shopList) {
            TreeTableColumn<Product, String> shopColumn = new TreeTableColumn<>(shop.getName());
            shopColumnGroup.getColumns().add(shopColumn);
        }

        //2. Добавление колонок цен
        TreeTableColumn<Product, String> priceColumnGroup = new TreeTableColumn<>("Цены");
        TreeTableColumn<Product, String> priceColumnBeginningYear = new TreeTableColumn<>("Начало года");
        TreeTableColumn<Product, String> priceColumnBeginningMonth = new TreeTableColumn<>("Прошлый месяц");
        TreeTableColumn<Product, String> priceColumnCurrentMonth = new TreeTableColumn<>("Текущий месяц");

        priceColumnGroup.getColumns().addAll(priceColumnBeginningYear, priceColumnBeginningMonth, priceColumnCurrentMonth);

        //3. Добавление колонок изменений цены
        TreeTableColumn<Product, String> flucColumnGroup = new TreeTableColumn<>("Динамика цен");
        TreeTableColumn<Product, String> flucColumnBeginningYear = new TreeTableColumn<>("С начала года");
        TreeTableColumn<Product, String> flucColumnBeginningMonth = new TreeTableColumn<>("С прошлого месяца");

        flucColumnGroup.getColumns().addAll(flucColumnBeginningYear, flucColumnBeginningMonth);

        //4. Добавление колонок на форму
        goodsTable.getColumns().addAll(productColumn, shopColumnGroup, priceColumnGroup, flucColumnGroup);

        //Добавление строк
        TreeItem<Product> productTreeItem = new TreeItem<Product>(new Product("товары народного потребления"));
        TreeItem<Product> productTreeItem1 = new TreeItem<Product>(new Product("Хлеб"));
        TreeItem<Product> productTreeItem2 = new TreeItem<Product>(new Product("Вода"));

        productTreeItem.getChildren().addAll(productTreeItem1, productTreeItem2);

        goodsTable.setRoot(productTreeItem);
    }
}

