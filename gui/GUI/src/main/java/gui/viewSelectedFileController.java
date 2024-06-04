package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class viewSelectedFileController {

    @FXML
    private WebView webView;


    @FXML
    public void initialize() throws FileNotFoundException {
        String linkToShow = showResultController.getLink();
        System.out.println(linkToShow);
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(convertToHtml(linkToShow));

    }

    public static String convertToHtml(String filePath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        content.append("<html><body><pre>");
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        }
        content.append("</pre></body></html>");
        return content.toString();
    }

    @FXML
    private void backToResult(ActionEvent event) throws IOException {
        gui.controller.switchScene(event, "/showResults.fxml");
    }
}
