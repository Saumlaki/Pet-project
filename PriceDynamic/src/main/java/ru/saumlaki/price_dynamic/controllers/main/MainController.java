package ru.saumlaki.price_dynamic.controllers.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.list.PriceListStarter;
import ru.saumlaki.price_dynamic.view.list.ProductListStarter;
import ru.saumlaki.price_dynamic.view.list.ShopListStarter;


@Component("mainController")
@FxmlView("MainView.fxml")
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
        System.out.println("");
    }

    @FXML
    void shopListOnAction(ActionEvent event) {
       Main.applicationContext.getBean("shopListStarter", ShopListStarter.class).showForm(currentStage);
    }

    @FXML
    void productAddOnAction(ActionEvent event) {

    }

    @FXML
    void productListOnAction(ActionEvent event) {
        Main.applicationContext.getBean("productListStarter", ProductListStarter.class).showForm(currentStage);
    }

    @FXML
    void priceAddOnAction(ActionEvent event) {

    }

    @FXML
    void priceListOnAction(ActionEvent event) {
        Main.applicationContext.getBean("priceListStarter", PriceListStarter.class).showForm(currentStage);
    }

    @FXML
    void aboutOnAction(ActionEvent event) {

    }

    @FXML
    void exitOnAction(ActionEvent event) {

    }
}
