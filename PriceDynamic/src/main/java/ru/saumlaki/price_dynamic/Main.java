package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.entity.Shop;
import ru.saumlaki.price_dynamic.supporting.SimpleObject;

import java.util.Properties;

@SpringBootApplication
public class Main {

    public static ConfigurableApplicationContext applicationContext;
    private static ObservableList<Shop> shopObsList =  FXCollections.observableArrayList();
    private static ObservableList<Product> productObsList =  FXCollections.observableArrayList();
    private static ObservableList<Price> priceObsList =  FXCollections.observableArrayList();

    public static void main(String[] args) {

        //Тест работы с сампл обжект
        SimpleObject<Shop> shopSimpleObject = new SimpleObject<>(new Shop(1, "My Shop"));







        Main.applicationContext = new SpringApplicationBuilder().sources(Main.class).run("");
        Application.launch(PriceDynamic.class, args);
    }

    //****HIBERNATE AND SQL***

    @Bean
    public ObservableList<Shop> shopObsList() {
        return shopObsList;
    }

    @Bean
    public ObservableList<Product> productObsList() {
        return productObsList;
    }

    @Bean
    public ObservableList<Price> priceObsList() {
        return priceObsList;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.sqlite.JDBC");
        driverManagerDataSource.setUrl("jdbc:sqlite:C:/Git/Pet-project/PriceDynamic/src/main/resources/DB.sqlite");

        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());

        Properties pr = new Properties();
        pr.setProperty("hibernate.hbm2ddl.auto","update" );
        localSessionFactoryBean.setHibernateProperties(pr);

        localSessionFactoryBean.setAnnotatedClasses(Shop.class,Product.class,Price.class);

        return  localSessionFactoryBean;
    }
}