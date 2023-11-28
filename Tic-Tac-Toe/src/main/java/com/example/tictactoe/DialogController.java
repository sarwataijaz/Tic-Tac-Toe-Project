package com.example.tictactoe;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DialogController {

    @FXML
    Label myLabel1;
    @FXML
    Label myLabel2;
    @FXML
    ImageView imageView;

    // text
    public void setMyLabel1(String name) {
        myLabel1.setText(name);

    }
    // header
    public void setMyLabel2(String msg) {
        myLabel2.setText(msg);
    }

    public void setImage(Image image, double x, double y) {

        imageView.setImage(image);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);

    }
}
