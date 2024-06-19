package com;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class controller implements applicationAware{
    private application app;

    @FXML
    private TextField keywordInput;
    @FXML
    private Pane menuPane;
    private boolean isMenuVisible = true;
    private static List<String> dataBaseLines;


    @Override
    public void setApplication(application app) {
        this.app = app;
    }

    @FXML
    private void submitKeyWord(ActionEvent event) throws IOException {
        String input = keywordInput.getText();
        if (!Utils.whatIsPath(input) && !input.isEmpty()) {
            List<String> retrievedData = dataBaseSearch.readDataBase(keywordInput.getText());
            if (retrievedData != null && !retrievedData.isEmpty()) {
                dataBaseLines = retrievedData;
                app.switchScene("/fxml/showResults.fxml");
            } else {
                keywordInput.setStyle("-fx-background-color: red");
            }
        } else {
            keywordInput.setStyle("-fx-background-color: red");
        }
    }
    @FXML
    private void changeInputColor() {
        keywordInput.setStyle("-fx-background-color: white");
    }
    @FXML
    public static List<String> getDataBaseLines() {
        List<String> dataBaseLines1 = dataBaseLines;
        return dataBaseLines1;
    }

    @FXML
    private void toggleMenu() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(250), menuPane);
        if (isMenuVisible) {
            transition.setToX(menuPane.getWidth());
        } else {
            transition.setToX(0);
        }
        transition.play();
        isMenuVisible = !isMenuVisible;
    }
    @FXML
    private void addButton() throws IOException {
        app.switchScene("/fxml/addData.fxml");
    }
    @FXML
    private void helpButton() {
        popUps("Help", "Documentation and help on GitHub page");
    }
    @FXML
    private void openSupportAlert() {
        popUps("Support", "Supported languages: Python, Java, C.\n \nYou can expand language support by creating new keyword files for the wanted language.\n \nNOTE: you will need to remake the database if you add new keywords.");
    }
    private void popUps(String title, String contents) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contents);
        alert.showAndWait();
    }
}