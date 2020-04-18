package ui.controllers;

import graphs.structure.generators.UGGenerator;
import javafx.fxml.FXML;
import physics.forces.SpringsAndParticles;
import ui.animation.GraphAnimation;
import ui.animation.GraphCanvas;

public class CentralController {
    @FXML
    private GraphCanvas graphCanvas;
    private GraphAnimation graphAnimation;

    public CentralController() {
    }

    public void initialize() {
        graphAnimation = new GraphAnimation(graphCanvas, new SpringsAndParticles(UGGenerator.cyclic(10)));
        graphAnimation.start();
    }

    public GraphAnimation getGraphAnimation() {
        return graphAnimation;
    }

    public void setGraphAnimation(GraphAnimation graphAnimation) {
        this.graphAnimation = graphAnimation;
    }

    public GraphCanvas getGraphCanvas() {
        return graphCanvas;
    }

    public void setGraphCanvas(GraphCanvas graphCanvas) {
        this.graphCanvas = graphCanvas;
    }
}