package ru.saumlaki.price_dynamic.view.element;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controllers.element.PriceElementController;
import ru.saumlaki.price_dynamic.entity.Price;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

import java.io.IOException;
import java.net.URL;

/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы элемента
 */
@Component
public class PriceElementStarter extends AbstractView {

        @Autowired
        FxWeaver fxWeaver;

        String title = "Магазин";
        String iconProp = "StringIcon";
        String beanName ="priceElementController";
        Class<PriceElementController> classControllerType = PriceElementController.class;

        public void showForm(Stage parentStage, Price object)  {

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

            Main.applicationContext.getBean(beanName, classControllerType).setCurrentStage(currentStage);
            Main.applicationContext.getBean(beanName, classControllerType).setObject(object);

            currentStage.initOwner(parentStage);
            currentStage.initModality(Modality.WINDOW_MODAL);
            currentStage.showAndWait();
        }
}