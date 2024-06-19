module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;


    opens gui to javafx.fxml;

    exports gui;
}