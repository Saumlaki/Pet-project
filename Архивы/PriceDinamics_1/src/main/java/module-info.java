module ru.saumlaki.pricedinamics {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires lombok;

    opens ru.saumlaki.pricedinamics to javafx.fxml;
    exports ru.saumlaki.pricedinamics;
}