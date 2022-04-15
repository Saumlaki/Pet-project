package ru.saumlaki.price_dynamic.supporting;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class JFXTextField implements ChangeListener {

    TextField textField;

    public JFXTextField(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue observableValue, Object oldValueTmp, Object newValueTmp) {

        String oldValue = (String) oldValueTmp;
        String newValue = (String) newValueTmp;

        if (!newValue.matches("[0-9|.]+")) {
            newValue = oldValue;
           // return;
        }

        //Обработку удаления точки
        if (oldValue.indexOf(".") != -1 && newValue.indexOf(".") == -1) {
            if (oldValue.length() - 1 == newValue.length()) {
                newValue = oldValue.substring(0,textField.getCaretPosition()-2) + "." + oldValue.substring(textField.getCaretPosition());
            }
        }

        //Преобразование пустой строки(""->0.00)
        if (newValue.isEmpty()) {
            newValue = "0.00";
        }

        //Если строка начиналась с 0.** то следующая строка должна быть типа 1.**, а не 10.**
        if (oldValue.startsWith("0.") && !newValue.startsWith("0.")&&textField.getCaretPosition()!=0) {
            newValue = newValue.replace("0.", ".");
        }

        //Добавление дробной части(78->78.00)
        if (newValue.matches("[0-9]+")) {
            newValue += ".00";
            textField.setText(newValue);

            Platform.runLater(() -> textField.positionCaret(1));
        }

        //Добавление нулей в дробную часть(78.->78.00)
        if (newValue.matches("[0-9]+\\.")) {
            newValue += ".00";
        }

        //Добавление нулей в дробную часть(78.0->78.00)
        if (newValue.matches("[0-9]+\\.[0-9]")) {
            newValue += "0";
            if (oldValue.matches("[0-9]+\\.[0-9][0-9]")) {
                Platform.runLater(() -> textField.positionCaret(textField.getCaretPosition() - 1));
            }
        }

        //Убираем лидирующие нули в случае их присутствия(001.78->1.78)
        while (true) {
            if (newValue.startsWith("0") && !newValue.startsWith("0.")) {
                newValue = newValue.substring(1);
            } else
                break;
        }

        //Преобразование целой части в случаее ее отсутствия(.78->0.78)
        if (newValue.matches("\\.[0-9]+")) {
            newValue = "0" + newValue;
        }

        //Работа с корректой в дробной части
        String[] mass = newValue.split("\\.");

        if (textField.getCaretPosition() > oldValue.length() - 3 && mass[1].length() == 3 && textField.getCaretPosition() != oldValue.length()) {

            //Удаляем старое значение(1.23->1.1->2<-3)
            newValue = newValue.substring(0, textField.getCaretPosition() + 1) +
                    newValue.substring(textField.getCaretPosition() + 2);
            //Двигаем курсор назад
            Platform.runLater(() -> textField.positionCaret(textField.getCaretPosition() + 1));
        }

        //Убираем лишнии символы дроби
        mass = newValue.split("\\.");
        if (mass[1].length() > 2) {
            newValue = mass[0] + "." + mass[1].substring(0, mass[1].length() - 1);
        }

        textField.setText(newValue);
    }
}

