package ru.saumlaki.price_dynamic.view.interfaces;

import javafx.stage.Stage;
import ru.saumlaki.price_dynamic.controller.interfaces.Refreshable;

public interface Showable {
    void show(Stage stage, Refreshable refreshable);
}
