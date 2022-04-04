package ru.saumlaki.price_dynamic.view.abstracts;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.controllers.list.abstracts.AbstractListController;
import ru.saumlaki.price_dynamic.supporting.AlertMessage;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.io.IOException;
import java.net.URL;


/**Класс реализует базовый загрузчик формы*/
public abstract class AbstractView {

    Object controller;


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


    public void initialize(String viewNameProp) {

        currentStage = new Stage();

        //Получаем путь до формы
        fxmlLoader = new FXMLLoader(Helper.getResourcesURLForPropertyName(viewNameProp));

        //Устанавливаем контроллер
        controller = fxmlLoader.getController();

        //Инициализация
        HBox hBox = null;
        try {
            hBox = fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("Критическая ошибка загрузки формы:" + ex.getMessage());
            AlertMessage.showError("Ошибка загрузки окна: " + viewNameProp, ex.getMessage());
            return;
        }

        scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());
        currentStage.setScene(scene);

        if(controller instanceof AbstractListController)
            ((AbstractListController) controller).setCurrentStage(currentStage);
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
}