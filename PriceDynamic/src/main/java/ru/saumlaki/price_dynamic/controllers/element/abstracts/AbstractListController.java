package ru.saumlaki.price_dynamic.controllers.element.abstracts;

import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.saumlaki.price_dynamic.entity.interfaces.CheckDetails;
import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;
import ru.saumlaki.price_dynamic.view.AlertMessage;

import java.lang.reflect.Field;
import java.util.List;

public abstract class AbstractListController<T> {

    private Stage currentStage;
    private Stage parentStage;
    private Scene currentScene;
    private T object;
    
    /**
     * Метод проверки корректно заполнения реквизитов. Доступ к реквизитам осуществляется с помощью рефликсии. Простые реквизиты(String, int и пр.) проверяються на пустые значения
     * сложные проверяються на <code>null</code>
     *
     * @param details список реквизтов строкой которые проверяем("реквизит1, реквизит2, реквизит 3")
     * @return boolean
     */
    boolean checkTheDetails(List<String> details) {

        for (String currentDetail : details) {
            try {
                Field field = object.getClass().getDeclaredField(currentDetail.strip());
                field.setAccessible(true);
                Object fieldObject = field.get(object);

                if (fieldObject == null) {
                    AlertMessage.showError("Не заполнено значение реквизита", "Заполните реквизит \"" + currentDetail + "\"");
                    return false;
                } else if (fieldObject instanceof String) {
                    if (((String) fieldObject).isEmpty()) {
                        AlertMessage.showError("Не заполнено значение реквизита", "Заполните реквизит \"" + currentDetail + "\"");
                        return false;
                    }
                } else if (fieldObject instanceof Integer) {
                    if ((Integer) fieldObject == 0) {
                        AlertMessage.showError("Не заполнено значение реквизита", "Заполните реквизит \"" + currentDetail + "\"");
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
     * @return boolean - возвращает истину если объект прошел проверку на корректное заполнение реквизитов и был сохранен
     */
    boolean save() {

        boolean result = true;

        //Обновляем основной элемент формы
        updateObject();

        if(object instanceof CheckDetails){
            checkTheDetails(((CheckDetails) object).details());
        }else{
            ServiceFactory.getService(object.getClass()).add(object);
        }

        return result;
    }

    /**
     * Метод закрывает текущую форму без сохранения объекта
     */
    void closeForm() {

        currentStage.close();
    }

    /**
     * Устанавливает основной Stage
     */
    public void setStage(Stage stage) {

        //Установка значения полей
        this.currentStage = stage;
        this.currentScene = stage.getScene();

        //Установка клавиши закрытия формы
        currentScene.setOnKeyPressed(e -> {

            switch (e.getCode()) {
                case ESCAPE -> currentStage.close();
            }
        });

        //Установка горячих клавиш
        setMnemonic();
    }

    /**
     * Метод установки основного элемента. Вызов данного метода вызывает метод обновления формы <code>update</code>
     */
    public void setObject(T element) {

        this.object = element;
        updateForm();
    }

    /**
     * В данном методе необходимо переопределить логику обновдения формы
     */
    public abstract void updateForm();

    /**
     * В данном методе необходимо переопределить логику обновления основного элемента формы.
     */
    public abstract void updateObject();

    /**
     * В данном методе необходимо переопределить горячии клавищи для формы
     */
    public abstract void setMnemonic();
}
