package ru.saumlaki.price_dynamic.view.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import ru.saumlaki.price_dynamic.Helper;
import ru.saumlaki.price_dynamic.controllers.Init;
import ru.saumlaki.price_dynamic.view.AlertMessage;

import java.io.IOException;
import java.net.URL;


public abstract class AbstractView {

    private Stage currentStage;
    private Stage parentStage;

    @Getter
    private FXMLLoader fxmlLoader;
    private Scene scene;
    boolean isModal;


    /**
     * Метод инициализации формы
     */
    public void init(String viewNameProp) {
        init(new Stage(), viewNameProp);
    }

    public void init(Stage currentStage, String viewNameProp) {

        this.currentStage = currentStage==null?new Stage():currentStage;

        //Получаем путь до формы
        fxmlLoader = new FXMLLoader(Helper.getResourcesURLForPropertyName(viewNameProp));

        HBox hBox = null;
        try {
            hBox = fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            AlertMessage.showError("Ошибка загрузки окна " + viewNameProp, ex.getMessage());
        }

        scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());
        currentStage.setScene(scene);
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

    public <T> T getController() {

        return fxmlLoader.getController();
    }
}