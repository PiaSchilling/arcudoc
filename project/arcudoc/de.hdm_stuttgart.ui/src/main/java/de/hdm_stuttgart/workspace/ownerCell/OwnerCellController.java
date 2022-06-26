package de.hdm_stuttgart.workspace.ownerCell;

import de.hdm_stuttgart.workspace.service.IMemberProjectResponse;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class OwnerCellController {

    private final IMemberProjectResponse projectResponse;

    @FXML
    private Circle ownerAvatarFrame;

    @FXML
    private Text ownerMailLabel;


    public OwnerCellController(IMemberProjectResponse projectResponse) {
        this.projectResponse = projectResponse;
    }

    public void initialize(){
        ownerMailLabel.setText(projectResponse.getOwnerMail());

        try {
            URL url = new URL(projectResponse.getOwnerAvatarUrl());
            InputStream inputStream =  url.openStream();
            Image image = new Image(inputStream);
            ownerAvatarFrame.setFill(new ImagePattern(image));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
