package ru.saumlaki.price_dynamic.view.main;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.main.MainController;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.io.IOException;
import java.net.URL;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации главной формы
 */
@Component("mainViewStarter")
public class MainViewStarter {

    @Autowired
    FxWeaver fxWeaver;

    public void showForm() {

        HBox hBox = fxWeaver.loadView(MainController.class);
        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());

        Stage currentStage = new Stage();
        currentStage.setTitle("Динамика цен");
        currentStage.setScene(scene);

        URL iconURL = Helper.getResourcesURLForPropertyName("Icon");
        try {
            currentStage.getIcons().add(new Image(iconURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.applicationContext.getBean("mainController", MainController.class).setCurrentStage(currentStage);
        currentStage.show();
    }
}
