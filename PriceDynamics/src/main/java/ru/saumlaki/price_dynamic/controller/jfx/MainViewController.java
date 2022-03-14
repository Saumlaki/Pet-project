package ru.saumlaki.price_dynamic.controller.jfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import lombok.Getter;
import ru.saumlaki.price_dynamic.PriceDynamics;
import ru.saumlaki.price_dynamic.controller.interfaces.Initializable;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;

import java.util.List;

public class MainViewController implements Initializable, Refreshable {

    Refreshable parentView;

    //*************************************************
    //Идентификаторы элементов формы

    @FXML
    private TreeTableView<TableRow> table;//Используем внутренний класс так как структура отображения имеет более сложную форму вывода данных

    //*************************************************
    //Обработчики событий элементов формы - кнопки

    @FXML
    void cityOpenList(ActionEvent event) {
        Showable showable = (Showable) PriceDynamics.applicationContext.getBean("cityListView");
        showable.show(null, this);
    }

    @FXML
    void shopOpenList(ActionEvent event) {

        Showable showable = (Showable) PriceDynamics.applicationContext.getBean("shopList");
        showable.show(null, this);
    }

    @FXML
    void seasonOpenList(ActionEvent event) {
        Showable showable = (Showable) PriceDynamics.applicationContext.getBean("seasonListView");
        showable.show(null, this);
    }

    @FXML
    void productOpenList(ActionEvent event) {
        Showable showable = (Showable) PriceDynamics.applicationContext.getBean("productListView");
        showable.show(null, this);
    }

    @FXML
    void priceOpenList(ActionEvent event) {
        Showable showable = (Showable) PriceDynamics.applicationContext.getBean("priceListView");
        showable.show(null, this);
    }

    @FXML
    void about(ActionEvent event) {
        Showable showable = (Showable) PriceDynamics.applicationContext.getBean("about");
        showable.show(null, this);
    }

    @FXML
    void close(ActionEvent event) {

    }

    //*************************************************
    //Прочие методы

    //*************************************************
    //-Создание таблицы

    private void creteColumn() {

        //Очищаем старую структуру
        table.getColumns().removeAll();

        //Создаем новую
        createColumnProduct();
        createColumnShop();
        createColumnPrice();
    }

    private void createColumnProduct() {
        TreeTableColumn<TableRow, String> productColumn = new TreeTableColumn<>("Товары");
        productColumn.setCellValueFactory(StringCellDataFeatures -> new SimpleStringProperty(StringCellDataFeatures.getValue().getValue().getName()));

        productColumn.setPrefWidth(500);

        table.getColumns().add(productColumn);
    }

    private void createColumnShop() {


        List<Shop> shopList = ((ShopService) PriceDynamics.applicationContext.getBean("shopServiceImpl")).getAll();

        TreeTableColumn<TableRow, String> shopColumnGroup = new TreeTableColumn<>("Магазины");

        for (Shop shop : shopList) {
            TreeTableColumn<TableRow, String> shopColumn = new TreeTableColumn<>(shop.getName());
            shopColumnGroup.getColumns().add(shopColumn);
        }

        table.getColumns().add(shopColumnGroup);
    }

    private void createColumnPrice() {
        TreeTableColumn<TableRow, String> priceColumnGroup = new TreeTableColumn<>("Цены");
        TreeTableColumn<TableRow, String> priceColumnBeginningYear = new TreeTableColumn<>("Начало года");
        TreeTableColumn<TableRow, String> priceColumnBeginningMonth = new TreeTableColumn<>("Прошлый мес.");
        TreeTableColumn<TableRow, String> priceColumnCurrentMonth = new TreeTableColumn<>("Текущий мес.");

        priceColumnBeginningYear.setPrefWidth(100);
        priceColumnBeginningMonth.setPrefWidth(100);
        priceColumnCurrentMonth.setPrefWidth(100);

        priceColumnGroup.getColumns().addAll(priceColumnBeginningYear, priceColumnBeginningMonth, priceColumnCurrentMonth);
        table.getColumns().add(priceColumnGroup);
    }


    //*************************************************
    //Реализация интерфейса Refreshable


    @Override
    public void refresh() {

        //В случае необходимости обновления перерисовываем форму
        //Под обновлением у нас могут быть
        //1.Добавили/убрали магазин
        //2.Добавили убрали товар

        creteColumn();


        //Если есть форма владелец то обновляем ее
        if(parentView!=null) parentView.refresh();
    }


    //*************************************************
    //Реализация интерфейса Initializable

    @Override
    public void initialization(Refreshable parentView) {

        this.parentView = parentView;

        creteColumn();
    }


    //*************************************************
    //Внутренний класс для отображения в таблице

    class TableRow {

        @Getter
        Product product;

        @Getter
        String name;

    }
}