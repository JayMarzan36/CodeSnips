module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    exports gui;
    opens gui to javafx.fxml;
}