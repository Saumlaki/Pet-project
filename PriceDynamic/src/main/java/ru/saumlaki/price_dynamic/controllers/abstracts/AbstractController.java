package ru.saumlaki.price_dynamic.controllers.abstracts;

import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import lombok.Setter;

/**Реализует общую функциональность для всех контроллеров*/
public abstract class AbstractController {

    /**
     * Текущий stage
     */
    @Setter
    protected Stage currentStage;

    /**
     * Метод содержит операции которые необходимо выполнить при закрытии формы
     */
    public void close() {
    }


}
