package gui;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;


public class controller {
    @FXML
    private TextField keywordInput;
    @FXML
    private Pane menuPane;
    private boolean isMenuVisible = true;
    private static List<String> dataBaseLines;
    @FXML
    private void submitKeyWord(ActionEvent event) throws IOException {
        String input = keywordInput.getText();
//        System.out.println("Input: " + input);
        if (!Utils.whatIsPath(input) && !input.isEmpty()) {
            System.out.println(input);
            List<String> retrievedData = dataBaseSearch.readDataBase(keywordInput.getText());

            if (retrievedData != null && !retrievedData.isEmpty()) {
                dataBaseLines = retrievedData;
                System.out.println(dataBaseLines);
                switchScene(event, "/showResults.fxml");
            } else {
                keywordInput.setStyle("-fx-background-color: red");
            }
        } else {
            System.out.println("Blank Input");
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
    public static void switchScene(ActionEvent event, String scenePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(controller.class.getResource(scenePath));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void toggleMenu() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(250), menuPane);
        if (isMenuVisible) {
            transition.setToX(menuPane.getWidth());
            System.out.println("Should be visible");
        } else {
            transition.setToX(0);
        }
        transition.play();
        isMenuVisible = !isMenuVisible;
        System.out.println("Menu clicked");
    }
    @FXML
    private void openSupportAlert() {
        popUps("Support", "Supported languages: Python, Java, C.\nYou can add more by creating new keyword files for the wanted language");
    }
    private void popUps(String title, String contents) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contents);
        alert.showAndWait();
    }
}