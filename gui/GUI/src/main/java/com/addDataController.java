package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;



public class addDataController implements applicationAware{
    private application app;
    private static File selectedDirectory;

    @Override
    public void setApplication(application app) {
        this.app = app;
    }

    @FXML
    private void selectFolderButton() {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select a Folder");
        selectedDirectory = chooser.showDialog(application.getPrimaryStage());

        if (selectedDirectory != null) {
            System.out.println("Valid selection: " + selectedDirectory);
        } else {
            System.out.println("Invalid selection");
        }
    }

    @FXML
    private void backToMain(ActionEvent event) throws IOException {
        app.switchScene("/fxml/main.fxml");
    }

    @FXML
    private void startMakingData() throws IOException {
        String selectedFolderPath = String.valueOf(selectedDirectory);
        ArrayList<String> currentContents = null;
        Map<String, String[]> returnedDict = null;
        String dataBaseFile = "/src/data/DataBase.txt";


        dataParser.doMainLogic(selectedFolderPath, 2, currentContents, returnedDict, dataBaseFile);
    }
}
