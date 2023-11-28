package com.example.tictactoe;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Label gameLabel;
    @FXML
    private GridPane gridLayout;
    @FXML
    private Button continueButton;
    @FXML
    private Button infoButton;

    // would be called immediately after the loading of scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        gridLayout.setOpacity(0); // hide the layout

        // *** Shadow Effect given to the labels ***
        DropShadow shadow = new DropShadow(60, Color.valueOf("#1b0c09"));
        welcome.setEffect(shadow);

        DropShadow shadow2 = new DropShadow(60, Color.valueOf("#1b0c09"));
        gameLabel.setEffect(shadow2);

        // *** Animations given to the labels ***

        TranslateTransition transition1 = new TranslateTransition();
        TranslateTransition transition2 = new TranslateTransition();

        transition1.setNode(welcome);
        transition1.setDuration(Duration.millis(2000));
        transition1.setByY(-500); // position where you want it to animate towards
        transition1.play();

        transition1.setOnFinished(event -> { // after the transition is finished
            transition2.setNode(gameLabel); // set the transition of another label
            transition2.setDuration(Duration.millis(1000));
            transition2.setByY(-480);
            transition2.play();
        });

        FadeTransition fade = new FadeTransition();

        transition2.setOnFinished(event -> {


            fade.setNode(gridLayout);

            fade.setDuration(Duration.millis(1000));

            fade.setCycleCount(1);

            fade.setInterpolator(Interpolator.LINEAR);

            fade.setFromValue(0);

            fade.setToValue(1);

            fade.play();

        });

        fade.setOnFinished(event -> {
            // Make the gridLayout fully visible after the fade-in animation
            gridLayout.setOpacity(1);
            continueButton.setVisible(true);
            infoButton.setVisible(true);

        });

    }

    @FXML
    public void onInfoButton(ActionEvent event) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogLayout.fxml"));
        Parent dialogContent = fxmlLoader.load();

        DialogController controller = fxmlLoader.getController(); // instance of DialogController class
        controller.setMyLabel2("Developed by: ");
        controller.setMyLabel1("Sarwat Aijaz :')");
        controller.setImage(new Image(getClass().getResourceAsStream("/com/example/tictactoe/player.png")), -47,8);

        // Create a new Stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL); // blocks the other window until it is closed


        dialogStage.setScene(new Scene(dialogContent));
        dialogStage.showAndWait();
    }

    private Stage stage;
    private Parent root;
    @FXML
    public void onContinueButton(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml")); // load the player class
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("style.css").toExternalForm();

        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}