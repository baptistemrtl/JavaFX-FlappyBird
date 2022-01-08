package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreBoard {

    private final Image firstmedal = new Image("image/firstmedal.png");
    private final Image secondmedal = new Image("image/secondmedal.png");

    @FXML
    private Button retourButton;

    /*@FXML
    private ImageView first;

    @FXML
    private ImageView second;

    @FXML
    private ImageView third;*/

     @FXML
    public void initialize() {
    }

    public void retourButtonAction(ActionEvent actionEvent) throws IOException {
        Stage stage=(Stage) retourButton.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/FXML/MainWindow.fxml")));
        scene.getStylesheets().add(getClass().getResource("/css/background.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(450);
        stage.setHeight(700);
        stage.show();

    }
}
