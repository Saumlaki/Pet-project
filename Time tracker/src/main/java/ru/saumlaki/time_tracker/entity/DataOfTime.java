package ru.saumlaki.time_tracker.entity;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
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

    public DataOfTime() {
    }

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
        return id == that.id || calendar.equals(that.calendar) && time.equals(that.getTime());
    }

    public String getDateToStr() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(calendar.getTime());
    }

    public String getValueToStr() {

        int hors = values / 3600;
        int min = (values - hors * 3600) / 60;
        int sec = (values - hors * 3600 - min * 60);

        String result = hors == 0 ? "" : (hors + " ч.");
        result += min == 0 ? "" : " " + (min + " мин.");
        result += sec == 0 ? "" : " " + (sec + " с.");
        return result;
    }
}
