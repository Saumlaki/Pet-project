package ru.saumlaki.time_tracker.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.TimeTracker;
import ru.saumlaki.time_tracker.controllers.AbstractElementController;
import ru.saumlaki.time_tracker.supporting.Error;

import java.io.IOException;
import java.net.URL;



public abstract class AbstractView {

    public FXMLLoader showForm(Stage stage, Stage parentStage, Object element, String formName, String title, String css, String icon) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = null;

        //Получаем путь до формы
        URL url = getClass().getClassLoader().getResource(TimeTracker.getPropertyForName(formName));
        fxmlLoader = new FXMLLoader(url);

        HBox hBox = null;
        try {
            hBox = (HBox) fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Error.showError("Ошибка загрузки окна " + formName, ex.getMessage());
        }

        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());
        stage.setTitle(title);
        stage.setScene(scene);

        if (css != null && !css.isEmpty())
            scene.getStylesheets().add(css);
        if(icon!=null&&!icon.isEmpty()){
            URL iconURL = getClass().getClassLoader().getResource(TimeTracker.getPropertyForName(icon));
            try {
                stage.getIcons().add(new Image(iconURL.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Заполнение полей контроллера
        if (fxmlLoader.getController() instanceof AbstractElementController) {
            AbstractElementController controller = fxmlLoader.getController();
            controller.setStage(stage);
            controller.setElement(element);
        }

        //Модальность окна если нужно
        if (parentStage != null) {
            stage.initOwner(parentStage);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
        } else {
            stage.show();
        }

        return fxmlLoader;
    }
}

