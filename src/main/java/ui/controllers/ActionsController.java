package ui.controllers;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import physics.forces.TheForce;
import ui.actions.*;
import ui.animation.GraphCanvas;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ActionsController implements Initializable {
    @Autowired
    private AbstractGraph<Vertex> graph;
    @Autowired
    private TheForce theForce;

    private AbstractAction action;
    private GraphCanvas graphCanvas;

    public ActionsController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAction(new MoveNodeAction());
    }

    public void setGraphCanvas(GraphCanvas graphCanvas) {
        this.graphCanvas = graphCanvas;
        this.graphCanvas.setAction(action);
    }

    public void setAction(AbstractAction action) {
        action.setGraph(graph);
        action.setTheForce(theForce);
        this.action = action;
    }

    public void moveAction(MouseEvent event) {
        setAction(new MoveNodeAction());
        graphCanvas.setAction(action);
    }

    public void translateAction(MouseEvent event) {
        setAction(new TranslateAction());
        graphCanvas.setAction(action);
    }

    public void addNode(MouseEvent event) {
        setAction(new AddNodeAction());
        graphCanvas.setAction(action);
    }

    public void addEdge(MouseEvent event) {
        setAction(new AddEdgeAction());
        graphCanvas.setAction(action);
    }

    public void removeNode(MouseEvent event) {
        setAction(new RemoveNodeAction());
        graphCanvas.setAction(action);
    }

    public void removeEdge(MouseEvent event) {
        setAction(new RemoveEdgeAction());
        graphCanvas.setAction(action);
    }

    public void freezeNode(MouseEvent event) {
        setAction(new FreezeNodeAction());
        graphCanvas.setAction(action);
    }

    public void unfreezeNode(MouseEvent event) {
        setAction(new UnfreezeNodeAction());
        graphCanvas.setAction(action);
    }
}
