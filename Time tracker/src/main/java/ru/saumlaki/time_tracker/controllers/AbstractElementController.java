package ru.saumlaki.time_tracker.controllers;

import javafx.stage.Stage;
import ru.saumlaki.time_tracker.DialogMessengerElementForm;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;

import java.lang.reflect.Field;

/**
 * Класс содержит методы общие для всех форм элементов
 */
public abstract class AbstractElementController<T> {

    //***Элемент данных"***

    public T element;//Основной элемент формы

    Stage stage;//Основное окно формы

    public void setStage(Stage stage) {

        this.stage = stage;
    }

    /**
     * Метод закрывает текущую форму без сохранения объекта
     */
    void closeForm() {

        stage.close();
    }

    /**
     * Метод проверки корректно заполнения реквизитов. Доступ к реквизитам осуществляется с помощью рефликсии. Простые реквизиты(String, int и пр.) проверяються на пустые значения
     * сложные проверяються на <code>null</code>
     *
     * @param details список реквизтов строкой которые проверяем("реквизит1, реквизит2, реквизит 3")
     * @return boolean
     */
    boolean checkTheDetails(String details) {

        String[] detailsArr = details.split(",");
        for (String currentDetail : detailsArr) {

            try {
                Field field = element.getClass().getDeclaredField(currentDetail.strip());
                field.setAccessible(true);
                Object fieldObject = field.get(element);

                if (fieldObject == null) {
                    DialogMessengerElementForm.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                    return false;
                } else if (fieldObject instanceof String) {
                    if (((String) fieldObject).isEmpty()) {
                        DialogMessengerElementForm.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                        return false;
                    }
                } else if (fieldObject instanceof Integer) {
                    if ((Integer) fieldObject == 0) {
                        DialogMessengerElementForm.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                        return false;
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    /**
     * Метод сохраняет объекта основной элемент формы в БД.
     *
     * @param details строка реквизитов для проверки корректного заполнения
     */
    void save(String details) {

        //Обновляем основной элемент формы
        updateElement();

        //Проверка корректного заполнения реквизитов, если все ок то сохраняем и закрываем форму
        if (checkTheDetails(String.valueOf(details))) {
            ServiceFactory.getService(element.getClass()).add(element);
            closeForm();
        }
    }

    /**
     * Метод сохраняет объекта основной элемент формы в БД
     */
    void save(String... details) {

        //Проверка корректного заполнения реквизитов
        checkTheDetails(String.valueOf(details));

        ServiceFactory.getService(element.getClass()).add(element);
    }

    /**
     * Метод установки основного элемента. Вызов данного метода вызывает метод обновления формы <code>update</code>
     */
    public void setElement(T element) {

        this.element = element;
        updateForm();
    }

    /**
     * Метод обновления формы. В данном методе рекомендуется располагать обработчики связанные с изменением значения основного элемента формы
     */
    public abstract void updateForm();

    /**
     * Метод обновления основного элемента.
     */
    public abstract void updateElement();

}
