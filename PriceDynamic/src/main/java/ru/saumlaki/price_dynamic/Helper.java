package ru.saumlaki.price_dynamic;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Helper {

    public static Helper instance;

    public static Helper getInstance() {
        if(instance==null) instance = new Helper();
        return instance;
    }

    private Helper() {

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

    public static URL getResourcesURLForPropertyName(String propertyName) {

        String property = getPropertyForName(propertyName);
        return  getInstance().getClass().getClassLoader().getResource(property);
    }


}
