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
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.element.ShopElementController;
import ru.saumlaki.price_dynamic.controllers.main.MainController;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.io.IOException;
import java.net.URL;


/**Класс реализует базовый загрузчик формы*/
public abstract class AbstractView {
    @Autowired
    FxWeaver fxWeaver;
    protected String title;
    String iconProp = "StringIcon";
    @Getter
    @Setter
    private FXMLLoader fxmlLoader;

    @Getter
    @Setter
    private Stage currentStage;

    @Getter
    @Setter
    private Stage parentStage;

    private Scene scene;
    boolean isModal;

    @Autowired
    ShopElementController controller;
    Class<ShopElementController> classControllerType = ShopElementController.class;

    public void initialize(String ww) {

        HBox hBox = fxWeaver.loadView(classControllerType);
        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());

        Stage currentStage = new Stage();
        currentStage.setTitle(title);
        currentStage.setScene(scene);

        URL iconURL = Helper.getResourcesURLForPropertyName(iconProp);
        try {
            currentStage.getIcons().add(new Image(iconURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOnKeyPressAction(scene, currentStage);

        controller.setCurrentStage(currentStage);
      //  controller.setObject(object);

        currentStage.initOwner(parentStage);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.showAndWait();
    }

    public void setTitle(String title) {

        currentStage.setTitle(title);
    }

    public void setIcon(String iconNameProp) {

        URL iconURL = Helper.getResourcesURLForPropertyName(iconNameProp);
        try {

            currentStage.getIcons().add(new Image(iconURL.openStream()));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void setParentStage(Stage parentStage, boolean isModal) {

        this.parentStage = parentStage;
        this.isModal = isModal;
    }

    public void show() {

        if (isModal) {

            currentStage.initOwner(parentStage);
            currentStage.initModality(Modality.WINDOW_MODAL);
            currentStage.showAndWait();
        } else {

            currentStage.show();
        }
    }

    void setOnKeyPressAction(Scene scene, Stage stage) {

        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.ESCAPE)) stage.close();
        });}
}