package ru.saumlaki.price_dynamic.controllers.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.about.AboutStarter;
import ru.saumlaki.price_dynamic.view.element.PriceElementStarter;
import ru.saumlaki.price_dynamic.view.element.ProductElementStarter;
import ru.saumlaki.price_dynamic.view.element.ShopElementStarter;
import ru.saumlaki.price_dynamic.view.list.PriceListStarter;
import ru.saumlaki.price_dynamic.view.list.ProductListStarter;
import ru.saumlaki.price_dynamic.view.list.ShopListStarter;


@Component
@FxmlView("MainView.fxml")
public class MainController extends AbstractController {

    @Autowired
    ShopListStarter shopListStarter;

    @Autowired
    ProductListStarter productListStarter;

    @Autowired
    PriceListStarter priceListStarter;

    @Autowired
    ShopElementStarter shopElementStarter;

    @Autowired
    ProductElementStarter productElementStarter;

    @Autowired
    PriceElementStarter priceElementStarter;

    @Autowired
    AboutStarter aboutStarter;

    @FXML
    private TreeTableView<PriceDataElement> priceData;

    @FXML
    private Button shopAdd;

    @FXML
    private Button productAdd;

    @FXML
    private Button priceAdd;

    //*****

    @FXML
    private Menu setting;

    @FXML
    private MenuItem shopList;

    @FXML
    private MenuItem productList;

    @FXML
    private MenuItem priceList;

    @FXML
    private MenuItem about;

    //*****

    @FXML
    public void initialize() {

        shopAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        priceAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        productAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        setting.setGraphic(new ImageView(Helper.getPropertyForName("SettingIcon")));
        about.setGraphic(new ImageView(Helper.getPropertyForName("AboutIcon")));

        shopList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        productList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        priceList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));


        PriceDataElement priceDataElement = new PriceDataElement();
        priceDataElement.setProduct(new Product(1,"wewew"));





        TreeTableColumn columnShop = new TreeTableColumn("Магазин");
        TreeTableColumn columnProduct = new TreeTableColumn("Товар");

        TreeTableColumn columnPrice = new TreeTableColumn("Цена");
        TreeTableColumn columnPrice1= new TreeTableColumn("Начало года");
        TreeTableColumn columnPrice2 = new TreeTableColumn("Предыдущий месяц");
        TreeTableColumn columnPrice3 = new TreeTableColumn("Текущая");
        columnPrice.getColumns().addAll(columnPrice1,columnPrice2,columnPrice3);

        TreeTableColumn columnDynamic = new TreeTableColumn("Динамика");
        TreeTableColumn columnDynamic1 = new TreeTableColumn("С начала года");
        TreeTableColumn columnDynamic2 = new TreeTableColumn("С прошлого месяца");
        columnDynamic.getColumns().addAll(columnDynamic1, columnDynamic2);

        priceData.getColumns().addAll(columnShop, columnProduct, columnPrice, columnDynamic);

    }

    //*****

    @FXML
    void shopAddOnAction(ActionEvent event) {
        shopElementStarter.showForm(currentStage, new Shop());
    }

    @FXML
    void shopListOnAction(ActionEvent event) {
        shopListStarter.showForm(currentStage);
    }

    @FXML
    void productAddOnAction(ActionEvent event) {
        productElementStarter.showForm(currentStage, new Product());
    }

    @FXML
    void productListOnAction(ActionEvent event) {
        productListStarter.showForm(currentStage);
    }

    @FXML
    void priceAddOnAction(ActionEvent event) {
        priceElementStarter.showForm(currentStage, new Price());
    }

    @FXML
    void priceListOnAction(ActionEvent event) {
        priceListStarter.showForm(currentStage);
    }

    @FXML
    void aboutOnAction(ActionEvent event) {
        aboutStarter.showForm(currentStage);
    }

    @FXML
    void exitOnAction(ActionEvent event) {
currentStage.close();
    }


    class PriceDataElement{
    @Getter
            @Setter
        Product product;

    }
}
