package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller pour la fenêtre d'erreur
 */
public class Error {

    @FXML private Button closeButton;

    @FXML
    private void initialize() { }

    @FXML
    private void close() {
        ((Stage)closeButton.getScene().getWindow()).close();
    }
}
