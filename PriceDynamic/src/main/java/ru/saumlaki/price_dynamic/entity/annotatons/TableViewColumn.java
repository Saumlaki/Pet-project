package ru.saumlaki.price_dynamic.entity.annotatons;


import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Маркерная аннотация. Указывает что данное поле должно быть включено в качестве колонки в списке объектов по умолчанию.
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface TableViewColumn {
    String name() default "";
    int order() default 0;

}
