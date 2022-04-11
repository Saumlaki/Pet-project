package ru.saumlaki.price_dynamic.controllers.about;


import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@FxmlView("About.fxml")
public class AboutController {

    @FXML
    private Hyperlink gitLink;

    @FXML
    public void initialize() {

        gitLink.setOnAction(e-> {
            try {
                Desktop desktop = Desktop.getDesktop();
                URI uri = new URI(gitLink.getText());
                desktop.browse(uri);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}


