package ru.saumlaki.price_dynamic.supporting;

import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/***Вспомогательный класс в который вынесен весь вспомогательный функционал*/
public class Helper {

    public static Helper instance;

    public static ApplicationContext applicationContext;

    private Helper() {

    }

    public static Helper getInstance() {
        if (instance == null) instance = new Helper();
        return instance;
    }

    private final static String PROPERTY_NAME = "settings.properties";


    private static Properties properties;

    /**
     * Метод получения настройки по имени
     */
    public static String getPropertyForName(String propertyName) {

        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_NAME));
            } catch (IOException ex) {
                System.out.println("err.Ошибка загрузки файла настроек.");
                System.out.println("--" + ex.getMessage());
            }
        }

        return properties.getProperty(propertyName);
    }

    /**
     * Метод получения ресурса по имени
     */
    public static URL getResourcesURLForPropertyName(String propertyName) {

        String property = getPropertyForName(propertyName);
        return getInstance().getClass().getClassLoader().getResource(property);
    }
}
