package ru.saumlaki.price_dynamic.view.abstracts;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.PriceDynamic;
import ru.saumlaki.price_dynamic.controllers.main.MainController;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.io.IOException;
import java.net.URL;


/**Класс реализует базовый загрузчик формы*/
public abstract class AbstractView {

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



    public void initialize(String ww) {

        currentStage = new Stage();

        FxWeaver fxWeaver = Main.applicationContext.getBean(FxWeaver.class);

        //Инициализация
        HBox hBox = fxWeaver.loadView(MainController.class);


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
}