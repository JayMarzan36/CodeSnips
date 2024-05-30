package jay.test;

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
import java.util.List;


public class HelloController {
    @FXML
    private TextField keywordInput;
    @FXML
    private ListView< Hyperlink> listview = new ListView<>();

    @FXML
    private void submitKeyWord(ActionEvent event) throws IOException {
        String input = keywordInput.getText();
        if (!Utils.whatIsPath(input)) {
            System.out.println(input);
            List<String> dataBaseLines = dataBaseSearch.readDataBase(keywordInput.getText());
            System.out.println(dataBaseLines);

            for (String path: dataBaseLines) {
                Hyperlink filehyperlink = new Hyperlink();
                filehyperlink.setOnAction(e -> {
                    try {
                        openFile(e, new File(path));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                listview.getItems().add(filehyperlink);
            }
        }
    }

    @FXML
    private void backToSearch(ActionEvent event) throws IOException {
        switchScene(event, "hello-view.fxml");
    }

    @FXML
    private void switchScene(ActionEvent event, String scenePath) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource(scenePath));
        Scene newScene = new Scene(newRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(newScene);
        stage.show();
    }

    @FXML
    private void openFile(ActionEvent event, File file) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view3.fxml"));
        Parent newRoot = loader.load();

        WebViewController webViewController = loader.getController();
        webViewController.loadFile(file.toURI().toString());

        switchScene(event, "hello-view3.fxml");
    }


}