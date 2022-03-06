module com.saumlaki.timer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.persistence;

    opens com.saumlaki.timer to javafx.fxml;
    exports com.saumlaki.timer;
}