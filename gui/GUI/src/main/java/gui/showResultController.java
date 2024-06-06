package gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class showResultController {
    @FXML
    private ListView<Hyperlink> listView;

    private static String selectedLink;
    @FXML
    private void initialize () throws InterruptedException {
        List<String> listOfPaths = gui.controller.getDataBaseLines();
        List<Hyperlink> hyperList = new ArrayList<>();
        for (String path: listOfPaths) {
            Hyperlink filehyperlink = new Hyperlink(path);
            filehyperlink.setOnAction(e -> {
                try {
                    handleHyperLinkAction(e, filehyperlink);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            hyperList.add(filehyperlink);
        }
        listView.setItems(FXCollections.observableArrayList(hyperList));
    }

    private void handleHyperLinkAction(ActionEvent event, Hyperlink hyperlink) throws IOException {
        selectedLink = hyperlink.getText();
        gui.controller.switchScene(event, "/viewFile.fxml");
    }

    public static String getLink() {
        String trueSelectedLink = selectedLink;
        return trueSelectedLink;
    }

    @FXML
    private void backToSearch(ActionEvent event) throws IOException {
        gui.controller.switchScene(event, "/main.fxml");
    }
}
