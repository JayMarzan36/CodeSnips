package gui;

import gui.application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class addDataController {
    private static File selectedDirectory;

    @FXML
    private void selectFolderButton() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select a Folder");
        selectedDirectory = chooser.showDialog(application.getPrimaryStage());

        if (selectedDirectory != null) {
            System.out.println(STR."Valid selection: \{selectedDirectory}");
        } else {
            System.out.println("Invalid selection");
        }
    }

    @FXML
    private void backToMain(ActionEvent event) throws IOException {
        gui.controller.switchScene(event, "/main.fxml");
    }

    @FXML
    private void startMakingData() throws IOException {
        String selectedFolderPath = String.valueOf(selectedDirectory);
        ArrayList<String> currentContents = null;
        Map<String, String[]> returnedDict = null;
        String dataBaseFile = "src/main/java/gui/data/DataBase.txt";


        dataParser.doMainLogic(selectedFolderPath, 2, currentContents, returnedDict, dataBaseFile);
    }
}
