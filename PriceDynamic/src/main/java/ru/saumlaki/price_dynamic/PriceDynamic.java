package ru.saumlaki.price_dynamic;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import ru.saumlaki.price_dynamic.view.main.MainViewStarter;

public class PriceDynamic extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws Exception {
        Main.applicationContext = new SpringApplicationBuilder().sources(Main.class).run("");
    }

    @Override
    public void start(Stage stage) {
        Main.applicationContext.getBean("mainView", MainViewStarter.class).showForm();
    }

    @Override
    public void stop() throws Exception {
        Main.applicationContext.close();
        Platform.exit();
    }
}



