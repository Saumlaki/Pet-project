package ru.saumlaki.price_dynamic.view.jfx.support;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;
import ru.saumlaki.price_dynamic.controller.jfx.MainViewController;
import ru.saumlaki.price_dynamic.controller.jfx.support.ErrorMessageController;
import ru.saumlaki.price_dynamic.entity.Product;
import ru.saumlaki.price_dynamic.view.interfaces.Erroreble;

import java.io.IOException;

@Component
public class ErrorMessageView implements Erroreble {

    @Getter
    String text;

    @Getter
    String errorMessage;

    @Override
    public void error(String text, String errorMessage) {

        this.text = text;
        this.errorMessage = errorMessage;

        show(null);
    }

    @Override
    public void show(Stage stage) {

        //1. Подключение формы
        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/jfxView/support/errorMessage.fxml"));
        VBox root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("-->Ошибка");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        stage.setScene(scene);
        stage.show();

        ((ErrorMessageController)fxmlLoader.getController()).setStage(stage);

        //2. Настройка формы
        ((Label)scene.lookup("#labelErrorShortMessage")).setText(text);
        ((TextArea)scene.lookup("#textErrorMessage")).setText(errorMessage);
    }
}


