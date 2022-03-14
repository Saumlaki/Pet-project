package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.saumlaki.price_dynamic.config.DataConfig;
import ru.saumlaki.price_dynamic.view.interfaces.Showable;

/**
 * Основной класс запуска программы
 */
public class PriceDynamics extends Application {

    public static AnnotationConfigApplicationContext applicationContext;

    public static void main(String[] args) {

        applicationContext = new AnnotationConfigApplicationContext(DataConfig.class);
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Showable showable = (Showable) PriceDynamics.applicationContext.getBean("main");
        showable.show(stage, null);
    }
}