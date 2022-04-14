package ru.saumlaki.price_dynamic.starters.list;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.list.ShopListController;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.io.IOException;
import java.net.URL;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы списка магазинов
 */
@Component("shopListStarter")
public class ShopListStarter {

    @Autowired
    FxWeaver fxWeaver;

    public void showForm(Stage parentStage) {

        HBox hBox = fxWeaver.loadView(ShopListController.class);
        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());

        Stage currentStage = new Stage();
        currentStage.setTitle("Список магазинов");
        currentStage.setScene(scene);

        URL iconURL = Helper.getResourcesURLForPropertyName("ListIcon");
        try {
            currentStage.getIcons().add(new Image(iconURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOnKeyPressAction(scene, currentStage);

        Main.applicationContext.getBean("shopListController", ShopListController.class).setCurrentStage(currentStage);

        currentStage.initOwner(parentStage);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.showAndWait();
    }

    void setOnKeyPressAction(Scene scene, Stage stage) {

        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.ESCAPE)) stage.close();
        });}
}
