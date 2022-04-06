package ru.saumlaki.price_dynamic.view.list;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.list.PriceListController;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

import java.io.IOException;
import java.net.URL;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы списка цен
 */
@Component
public class PriceListStarter extends AbstractView {

    @Autowired
    FxWeaver fxWeaver;

    public void showForm(Stage parentStage) {

        HBox hBox = fxWeaver.loadView(PriceListController.class);
        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());

        Stage currentStage = new Stage();
        currentStage.setTitle("Список цен");
        currentStage.setScene(scene);

        URL iconURL = Helper.getResourcesURLForPropertyName("ListIcon");
        try {
            currentStage.getIcons().add(new Image(iconURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Main.applicationContext.getBean("priceListController", PriceListController.class).setCurrentStage(currentStage);

        currentStage.initOwner(parentStage);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.showAndWait();
    }
}
