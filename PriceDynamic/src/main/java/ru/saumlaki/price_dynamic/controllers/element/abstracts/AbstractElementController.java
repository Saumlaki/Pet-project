package ru.saumlaki.price_dynamic.controllers.element.abstracts;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;
import ru.saumlaki.price_dynamic.service.interfaces.Service;
import ru.saumlaki.price_dynamic.supporting.AlertMessage;

import java.lang.reflect.Field;
import java.sql.SQLDataException;

public abstract class AbstractElementController <T> extends AbstractController {

    public T object;

    @Autowired
    public ObservableList<T> obsList;

    public Service service;

    @FXML
    protected TextField description;

    @FXML
    protected Button cancel;

    @FXML
    protected Button save;

    @FXML
    void cancelOnAction(ActionEvent event) {
        closeForm();
    }

    @FXML
    void saveOnAction(ActionEvent event) {
        try {
            saveObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверки корректно заполнения реквизитов. Доступ к реквизитам осуществляется с помощью рефликсии. Простые реквизиты(String, int и пр.) проверяються на пустые значения
     * сложные проверяються на <code>null</code>
     *
     * @param details список реквизтов строкой которые проверяем("реквизит1, реквизит2, реквизит 3")
     * @return boolean
     */
    protected void checkTheDetails(String details) throws SQLDataException {

        String[] detailsArr = details.split(",");
        for (String currentDetail : detailsArr) {

            try {
                Field field = object.getClass().getDeclaredField(currentDetail.trim());
                field.setAccessible(true);
                Object fieldObject = field.get(object);

                if (fieldObject == null) {
                    AlertMessage.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                    throw new SQLDataException();
                } else if (fieldObject instanceof String) {
                    if (((String) fieldObject).isEmpty()) {
                        AlertMessage.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                        throw new SQLDataException();
                    }
                } else if (fieldObject instanceof Integer) {
                    if ((Integer) fieldObject == 0) {
                        AlertMessage.showError("Не заполнено свойство", "Заполните реквизит \"" + currentDetail + "\"");
                        throw new SQLDataException();
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (SQLDataException e) {
                throw e;
            }
        }
    }

    //****

    protected void save(String details) throws SQLDataException {

        //Обновляем основной элемент формы
        updateElement();

        //Проверка корректного заполнения реквизитов, если все ок то сохраняем и закрываем форму
        try {
            checkTheDetails(String.valueOf(details));
            ServiceFactory.getService(object.getClass()).add(object);
        } catch (SQLDataException ex) {
            throw ex;
        }
    }

    public void closeForm() {
        currentStage.close();
    }

    public void setObject(T object) {
        this.object = object;
        updateForm();
    }

    //****

    public abstract void saveObject();

    public abstract void updateForm();

    public abstract void updateElement();
}


