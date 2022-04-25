package ru.saumlaki.price_dynamic.supporting;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;


public class MaskField extends TextField {

    /**
     * Поле маски
     */
    private StringProperty mask;
    //Спец символы ввода

    @Override
    public void replaceText(int start, int end, String numberStr) {

        //Проверка вводимого символа на число
        if (isNotFormatNumber(numberStr))
            return;

        //Проверка на то что текст был стерт и введено новое число
        if (start == 0 && end == getText().length()) {
            setText(numberStr + ".00");
            positionCaret(1);
            return;
        }

        String oldText = getText();
        Integer caretPosition = getCaretPosition();

        //Проверка на точ тои идет ввод нового числа с нуля
        if (oldText.startsWith("0.") && !numberStr.isEmpty()) {
            oldText = oldText.replace("0.", numberStr + ".");
            setText(oldText);
            positionCaret(1);
            return;
        }

        //Печать числа
        if (!numberStr.isEmpty()) {

            //Целая часть
            if (end <= oldText.indexOf(".")) {
                oldText = oldText.substring(0, start) + numberStr + oldText.substring(end);
                setText(oldText);
                positionCaret(caretPosition + 1);
            }
            //Дробная часть
            else {

                oldText = oldText.substring(0, start) + numberStr + oldText.substring(end + 1);
                setText(oldText);
                positionCaret(caretPosition + 1);
            }
        }
        //Удаление числа
        else {
            //Целая часть
            if (end <= oldText.indexOf(".")) {
                oldText = oldText.substring(0, start) + numberStr + oldText.substring(end);

                if (oldText.matches("\\.[0-9]{2}")) oldText = "0" + oldText;
                setText(oldText);
                positionCaret(caretPosition - 1);
            }
            //Дробная часть
            else {

                oldText = oldText.substring(0, start) + 0 + oldText.substring(end);
                setText(oldText);
                positionCaret(caretPosition);
            }
        }
    }

    /**
     * Метод установки значения в поле
     */
    public final void setNumber(String numberStr) {

        numberStr = examNull(numberStr);
        numberStr = examEmpty(numberStr);
        numberStr = examNumNoFraction(numberStr);

        setText(numberStr);
    }

    /**
     * Метод обрабатывает параметр "mask" указанный в FXML файле
     */
    public final void setMask(String value) {
        if (mask == null) mask = new SimpleStringProperty();
        mask.set(value);
    }

    public final String getMask() {
        return mask.getValue();
    }

    //Список методов проверки и форматирования введенного текста

    /**
     * Проверка на то что число не являеться нашим форматным числом
     */
    protected boolean isNotFormatNumber(String value) {
        return (!(value.matches("[0-9]+\\.[0-9]{2}") ||
                value.matches("[0-9]+\\.[0-9]{1}") ||
                value.matches("[0-9]+\\.") ||
                value.matches("[0-9]+"))
                && !value.isEmpty());
    }

    /**
     * Проверка ввода на NULL(NULL->0.00)
     */
    protected String examNull(String value) {
        if (value == null) return "0.00";
        else return value;
    }

    /**
     * Проверка ввода на пустое значение(""->0.00)
     */
    protected String examEmpty(String value) {
        if (value.isEmpty()) return "0.00";
        else return value;
    }

    /**
     * Проверка корректного ввода дробной части (1.2->1.20)
     */
    protected String examNumNoFraction(String value) {
        if (value.matches("[0-9]+")) return value + ".00";
        if (value.matches("[0-9]+\\.")) return value + "00";
        if (value.matches("[0-9]+\\.[1-9]")) return value + "0";
        if (value.matches("[0-9]+\\.[1-9]{2}")) return value;
        if (value.matches("[0-9]+\\.[1-9]+")) return value.substring(0, value.indexOf(".") + 3);
        else return value;
    }

    /**
     * Проверка ввода нового целого числа(12.34->5)
     */
    protected String examNewIntValue(String oldText, String newText) {
        if (oldText.matches("[0-9]+\\.[0-9]{2}") && newText.matches("[0-9]")) {
            return newText + ".00";
        } else return oldText;

    }
}

