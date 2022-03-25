package ru.saumlaki.time_tracker.supporting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.saumlaki.time_tracker.view.ErrorView;

/**
 * Класс ошибки. Данный класс идет как заглушка для реализации абстрактного класса <code>AbstractElementController</code>
 */
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    @Getter
    @Setter
    String errorShortMessage;

    @Getter
    @Setter
    String errorMessage;

    public static void showError(String errorShortMessage, String errorMessage) {

        new ErrorView().showForm(null, new Error(errorShortMessage, errorMessage));
    }

    public static void showError(String message) {

        new ErrorView().showForm(null, new Error(message, message));
    }
}
