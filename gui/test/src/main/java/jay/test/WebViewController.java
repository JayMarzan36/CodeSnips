package jay.test;


import javafx.fxml.FXML;
import javafx.scene.web.WebView;


public class WebViewController {
    @FXML
    private WebView webView;

    public void loadFile(String fileURI) {
        webView.getEngine().load(fileURI);
    }
}
