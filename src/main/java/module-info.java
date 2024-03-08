module com.gui.jvio {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.gui.jvio to javafx.fxml;
    exports com.gui.jvio;
}