package gui;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class webViewController {
    @FXML
    private WebView webView;

    public void loadFile(String fileURI) {
        webView.getEngine().load(fileURI);
    }
}
