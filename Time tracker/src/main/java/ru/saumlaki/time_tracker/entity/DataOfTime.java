package ru.saumlaki.time_tracker.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

/**
 * Класс содержит информацию о временных затратах на определенный день
 */
public class DataOfTime {

    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private Calendar calendar;

    @Setter
    @Getter
    private Time time;

    @Setter
    @Getter
    private int values;

    public DataOfTime(int id, Calendar calendar, Time time, int values) {
        this.id = id;
        this.calendar = calendar;
        this.time = time;
        this.values = values;
    }


    @Override
    public String toString() {
        return "[" + calendar + "][" + time + "][" + values + "](" + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataOfTime that = (DataOfTime) o;
        return id == that.id||calendar.equals(that.calendar)&&time.equals(that.getTime());
    }
}
