package ru.saumlaki.time_tracker.controllers;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.saumlaki.time_tracker.service.factory.ServiceFactory;
import ru.saumlaki.time_tracker.supporting.Error;

import java.lang.reflect.Field;

/**
 * Класс содержит методы общие для всех форм элементов
 */
public abstract class AbstractElementController<T> {

    //************************************************************************
    // Обязательные элементы данных
    //************************************************************************

    /**
     * Данный элемент являеться основным элеентом формы
     */
    public T element;

    /**
     * Stage текущей формы
     */
    Stage stage;
    /**
     * Scene текущей формы
     */
    Scene scene;

    //************************************************************************
    // Сеттеры
    //************************************************************************

    /**
     * Устанавливает основной Stage
     */
    public void setStage(Stage stage) {

        //Установка значения полей
        this.stage = stage;
        this.scene = stage.getScene();

        //Установка клавиши закрытия формы
        scene.setOnKeyPressed(e -> {

            switch (e.getCode()) {
                case ESCAPE -> stage.close();
            }
        });

        //Установка горячих клавиш
        setMnemonic();
    }

    /**
     * Метод установки основного элемента. Вызов данного метода вызывает метод обновления формы <code>update</code>
     */
    public void setElement(T element) {

        this.element = element;
        updateForm();
    }

    //************************************************************************
    // Методы формы
    //************************************************************************

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
                    Error.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                    return false;
                } else if (fieldObject instanceof String) {
                    if (((String) fieldObject).isEmpty()) {
                        Error.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                        return false;
                    }
                } else if (fieldObject instanceof Integer) {
                    if ((Integer) fieldObject == 0) {
                        Error.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
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
     * @return boolean - возвращает истину если объект прошел проверку на заполненость реквизитов и его удалось сохранить(тут момент - исключения не пробрасываются так что не факт что удалось сохранить, нао будет как-нибудь доделать)
     */
    boolean save(String details) {

        boolean result = true;

        //Обновляем основной элемент формы
        updateElement();

        //Проверка корректного заполнения реквизитов, если все ок то сохраняем и закрываем форму
        if (checkTheDetails(String.valueOf(details))) {
            ServiceFactory.getService(element.getClass()).add(element);
        } else
            result = false;

        return result;
    }

    /**
     * Метод закрывает текущую форму без сохранения объекта
     */
    void closeForm() {

        stage.close();
    }

    //************************************************************************
    // Абстрактные методы
    //************************************************************************

    /**
     * В данном методе необходимо переопределить логику обновдения формы
     */
    public abstract void updateForm();

    /**
     * В данном методе необходимо переопределить логику обновления основного элемента формы.
     */
    public abstract void updateElement();

    /**
     * В данном методе необходимо переопределить горячии клавищи для формы
     */
    public abstract void setMnemonic();

}
