package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.view.main.MainView;


import java.net.URL;

public class PriceDynamic extends Application{

    public static void main(String[] args) {

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        new MainView().showForm(stage);





    }
}
