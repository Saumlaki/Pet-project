package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.saumlaki.price_dynamic.view.jfx.MainView;

/**
 * Основной класс запуска программы
 */
public class Main extends Application {

    public static ApplicationContext applicationContext;

    public static void main(String[] args) {

        applicationContext = new AnnotationConfigApplicationContext("ru/saumlaki/price_dynamic/config/DataConfig.java");
    }

    @Override
    public void start(Stage stage) {
        MainView mainView = (MainView) applicationContext.getBean("mainView");
        mainView.show(stage);
    }
}