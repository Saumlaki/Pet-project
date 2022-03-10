module ru.saumlaki.pricedynamics {

//requires - указывает модули от которых зависит текущий модуль
//Opens - указываем какие пакеты открыты для reflection доступа
//exports - открывает доступ к пакету

//JAVA FX
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;


    //DATABASE
    requires org.hibernate.orm.core;
    requires org.hibernate.commons.annotations;
    requires org.xerial.sqlitejdbc;
    requires sqlite.dialect;
    requires java.persistence;
    requires java.xml.bind;
    requires java.sql;
    requires java.naming;


    //SPRING
    requires spring.context;
    requires spring.tx;
    requires spring.core;
    requires spring.beans;
    requires java.transaction;

//ПРОЧЕЕ
    requires lombok;


//Проверить надо ли...
//
    requires org.jboss.logging;
//    requires org.junit.jupiter.engine;
//    requires org.junit.jupiter.api;
    requires jdk.internal.le;

    exports ru.saumlaki.pricedynamics;
    exports ru.saumlaki.pricedynamics.entity;
    exports ru.saumlaki.pricedynamics.config;
    exports ru.saumlaki.pricedynamics.controller;

    opens ru.saumlaki.pricedynamics to javafx.fxml,spring.core, org.jboss.logging;
    opens ru.saumlaki.pricedynamics.controller to javafx.fxml;
    opens ru.saumlaki.pricedynamics.entity to com.sun.javafx.property;
    opens ru.saumlaki.pricedynamics.config to spring.core;

}
