package ru.saumlaki.price_dynamic.controllers.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import ru.saumlaki.price_dynamic.Helper;
import ru.saumlaki.price_dynamic.controllers.Init;
import ru.saumlaki.price_dynamic.view.list.ShopView;


public class MainController implements Init {


    @FXML
    private MenuItem about;

    @FXML
    private Button priceAdd;

    @FXML
    private MenuItem priceList;

    @FXML
    private Button productAdd;

    @FXML
    private MenuItem productList;

    @FXML
    private Menu setting;

    @FXML
    private Button shopAdd;

    @FXML
    private MenuItem shopList;

    @FXML
    void shopOpenList(ActionEvent event) {


        new ShopView().showForm();

    }

    @Override
    public void init() {

        shopAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        priceAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
        productAdd.setGraphic(new ImageView(Helper.getPropertyForName("AddElementIcon")));
setting.setGraphic(new ImageView(Helper.getPropertyForName("SettingIcon")));
        about.setGraphic(new ImageView(Helper.getPropertyForName("AboutIcon")));


        shopList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        productList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));
        priceList.setGraphic(new ImageView(Helper.getPropertyForName("ListIcon")));



    }

}
