package ru.saumlaki.price_dynamic.view.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.saumlaki.price_dynamic.Helper;
import ru.saumlaki.price_dynamic.controllers.main.AbstractController;
import ru.saumlaki.price_dynamic.view.AlertMessage;

import java.io.IOException;
import java.net.URL;


public abstract class AbstractView {

    public FXMLLoader showForm(Stage currentStage, Stage parentStage, String viewNameProp, String title, String iconNameProp) {

        currentStage = currentStage == null ? new Stage() : currentStage;

        FXMLLoader fxmlLoader = null;

        //Получаем путь до формы
        fxmlLoader = new FXMLLoader(Helper.getResourcesURLForPropertyName(viewNameProp));

        HBox hBox = null;
        try {
            hBox = fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            AlertMessage.showError("Ошибка загрузки окна " + viewNameProp, ex.getMessage());
        }

        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());
        currentStage.setTitle(title);
        currentStage.setScene(scene);

        if(iconNameProp!=null&&!iconNameProp.isEmpty()){
            URL iconURL = Helper.getResourcesURLForPropertyName(iconNameProp);
            try {
                currentStage.getIcons().add(new Image(iconURL.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //Установка stage
     //   ((AbstractController)fxmlLoader.getController()).setStage(currentStage);

        //Модальность окна, если нужно
        if (parentStage != null) {
            currentStage.initOwner(parentStage);
            currentStage.initModality(Modality.WINDOW_MODAL);
            currentStage.showAndWait();
        } else {
            currentStage.show();
        }

        return fxmlLoader;
    }
}

