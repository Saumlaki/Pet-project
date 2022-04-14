package ru.saumlaki.price_dynamic.controllers.about;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import javax.swing.text.html.ImageView;
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
    void onAction(ActionEvent event) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI(gitLink.getText());
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


