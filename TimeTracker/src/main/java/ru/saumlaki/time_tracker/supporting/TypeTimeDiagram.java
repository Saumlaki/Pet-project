package ru.saumlaki.time_tracker.supporting;

/**
 * Перечисление описывает типы диаграмм отображаемых на основном экране формы
 */
public enum TypeTimeDiagram {

    TypeTime("По типу времени"),
    Time("По виду времени");

    String description;

    TypeTimeDiagram(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
