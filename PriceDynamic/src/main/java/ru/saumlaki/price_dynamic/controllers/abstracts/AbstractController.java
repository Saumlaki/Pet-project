package ru.saumlaki.price_dynamic.controllers.abstracts;

import javafx.stage.Stage;
import lombok.Setter;

/**Реализует общую функциональность для всех контроллеров*/
public abstract class AbstractController {

    @Setter
    protected  Stage currentStage;
}
