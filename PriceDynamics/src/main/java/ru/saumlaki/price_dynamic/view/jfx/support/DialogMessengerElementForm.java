package ru.saumlaki.price_dynamic.view.jfx.support;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.saumlaki.price_dynamic.Main;

import java.io.IOException;

@Component
public class DialogMessengerElementForm  {

    @Getter
    String text;

    @Getter
    String errorMessage;

    public static void show(String text, String errorMessage) {

        DialogMessengerElementForm dialogMessengerElementForm = new DialogMessengerElementForm(text, errorMessage);
        dialogMessengerElementForm.createFormError(null,  text,  errorMessage);

        System.out.println(text + ": " + errorMessage);
    }

    public DialogMessengerElementForm(String text, String errorMessage) {
        this.text = text;
        this.errorMessage = errorMessage;
    }

    public DialogMessengerElementForm() {
    }

    private void createFormError(Stage stage, String text, String errorMessage) {

        stage = stage == null ? new Stage() : stage;

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/jfxView/support/dialogMessengerForm.fxml"));

        VBox root = null;
        try {
            root = (VBox) fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("Ошибка создания окна нового элемента 'DialogMessenger': " +  ex.getMessage());
        }

        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
        stage.setTitle("Ошибка");
        stage.setScene(scene);

       // stage.getIcons().add(Bean.iconImage);

      //  Init controller = fxmlLoader.getController();
       // controller.init(this, stage);

        stage.show();
    }
}
