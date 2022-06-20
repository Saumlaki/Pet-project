package ru.saumlaki.price_dynamic.controllers.element.abstracts;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import org.springframework.beans.factory.annotation.Autowired;
import ru.saumlaki.price_dynamic.controllers.abstracts.AbstractController;
import ru.saumlaki.price_dynamic.service.factory.ServiceFactory;
import ru.saumlaki.price_dynamic.supporting.Helper;
import ru.saumlaki.price_dynamic.supporting.SimpleObject;
import java.sql.SQLDataException;

public abstract class AbstractElementController<T> extends AbstractController {

    //***ОБЪЕКТЫ С КОТОРЫМИ РАБОТАЕТ ФОРМА

    /**Протообъект - проекция данных объекта на специальный объект формы*/
    public SimpleObject<T> protoObject;

    @Autowired
    public ServiceFactory serviceFactory;

    //***БАЗОВЫЕ ПОЛЯ И КНОПКИ КОТРЫЕ ДОЛЖНЫ БЫТЬ НА ЛЮБОЙ ФОРМЕ

    @FXML
    protected TextField id;

    @FXML
    protected TextField description;

    @FXML
    protected Button cancel;

    @FXML
    protected Button save;

    //***НИЦИАЛИЗАЦИЯ
    @FXML
    public void initialize()
    {
        save.setGraphic(new ImageView(Helper.getPropertyForName("SaveIcon")));
    }

    //***ДЕЙСТВИЯ БАЗОВЫХ КНОПОК

    @FXML
    public void  saveOnAction(ActionEvent event) throws SQLDataException {
        updateElement();
        if (protoObject.isCorrectly()) {
            //Сохраняем дубликат объекта для того что бы в случае ошибки транзакции не было рассогласованных данных между формой и БД
            serviceFactory.getService(protoObject.getObject().getClass()).add(protoObject.getObjectCopy());
            currentStage.close();
        }
    }

    @FXML
    void cancelOnAction(ActionEvent event) {
        currentStage.close();
    }

    //***ПРОЧИЕ МЕТОДЫ

    public void setObject(T object) {
        this.protoObject = new SimpleObject<T>(object);
        updateForm();
    }

    //***АБСТРАКТНЫЕ МЕТОДЫ

    /**
     * Метод служит для обновления значений элементов формы данными протообъекта
     */
    public abstract void updateForm();

    /**
     * Метод служит для обновления значений полей протообъекта
     */
    public abstract void updateElement();
}
