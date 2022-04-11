package ru.saumlaki.price_dynamic.view.element;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.PriceDynamic;
import ru.saumlaki.price_dynamic.controllers.element.ProductElementController;
import ru.saumlaki.price_dynamic.entity.Product;
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
public class ProductElementStarter extends AbstractView {

        @Autowired
        FxWeaver fxWeaver;

        String title = "Товар";
        String iconProp = "StringIcon";
        String beanName ="productElementController";
        Class<ProductElementController> classControllerType = ProductElementController.class;

        public void showForm(Stage parentStage, Product object)  {

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

            Main.applicationContext.getBean(beanName, classControllerType).setCurrentStage(currentStage);
            Main.applicationContext.getBean(beanName, classControllerType).setObject(object);

            currentStage.initOwner(parentStage);
            currentStage.initModality(Modality.WINDOW_MODAL);
            currentStage.showAndWait();
        }

    void setOnKeyPressAction(Scene scene, Stage stage) {

        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.ESCAPE)) stage.close();
        });}
}