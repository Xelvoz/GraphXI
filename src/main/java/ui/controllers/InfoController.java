package ui.controllers;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private AbstractGraph<Vertex> graph;

    public InfoController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gtype.setText(graph.getType().toString());
        gnodes.setText(String.valueOf(graph.numberOfNodes()));
        gedges.setText(String.valueOf(graph.numberOfEdges()));
        for (ObservableSet<Vertex> neighbor : graph.getGraph().values())
            JavaFxObservable.changesOf(neighbor).subscribe(
                    (v) -> {
                        gedges.setText(String.valueOf(graph.numberOfEdges()));
                    }
            );
        JavaFxObservable.changesOf(graph.getGraph()).subscribe(
                (v) -> {
                    gnodes.setText(String.valueOf(graph.numberOfNodes()));
                }
        );
    }
}
