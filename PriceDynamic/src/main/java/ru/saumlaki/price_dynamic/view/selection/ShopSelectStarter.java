package ru.saumlaki.price_dynamic.view.selection;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.list.ShopListController;
import ru.saumlaki.price_dynamic.controllers.selection.ShopSelectController;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;
import ru.saumlaki.price_dynamic.supporting.Helper;

import java.io.IOException;
import java.net.URL;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы списка магазинов
 */
@Component
public class ShopSelectStarter {

    @Autowired
    FxWeaver fxWeaver;

    @Autowired
    ShopSelectController shopSelectController;

    public void showForm(Stage parentStage, Selectable resultAction) {

        HBox hBox = fxWeaver.loadView(ShopSelectController.class);
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

        shopSelectController.setCurrentStage(currentStage);
        shopSelectController.setResultAction(resultAction);

        currentStage.initOwner(parentStage);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.showAndWait();
    }
}
