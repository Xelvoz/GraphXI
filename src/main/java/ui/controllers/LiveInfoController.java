package ui.controllers;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Edge;
import graphs.structure.base.Vertex;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import physics.forces.TheForce;
import physics.vectors.Vector2D;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LiveInfoController implements Initializable {
    @FXML
    public TableView<Vertex> nodesTable;
    @FXML
    public TableView<Edge<Vertex>> edgesTable;
    @FXML
    public TableView<Vertex> positionsTable;

    @Autowired
    private TheForce theForce;

    @Autowired
    private AbstractGraph<Vertex> graph;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TableColumn<Vertex, String> nodeLabel = new TableColumn<>("Label");
        nodeLabel.setCellValueFactory(new PropertyValueFactory<>("label"));
        nodeLabel.setEditable(true);
        nodeLabel.setOnEditCommit(event -> {
            if (graph.getAllVertices().stream().noneMatch(v -> v.getLabel().equals(event.getNewValue())))
                event.getRowValue().setLabel(event.getNewValue());
        });
        nodeLabel.setStyle("-fx-alignment: CENTER;");
        nodeLabel.setPrefWidth(90.0);
        nodeLabel.setMaxWidth(90.0);

        TableColumn<Vertex, ColorPicker> nodeColor = new TableColumn<>("Color");
        nodeColor.setCellValueFactory(v -> {
            ColorPicker cp = new ColorPicker();
            cp.setValue(v.getValue().getColor());
            cp.setOnAction((event) -> {
                v.getValue().setColor(cp.getValue());
            });
            return new ReadOnlyObjectWrapper<>(cp);
        });
        nodeColor.setStyle("-fx-alignment: CENTER;");
        nodeColor.setPrefWidth(90.0);
        nodeColor.setMaxWidth(90.0);

        nodesTable.getColumns().add(nodeLabel);
        nodesTable.getColumns().add(nodeColor);
        nodesTable.getItems().addAll(graph.getAllVertices());

        TableColumn<Edge<Vertex>, String> firstEnd = new TableColumn<>("First End");
        firstEnd.setCellValueFactory(new PropertyValueFactory<>("firstEnd"));
        firstEnd.setStyle("-fx-alignment: CENTER;");
        firstEnd.setPrefWidth(90.0);
        firstEnd.setMaxWidth(90.0);

        TableColumn<Edge<Vertex>, String> secondEnd = new TableColumn<>("Second End");
        secondEnd.setCellValueFactory(new PropertyValueFactory<>("secondEnd"));
        secondEnd.setStyle("-fx-alignment: CENTER;");
        secondEnd.setPrefWidth(90.0);
        secondEnd.setMaxWidth(90.0);

        edgesTable.getColumns().add(firstEnd);
        edgesTable.getColumns().add(secondEnd);
        edgesTable.getItems().addAll(graph.getAllEdges());


        TableColumn<Vertex, String> positionLabel = new TableColumn<>("Label");
        positionLabel.setCellValueFactory(new PropertyValueFactory<>("label"));
        positionLabel.setStyle("-fx-alignment: CENTER;");
        positionLabel.setPrefWidth(90.0);
        positionLabel.setMaxWidth(90.0);

        TableColumn<Vertex, Vector2D> positionLocation = new TableColumn<>("Position");
        positionLocation.setCellValueFactory(v -> {
            Vector2D pos = theForce.getPositionPool().getPosition(v.getValue());
            return new ReadOnlyObjectWrapper<>(pos);
        });
        positionLocation.setStyle("-fx-alignment: CENTER;");
        positionLocation.setPrefWidth(90.0);
        positionLocation.setMaxWidth(90.0);

        positionsTable.getColumns().add(positionLabel);
        positionsTable.getColumns().add(positionLocation);
        positionsTable.getItems().addAll(graph.getAllVertices());

        // FIXME: There's a bug here. Empty the graph. Add two vertices. Add edge between them. No changes are emitted.
        for (ObservableSet<Vertex> neighbor : graph.getGraph().values()) {
            JavaFxObservable.emitOnChanged(neighbor).subscribe(
                    (v) -> {
                        edgesTable.getItems().setAll(graph.getAllEdges());
                    }
            );
        }
        JavaFxObservable.changesOf(graph.getGraph()).subscribe(
                (v) -> {
                    nodesTable.getItems().setAll(graph.getAllVertices());
                }
        );
        JavaFxObservable.changesOf(theForce.getPositionPool().getPositions()).subscribe(
                (v) -> {
                    positionsTable.getItems().setAll(graph.getAllVertices());
                }
        );
    }
}
