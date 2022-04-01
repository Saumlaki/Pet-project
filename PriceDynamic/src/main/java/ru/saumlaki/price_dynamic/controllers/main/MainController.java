package ru.saumlaki.price_dynamic.controllers.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.list.PriceView;
import ru.saumlaki.price_dynamic.view.list.ProductView;
import ru.saumlaki.price_dynamic.view.list.ShopView;


@Component("mainController")
public class MainController extends AbstractController {

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
    }

    //*****

    @FXML
    void shopAddOnAction(ActionEvent event) {

    }

    @FXML
    void shopListOnAction(ActionEvent event) {
        new ShopView().showForm(currentStage);
    }

    @FXML
    void productAddOnAction(ActionEvent event) {

    }

    @FXML
    void productListOnAction(ActionEvent event) {
        new ProductView().showForm(currentStage);
    }

    @FXML
    void priceAddOnAction(ActionEvent event) {

    }

    @FXML
    void priceListOnAction(ActionEvent event) {
        new PriceView().showForm(currentStage);
    }

    @FXML
    void aboutOnAction(ActionEvent event) {

    }

    @FXML
    void exitOnAction(ActionEvent event) {

    }
}
