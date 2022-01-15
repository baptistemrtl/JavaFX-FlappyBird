package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Error {

    @FXML private Button closeButton;

    @FXML
    private void initialize() { }

    @FXML
    private void close() {
        ((Stage)closeButton.getScene().getWindow()).close();
    }
}
