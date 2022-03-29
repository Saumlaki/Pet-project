package ru.saumlaki.price_dynamic.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.saumlaki.price_dynamic.Helper;

import java.io.IOException;
import java.net.URL;


public abstract class AbstractMainView2 {

    public FXMLLoader showForm(Stage currentStage, Stage parentStage, Object object, String viewNameProp, String title, String iconNameProp) {

        currentStage = currentStage == null ? new Stage() : currentStage;

        FXMLLoader fxmlLoader = null;

        //Получаем путь до формы
        URL url = getClass().getClassLoader().getResource(Helper.getPropertyForName(viewNameProp));
        fxmlLoader = new FXMLLoader(url);

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
            URL iconURL = getClass().getClassLoader().getResource(Helper.getPropertyForName(iconNameProp));
            try {
                currentStage.getIcons().add(new Image(iconURL.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//        //Установка основного элемента формы
//        if (fxmlLoader.getController() instanceof HasElement) {
//            ((HasElement<?>) fxmlLoader.getController()).setElement();
//            AbstractElementController controller = fxmlLoader.getController();
//            controller.setStage(stage);
//            controller.setElement(element);
//        }
//
//        //Модальность окна если нужно
//        if (parentStage != null) {
//            stage.initOwner(parentStage);
//            stage.initModality(Modality.WINDOW_MODAL);
//            stage.showAndWait();
//        } else {
//            stage.show();
//        }

        return fxmlLoader;
    }
}

