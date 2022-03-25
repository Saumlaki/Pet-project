package ru.saumlaki.time_tracker.supporting;

import lombok.Getter;
import lombok.Setter;
import ru.saumlaki.time_tracker.controllers.MainController;

/**
 * Класс реализующий функцию секундомера
 */
public class TimerWatch  implements Runnable{

    //Определяет статус таймера - истина запущен, ложь - остановлен
    @Getter
    private boolean run  =false;
    int value;
    @Setter
    MainController mainController;
    Thread thread;


    /**Активирует таймер если он не запушен или останавливает если он запущен*/

    public void startTimer() {

        thread = new Thread(this);
        thread.start();
        run = true;
    }

    public int stopTimer() {

        thread.interrupt();
        run = false;
        return value;
    }


    @Override
    public void run() {

        value = 0;

        while (true) {
            try {
                Thread.sleep(1000);
                value++;
                mainController.setTime(value);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

//    @Override
//    public void run() {
//
//        //Получаем выбранны й тип времени
//        Time currentTime = Bean.timeTrackerController.getCurrentTime();
//
//        Calendar beginDay = SimpleCalendar.getBeginningCurrentDay();
//        TimeData timeData = new TimeData(Bean.nextTimeDataId,currentTime, 0, beginDay, true);
//
//        //Добавлем данный тип времени в карту учета времени
//        if (!Bean.timeDataList.contains(timeData)) {
//            Bean.timeDataList.add(timeData);
//            Bean.nextTimeDataId++;
//        }
//
//        //Получаем ссылку на текущее время
//        TimeData currentTimeData = Bean.timeDataList.stream().filter(a -> a.getTime().equals(currentTime) && a.getCalendar().equals(beginDay)).findFirst().get();
//
//        //Начинаем цикл по подсчету времени
//        while (true) {
//
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                break;
//            }
//            Bean.seconds += 1;
//            if (Bean.seconds == 60) {
//                Bean.seconds = 0;
//                Bean.minute++;
//            }
//
//            if (Bean.minute == 60) {
//                Bean.minute = 0;
//                Bean.hour++;
//            }
//
//            if (Bean.hour == 24) {
//                Bean.hour = 0;
//            }
//
//            //Добавляем секунду ко времени
//            currentTimeData.setValues(currentTimeData.getValues() + 1);
//
//            Bean.timeTrackerController.setTime(currentTimeData.getValues());
//        }
//    }
//
//    public static String getTimePresents(int seconds) {
//
//        int locHours = seconds / 3600;
//        int locMinutes = (seconds - locHours * 60) / 60;
//        int locSeconds = seconds - locHours * 60 - locMinutes * 60;
//
//        String result = "";
//
//        if (locHours != 0) result += String.valueOf(locHours) + " ч. ";
//        if (locMinutes != 0 || locHours != 0) result += String.valueOf(locMinutes) + " мин. ";
//        result += String.valueOf(locSeconds) + " сек. ";
//        return result;
//    }
}





/**
    setZeroTime();
        timeTrackerController.setTimeCBox(FXCollections.<Time>observableArrayList(time));

        ObservableList<Time> list = FXCollections.observableArrayList(TimeTracker.timeTracker.time);
        timeTrackerController.setTimeCBox(time);

        timeTrackerController.setTypePieCBox();
        timeTrackerController.setTimePie();*/