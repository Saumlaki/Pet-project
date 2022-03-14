package ru.saumlaki.price_dynamic.view.jfx.support;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;

import java.io.IOException;

@Component
public class SaveNewElementView {

    public void show(Stage stage) {

        if (stage == null) stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/jfxView/support/saveNewElement.fxml"));
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
    }
}
