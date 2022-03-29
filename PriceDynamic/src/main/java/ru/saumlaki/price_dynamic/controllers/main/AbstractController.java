package ru.saumlaki.price_dynamic.controllers.main;

import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class AbstractController {

    private Stage currentStage;
    private Stage parentStage;
    private Scene currentScene;



    /**
     * Метод сохраняет объекта основной элемент формы в БД.
     *
     * @return boolean - возвращает истину если объект прошел проверку на корректное заполнение реквизитов и был сохранен
     */


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
     * В данном методе необходимо переопределить логику обновдения формы
     */
    public abstract void updateForm();


    /**
     * В данном методе необходимо переопределить горячии клавищи для формы
     */
    public abstract void setMnemonic();
}
