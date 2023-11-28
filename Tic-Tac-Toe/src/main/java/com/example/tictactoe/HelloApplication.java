package com.example.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        String css = this.getClass().getResource("style.css").toExternalForm();
        Image image = new Image(getClass().getResourceAsStream("/com/example/tictactoe/table.png"));

        Scene scene = new Scene(root,800,600);
        scene.getStylesheets().add(css);

        stage.setOnCloseRequest(event -> {
            event.consume(); // let the whole event finish (this wont allow the window to close if we press buttons other than "ok")
            closeScene(stage); // pass the current stage
        });
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.getIcons().add(image);
        stage.show();
    }

    public static void main(String[] args) {
        launch(); } // invokes the start method

    private void closeScene(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation!!");
        alert.setHeaderText("You are about to exit the game.");
        alert.setHeaderText("Are you sure you wanna quit?");

        if(alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }
    }

}