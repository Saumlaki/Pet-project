module ru.saumlaki.pricedynamics {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires lombok;

    opens ru.saumlaki.pricedynamics to javafx.fxml;
    exports ru.saumlaki.pricedynamics;

    opens ru.saumlaki.pricedynamics.Controller to javafx.fxml;
    exports ru.saumlaki.pricedynamics.Controller;

    opens ru.saumlaki.pricedynamics.Entity to  com.sun.javafx.property;
    exports ru.saumlaki.pricedynamics.Entity;

}
