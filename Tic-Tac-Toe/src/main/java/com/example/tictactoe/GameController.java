package com.example.tictactoe;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameController {

    String player1 = "";
    String player2 = "";
    @FXML
    TextField nameLabel;
    @FXML
    GridPane gridLayout;

    public void setPlayers(ActionEvent event, String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        nameLabel.setText(player1); // start the game with player1
    }
    @FXML
    public void clickCells(ActionEvent event) {

        Button selectedCell = (Button) event.getSource(); // buttons in gridlayout of 3x3 with no text, the user
                                                        // would select the cells randomly so we have to retrive the
                                                //selected button and set the text based on which player has clicked it
        if(selectedCell.getText().isEmpty()) {
            selectedCell.setDisable(false); // enable the button functionality if the text is empty on the selected button
            try {
                if (nameLabel.getText().equals(player1)) {
                    selectedCell.setStyle("-fx-text-fill: #092ee6"); // css styling

                    applyAnimation(selectedCell);
                    selectedCell.setText("X");
                    checkResult(event); // check the result imediately
                    nameLabel.setText(player2);
                } else {
                    selectedCell.setStyle("-fx-text-fill: #e51515");
                   applyAnimation(selectedCell);
                    selectedCell.setText("O");
                    checkResult(event);
                    nameLabel.setText(player1);
                }
                selectedCell.setOpacity(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            selectedCell.setDisable(true); // disable the button if the text (X or O) is already there so the text cant be changed
        }

    }

    public void applyAnimation(Button selectedCell) {
        FadeTransition fade = new FadeTransition(); // apply effect
        fade.setNode(selectedCell);

        fade.setDuration(Duration.millis(1000));

        fade.setCycleCount(1);

        fade.setInterpolator(Interpolator.LINEAR);

        fade.setFromValue(0);

        fade.setToValue(1);

        fade.play();
    }
    // buttons in each cell of gridlayout
    @FXML
    Button button00; // 1st row 1st column
    @FXML
    Button button10;
    @FXML
    Button button20;
    @FXML
    Button button01;
    @FXML
    Button button11;
    @FXML
    Button button21;
    @FXML
    Button button02;
    @FXML
    Button button12;
    @FXML
    Button button22;

    public void displayDialogBox(ActionEvent event, String buttonText) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogLayout.fxml"));
        Parent dialogContent = fxmlLoader.load();

        DialogController controller = fxmlLoader.getController();
        controller.setMyLabel2("Game Overrrr!!! ");
        controller.setImage(new Image(getClass().getResourceAsStream("/com/example/tictactoe/girl.png")), -12,-26);

        // Create a new Stage
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        if(buttonText.equals("O")) {
            controller.setMyLabel1(player2 + " wonnnn!!!");
        } else if(buttonText.equals("X")){
            controller.setMyLabel1(player1 + " wonnnn!!!");
        } else {
            controller.setMyLabel1("its a drawwww!!!");
        }

        dialogStage.setScene(new Scene(dialogContent));
        dialogStage.showAndWait();
        resetBoardButton(event);
    }

    @FXML
    public void checkResult(ActionEvent event) throws IOException {

        String text00 = button00.getText();
        String text10 = button10.getText();
        String text20 = button20.getText();
        String text01 = button01.getText();
        String text11 = button11.getText();
        String text21 = button21.getText();
        String text02 = button02.getText();
        String text12 = button12.getText();
        String text22 = button22.getText();

        if(!text00.isEmpty() && !text10.isEmpty() && !text20.isEmpty() && text00.equals(text10) && text10.equals(text20)) {

            displayDialogBox(event, text00);
        }

        else if(!text01.isEmpty() && !text11.isEmpty() && !text21.isEmpty() && text01.equals(text11) && text11.equals(text21)) {

            displayDialogBox(event, text01);
        }

        else if(!text02.isEmpty() && !text12.isEmpty() && !text22.isEmpty() && text02.equals(text12) && text12.equals(text22)) {

            displayDialogBox(event, text02);
        }

        else if(!text00.isEmpty() && !text01.isEmpty() && !text02.isEmpty() && text00.equals(text01) && text01.equals(text02)) {

            displayDialogBox(event, text00);
        }

        else if(!text10.isEmpty() && !text11.isEmpty() && !text12.isEmpty() && text10.equals(text11) && text11.equals(text12)) {

            displayDialogBox(event, text10);
        }
        else if(!text20.isEmpty() && !text21.isEmpty() && !text22.isEmpty() && text20.equals(text21) && text21.equals(text22)) {

            displayDialogBox(event, text20);
        }

        else if(!text00.isEmpty() && !text11.isEmpty() && !text22.isEmpty() && text00.equals(text11) && text11.equals(text22)) {

            displayDialogBox(event, text00);
        }
        else if(!text02.isEmpty() && !text11.isEmpty() && !text20.isEmpty() && text02.equals(text11) && text11.equals(text20)) {

            displayDialogBox(event, text02);
        }
        // if all the winning conditions are false and still the cells are filled

        else if (!text00.isEmpty() && !text10.isEmpty() && !text20.isEmpty() &&
                !text01.isEmpty() && !text11.isEmpty() && !text21.isEmpty() &&
                !text02.isEmpty() && !text12.isEmpty() && !text22.isEmpty()) {

            displayDialogBox(event, "draw");
        }


    }

    @FXML
    public void resetBoardButton(ActionEvent event) {

        button00.setText("");
        button10.setText("");
        button20.setText("");
        button01.setText("");
        button11.setText("");
        button21.setText("");
        button02.setText("");
        button12.setText("");
        button22.setText("");
        nameLabel.setText(player1); // start with player1 again

        // Re-enable buttons
        button00.setDisable(false);
        button10.setDisable(false);
        button20.setDisable(false);
        button01.setDisable(false);
        button11.setDisable(false);
        button21.setDisable(false);
        button02.setDisable(false);
        button12.setDisable(false);
        button22.setDisable(false);

    }
    private Scene preScene;
    private Stage stage;
    @FXML
    public void setPreScene(Scene scene) {
        this.preScene = scene;
    }
    @FXML
    public void resetPlayerButton(ActionEvent event) throws IOException {

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(preScene);
        stage.show(); // go back to the player scene that we stored

    }

}
