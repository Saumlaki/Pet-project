package ru.saumlaki.price_dynamic.starters.abstracts;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.element.ShopElementController;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.io.IOException;
import java.net.URL;


/**
 * Класс реализует базовый загрузчик формы
 */
public abstract class AbstractViewProto<T> {

    @Autowired
    FxWeaver fxWeaver;

    protected Stage currentStage;
    protected String title;
    protected String iconProp;

    public void initialize(T controller) {

        HBox hBox = fxWeaver.loadView(controller.getClass());
        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());

        currentStage = new Stage();
        currentStage.setTitle(title);
        currentStage.setScene(scene);

        URL iconURL = Helper.getResourcesURLForPropertyName(iconProp);
        try {
            currentStage.getIcons().add(new Image(iconURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOnKeyPressAction(scene, currentStage);
    }

    public void show() {
        currentStage.show();
    }

    public void show(Stage parentStage) {
        currentStage.initOwner(parentStage);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.showAndWait();
    }

    void setOnKeyPressAction(Scene scene, Stage stage) {

        scene.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) stage.close();
        });
    }
}