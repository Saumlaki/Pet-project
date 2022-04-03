package ru.saumlaki.price_dynamic.controllers.element.abstracts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;

public class AbstractElementController <T> extends AbstractController {

    T object;

    @FXML
    private Button cancel;

    @FXML
    private Button save;

    @FXML
    void cancelOnAction(ActionEvent event) {

        currentStage.close();
    }

    @FXML
    void saveOnAction(ActionEvent event) {

        ServiceFactory.getService(object.getClass()).add(object);
    }

    public  void 


}
