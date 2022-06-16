package ru.saumlaki.time_tracker.supporting;

import lombok.Getter;
import ru.saumlaki.time_tracker.controllers.MainController;

/**
 * Класс реализующий функцию секундомера
 */
public class TimerWatch  implements Runnable{
    @Getter
    volatile int  value;
    MainController mainController;

    public TimerWatch(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void run() {
        value = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000);
                value++;
                mainController.setTime(value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}




