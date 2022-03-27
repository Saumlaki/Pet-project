package ru.saumlaki.time_tracker.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**Класс описывает возможные типы времени.
 * Допустим мы можем работать как по основной работе, так и по подработке. В этом случае у нас будет 2 Time с одним TimeType.
 * @see Time
 */
public class TypeOfTime {

    @Setter
    @Getter
    int id;

    @Setter
    @Getter
    String description;

    public TypeOfTime() {
    }

    public TypeOfTime(int id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfTime that = (TypeOfTime) o;
        return id == that.id||description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
