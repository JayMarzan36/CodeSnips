package jay.test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


public class HelloController {
    @FXML
    private TextField keywordInput;

    @FXML
    private void submitKeyWord(ActionEvent event) throws IOException {
        System.out.println(keywordInput.getText());
        List<Integer> dataBaseLines = dataBaseSearch.readDataBase(keywordInput.getText());
        // System.out.println(dataBaseLines);
        //switchScene(event, "hello-view2.fxml");




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


}