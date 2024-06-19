module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;


    opens com to javafx.fxml;

    exports com;
}