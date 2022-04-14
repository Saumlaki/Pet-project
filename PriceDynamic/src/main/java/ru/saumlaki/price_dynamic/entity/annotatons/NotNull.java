package ru.saumlaki.price_dynamic.entity.annotatons;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Маркерная аннотация. При сохранении данное поле проверяется на NULL
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface NotNull {
}
