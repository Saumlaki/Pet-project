package ru.saumlaki.price_dynamic.view.selection;

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
import ru.saumlaki.price_dynamic.controllers.list.ProductListController;
import ru.saumlaki.price_dynamic.controllers.selection.ProductSelectController;
import ru.saumlaki.price_dynamic.controllers.selection.interfaces.Selectable;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.view.abstracts.AbstractView;

import java.io.IOException;
import java.net.URL;
/**
 * 2022.04.04
 */

/**
 * Класс инициализации формы выбора продуктов
 */
@Component
public class ProductSelectStarter extends AbstractView {

    @Autowired
    FxWeaver fxWeaver;
    @Autowired
    ProductSelectController productSelectController;

    public void showForm(Stage parentStage, Selectable resultAction) {

        HBox hBox = fxWeaver.loadView(ProductSelectController.class);
        Scene scene = new Scene(hBox, hBox.getPrefWidth(), hBox.getPrefHeight());

        Stage currentStage = new Stage();
        currentStage.setTitle("Список выбора товаров");
        currentStage.setScene(scene);

        URL iconURL = Helper.getResourcesURLForPropertyName("ListIcon");
        try {
            currentStage.getIcons().add(new Image(iconURL.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setOnKeyPressAction(scene, currentStage);

        productSelectController.setCurrentStage(currentStage);
        productSelectController.setResultAction(resultAction);

        currentStage.initOwner(parentStage);
        currentStage.initModality(Modality.WINDOW_MODAL);
        currentStage.showAndWait();
    }

    void setOnKeyPressAction(Scene scene, Stage stage) {

        scene.setOnKeyPressed(e -> {
            if(e.getCode().equals(KeyCode.ESCAPE)) stage.close();
        });}
}
