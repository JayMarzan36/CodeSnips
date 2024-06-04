package gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class controller {
    @FXML
    private TextField keywordInput;



    private static List<String> dataBaseLines;

    @FXML
    private void submitKeyWord(ActionEvent event) throws IOException {
        String input = keywordInput.getText();
        if (!Utils.whatIsPath(input)) {
            System.out.println(input);
            List<String> retrievedData = dataBaseSearch.readDataBase(keywordInput.getText());

            if (retrievedData != null && !retrievedData.isEmpty()) {
                dataBaseLines = retrievedData;
                System.out.println(dataBaseLines);
                switchScene(event, "/showResults.fxml");
            }
        }

    }

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
    private void openFile(ActionEvent event, File file) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("displayFile.fxml"));
        Parent newRoot = loader.load();

        webViewController webViewController = loader.getController();
        webViewController.loadFile(file.toURI().toString());

        switchScene(event, "displayFile.fxml");
    }


}
