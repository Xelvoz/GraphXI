package ui.controllers;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import physics.forces.TheForce;
import ui.animation.GraphAnimation;
import ui.animation.GraphCanvas;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CentralController implements Initializable {

    @FXML
    public CheckBox centering;

    @Autowired
    private ActionsController actionsController;

    @FXML
    private GraphCanvas graphCanvas;

    private GraphAnimation graphAnimation;

    @Autowired
    private TheForce theForce;

    @Autowired
    private AbstractGraph<Vertex> graph;

    public CentralController() {

    }

    public GraphAnimation getGraphAnimation() {
        return graphAnimation;
    }

    public GraphCanvas getGraphCanvas() {
        return graphCanvas;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        actionsController.setGraphCanvas(graphCanvas);
        graphAnimation = new GraphAnimation(graphCanvas, theForce);
        centering.setSelected(true);
        graphAnimation.setCenterGraph(centering.isSelected());
        theForce.initialize(graphCanvas.getWidth(), graphCanvas.getHeight());
        JavaFxObservable.changesOf(centering.selectedProperty()).subscribe(
                (v) -> {
                    graphAnimation.setCenterGraph(centering.isSelected());
                }
        );
        graphAnimation.start();
    }
}