package ru.saumlaki.price_dynamic.controllers.element.abstracts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;
import ru.saumlaki.price_dynamic.supporting.AlertMessage;

import java.lang.reflect.Field;

public abstract class AbstractElementController <T> extends AbstractController {

    public T object;

    @FXML
    private Button cancel;

    @FXML
    private Button save;

    @FXML
    void cancelOnAction(ActionEvent event) {
        currentStage.close();
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        saveObject();
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
                Field field = object.getClass().getDeclaredField(currentDetail.trim());
                field.setAccessible(true);
                Object fieldObject = field.get(object);

                if (fieldObject == null) {
                    AlertMessage.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                    return false;
                } else if (fieldObject instanceof String) {
                    if (((String) fieldObject).isEmpty()) {
                        AlertMessage.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                        return false;
                    }
                } else if (fieldObject instanceof Integer) {
                    if ((Integer) fieldObject == 0) {
                        AlertMessage.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
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
    protected boolean save(String details) {

        boolean result = true;

        //Обновляем основной элемент формы
        updateElement();

        //Проверка корректного заполнения реквизитов, если все ок то сохраняем и закрываем форму
        if (checkTheDetails(String.valueOf(details))) {
            ServiceFactory.getService(object.getClass()).add(object);
        } else
            result = false;

        return result;
    }

    public abstract void saveObject();

    /**
     * В данном методе необходимо переопределить логику обновдения формы
     */
    public abstract void updateForm();

    /**
     * В данном методе необходимо переопределить логику обновления основного элемента формы.
     */
    public abstract void updateElement();

    public void setObject(T object) {
        this.object = object;
        updateForm();
    }
}
