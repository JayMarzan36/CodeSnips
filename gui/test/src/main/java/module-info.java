module jay.test {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens jay.test to javafx.fxml;
    exports jay.test;
}