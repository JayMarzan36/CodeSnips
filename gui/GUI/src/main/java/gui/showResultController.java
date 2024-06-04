package gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.controller;

public class showResultController {
    @FXML
    private ListView<Hyperlink> listView;
    @FXML
    private void initialize () throws InterruptedException {
        List<String> listOfPaths = gui.controller.getDataBaseLines();
        List<Hyperlink> hyperList = new ArrayList<>();
        for (String path: listOfPaths) {
            Hyperlink filehyperlink = new Hyperlink(path);
            filehyperlink.setOnAction(e -> handleHyperLinkAction(filehyperlink));
            hyperList.add(filehyperlink);
        }
        listView.setItems(FXCollections.observableArrayList(hyperList));
    }

    private void handleHyperLinkAction(Hyperlink hyperlink) {
        System.out.println("Clicked on: " + hyperlink.getText());
    }

    @FXML
    private void backToSearch(ActionEvent event) throws IOException {
        gui.controller.switchScene(event, "/main.fxml");
    }
}
