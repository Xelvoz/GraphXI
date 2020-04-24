package ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.animation.GraphAnimation;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class InfoController implements Initializable {
    @FXML
    public Label gtype;
    @FXML
    public Label gnodes;
    @FXML
    public Label gedges;

    @Autowired
    private CentralController centralController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphAnimation graphAnimation = centralController.getGraphAnimation();
        gtype.setText(graphAnimation.getGraph().getType().toString());
        gnodes.setText(String.valueOf(graphAnimation.getGraph().numberOfNodes()));
        gedges.setText(String.valueOf(graphAnimation.getGraph().numberOfEdges()));
    }
}
