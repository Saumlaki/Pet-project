package ru.saumlaki.time_tracker.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс описывает вид времени(работа на основной работе, отдых, игры в ПК, подработка и пр.).
 * Каждый тип времени работы имеет обобщенный тип времени(работа, отдых и пр.)
 *
 * @see TypeOfTime
 */
public class Time {

    @Setter
    @Getter
    int id;

    @Setter
    @Getter
    TypeOfTime typeOfTime;

    @Setter
    @Getter
    String description;

    public Time() {
    }

    public Time(int id, String description, TypeOfTime typeOfTime) {
        this.id = id;
        this.description = description;
        this.typeOfTime = typeOfTime;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time that = (Time) o;
        return id == that.id || description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
