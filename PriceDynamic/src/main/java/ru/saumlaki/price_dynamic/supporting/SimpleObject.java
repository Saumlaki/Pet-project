package ru.saumlaki.price_dynamic.supporting;

import lombok.SneakyThrows;
import ru.saumlaki.price_dynamic.entity.annotatons.NotEmpty;
import ru.saumlaki.price_dynamic.entity.annotatons.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс представляет собой универсальный объект.
 * Посредством данного объекта происходит общение с пользовательскими формами
 */
public class SimpleObject<T> {

    T object;
    List<InnerField> innerFields;

    public SimpleObject(T object) {
        this.object = object;
        this.innerFields = new ArrayList();
        parsingObject();
    }

    protected void parsingObject() {

        for (Field field : object.getClass().getDeclaredFields()) {
            innerFields.add(new InnerField(object, field));
        }
    }

    @SneakyThrows
    public Object getValue(String name) {

        Object value = innerFields.stream().filter(a -> a.name.equals(name)).findFirst().get().value;
        Class clazz = innerFields.stream().filter(a -> a.name.equals(name)).findFirst().get().type;

        if (value == null && clazz.getEnclosingConstructor() == null) {
            if (clazz.getName().endsWith("Integer")) return 0;
            if (clazz.getName().endsWith("LocalDate")) return LocalDate.now();
            else return "";
        } else
            return value == null ?
                    clazz.getConstructor(new Class[]{}).newInstance() :
                    value;
    }

    public void setValue(String name, Object value) {
        innerFields.stream().filter(a -> a.name.equals(name)).findFirst().get().value = value;
    }

    /**
     * Метод проверяет корректность заполнения полей
     */
    public boolean isCorrectly() {

        String errorMessage = "";

        for (InnerField innerField : innerFields) {

            if (innerField.notNull && innerField == null) errorMessage = "поле " + innerField.name + " не заполнено \n";
            else if (innerField.notEmpty) {
                if (innerField.value instanceof String && ((String) innerField.value).isEmpty()) {
                    errorMessage = "поле " + innerField.name + "  не заполнено \n";
                }
            }
        }

        if (errorMessage.isEmpty()) return true;
        else
            AlertMessage.showError("Ошибка заполнения", errorMessage);
        return false;
    }

    /**
     * Метод создает копию объекта с актуальными заполненными полями
     */
    public T getObjectCopy() {

        T objectCopy = null;

        try {
            objectCopy = (T) object.getClass().getConstructor(new Class[]{}).newInstance();

            for (Field field : objectCopy.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(objectCopy, innerFields.stream().filter(a -> a.name.equals(field.getName())).findFirst().get().value);
            }

        } catch (Exception ex) {

        }
        return objectCopy;
    }

    /**
     * Метод возвращает объект на основании которого создан данный протообъект
     */
    public T getObject() {
        return object;
    }

    /**
     * Класс описывает поле объекта
     */
    protected class InnerField {

        String name;
        Object value;
        Class type;

        boolean notNull;
        boolean notEmpty;

        public InnerField(T object, Field field) {

            this.name = field.getName();
            this.type = field.getType();

            if (field.isAnnotationPresent(NotEmpty.class)) notEmpty = true;
            if (field.isAnnotationPresent(NotNull.class)) notNull = true;

            try {
                field.setAccessible(true);
                this.value = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}