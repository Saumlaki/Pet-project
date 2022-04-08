package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.saumlaki.price_dynamic.controllers.list.PriceListController;
import ru.saumlaki.price_dynamic.controllers.list.ProductListController;
import ru.saumlaki.price_dynamic.controllers.list.ShopListController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.service.PriceServiceImpl;
import ru.saumlaki.price_dynamic.service.ProductServiceImpl;
import ru.saumlaki.price_dynamic.service.ShopServiceImpl;
import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;
import ru.saumlaki.price_dynamic.service.interfaces.PriceService;
import ru.saumlaki.price_dynamic.service.interfaces.ProductService;
import ru.saumlaki.price_dynamic.service.interfaces.ShopService;
import ru.saumlaki.price_dynamic.view.main.MainViewStarter;

import java.util.Properties;


public class PriceDynamic extends Application {

    ShopService shopService = Main.applicationContext.getBean("shopServiceImpl", ShopServiceImpl.class);
    ProductService productService = Main.applicationContext.getBean("productServiceImpl", ProductServiceImpl.class);
    PriceService priceService = Main.applicationContext.getBean("priceServiceImpl", PriceServiceImpl.class);

    ObservableList<Shop> shops = Main.applicationContext.getBean("shopObsList", ObservableList.class);
    ObservableList<Product> products = Main.applicationContext.getBean("productObsList", ObservableList.class);
    ObservableList<Price> prices = Main.applicationContext.getBean("priceObsList", ObservableList.class);

    MainViewStarter mainViewStarter = Main.applicationContext.getBean("mainViewStarter", MainViewStarter.class);

    @Override
    public void init() {

        shops.addAll(shopService.getAll());
        products.addAll(productService.getAll());
        prices.addAll(priceService.getAll());
    }

    @Override
    public void start(Stage stage) {
        mainViewStarter.showForm();
    }

    @Override
    public void stop() throws Exception {
        Main.applicationContext.close();
        Platform.exit();
    }
}
