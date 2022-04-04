package ru.saumlaki.price_dynamic;

import javafx.application.Application;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
public class Main {

    public static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {

        Application.launch(PriceDynamic.class, args);
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
        localSessionFactoryBean.setAnnotatedPackages("ru/saumlaki/price_dynamic/entity");
        return  localSessionFactoryBean;
    }
}