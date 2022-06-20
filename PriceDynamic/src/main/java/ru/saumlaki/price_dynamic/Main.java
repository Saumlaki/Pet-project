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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        Main.applicationContext = new SpringApplicationBuilder().sources(Main.class).run("");
        Application.launch(PriceDynamic.class, args);
    }

    public static ConfigurableApplicationContext applicationContext;

    //***СПИСКИ ДАННЫЕ КОТОРЫХ ОТОБРАЖАЕМ НА ФОРМЕ
    private static ObservableList<Shop> shopObsList =  FXCollections.observableArrayList();
    private static ObservableList<Product> productObsList =  FXCollections.observableArrayList();
    private static ObservableList<Price> priceObsList =  FXCollections.observableArrayList();

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

    //****HIBERNATE AND SQL***

    @Bean
    public DriverManagerDataSource dataSource() {

        //1. Получаем путь до БД во временных файлах
        String dbAddress = getTempFileCatalog() + "\\DBPriceDynamic.sqlite";

        //2. Проверяем что файл базы данных существует. Если его нет, то создаем его
        createDBFile(dbAddress);

        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.sqlite.JDBC");
        driverManagerDataSource.setUrl("jdbc:sqlite:" + dbAddress);

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

    //****МЕТОДЫ РАБОТЫ С ФАЙЛОВОЙ СИСТЕМОЙ

    /**
     * Метод возвращает путь до каталога временных файлов
     */
    private static String getTempFileCatalog() {
        String tempFile = "";
        try {
            tempFile = Files.createTempFile("", ".tmp").toFile().getParentFile().getCanonicalPath();
        } catch (IOException e) {
            System.out.println("err.Ошибка получения каталога временных файлов");
            e.printStackTrace();
        }
        return tempFile;
    }

    /**
     * Метод создает файл базы данных по полному пути <code>dbName</code> в случае его отсутствия
     */
    private static void createDBFile(String dbName) {
        System.out.println("Проверка наличия файла БД:");
        System.out.println("--Путь: " + dbName);

        File dbFile = new File(dbName);

        if (!dbFile.exists()) {
            try {
                System.out.println("Файл БД не обнаружен. Пытаюсь создать");
                if (!dbFile.createNewFile()) {
                    System.out.println("err.Не смог создать файл БД");
                }
            } catch (IOException ex) {
                System.out.println("err.Ошибка создания файла БД");
                System.out.println("--" + ex.getMessage());
            }
        }
    }
}