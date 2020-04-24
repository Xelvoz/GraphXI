package ui.controllers;

import graphs.structure.generators.UGGenerator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.springframework.stereotype.Component;
import physics.forces.SpringsAndParticles;
import physics.forces.TheForce;
import ui.animation.GraphAnimation;
import ui.animation.GraphCanvas;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentralController implements Initializable {

    @FXML
    private GraphCanvas graphCanvas;
    private GraphAnimation graphAnimation;
    private TheForce theForce;

    public CentralController() {
        theForce = new SpringsAndParticles(UGGenerator.cyclic(20));
        graphAnimation = new GraphAnimation(graphCanvas, theForce);
    }

    public GraphAnimation getGraphAnimation() {
        return graphAnimation;
    }

    public GraphCanvas getGraphCanvas() {
        return graphCanvas;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphAnimation.setCanvas(graphCanvas);
        theForce.initialize(graphCanvas.getWidth(), graphCanvas.getHeight());
        graphAnimation.start();
    }
}