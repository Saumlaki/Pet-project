package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.view.main.MainViewStarter;


public class PriceDynamic extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        Main.applicationContext = new SpringApplicationBuilder().sources(Main.class).run("");

        //Заполнение списков значений
        Main.applicationContext.getBean("shopObsList", ObservableList.class).addAll(
                Main.applicationContext.getBean("shopServiceImpl", ShopServiceImpl.class).getAll());

        Main.applicationContext.getBean("productObsList", ObservableList.class).addAll(
                Main.applicationContext.getBean("productServiceImpl", ProductServiceImpl.class).getAll());

        Main.applicationContext.getBean("priceObsList", ObservableList.class).addAll(
                Main.applicationContext.getBean("priceServiceImpl", PriceServiceImpl.class).getAll());
    }

    @Override
    public void start(Stage stage) {
        Main.applicationContext.getBean("mainViewStarter", MainViewStarter.class).showForm();
    }

    @Override
    public void stop() throws Exception {
        Main.applicationContext.close();
        Platform.exit();
    }
}



