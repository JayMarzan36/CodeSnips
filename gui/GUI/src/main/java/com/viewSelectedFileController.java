package com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class viewSelectedFileController implements applicationAware{
    private application app;

    @FXML
    private WebView webView;


    @Override
    public void setApplication(application app) {
        this.app = app;
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        String linkToShow = showResultController.getLink();
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
        app.switchScene("/fxml/showResults.fxml");
    }
}
