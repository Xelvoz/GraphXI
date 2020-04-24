package ui.controllers;

import graphs.structure.base.Edge;
import graphs.structure.base.Vertex;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.MapChangeListener;
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
import ui.animation.GraphAnimation;

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
    private CentralController centralController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphAnimation graphAnimation = centralController.getGraphAnimation();
        TheForce theForce = centralController.getGraphAnimation().getTheForce();

        TableColumn<Vertex, String> nodeLabel = new TableColumn<>("Label");
        nodeLabel.setCellValueFactory(new PropertyValueFactory<>("label"));
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
        nodesTable.getItems().addAll(graphAnimation.getGraph().getAllVertices());

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
        edgesTable.getItems().addAll(graphAnimation.getGraph().getAllEdges());

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
        MapChangeListener<Vertex, Vector2D> mapChangeListener = change -> {
            positionsTable.getItems().setAll(graphAnimation.getGraph().getAllVertices());
        };
        theForce.getPositionPool().getPositions().addListener(mapChangeListener);

        positionsTable.getColumns().add(positionLabel);
        positionsTable.getColumns().add(positionLocation);
        positionsTable.getItems().addAll(graphAnimation.getGraph().getAllVertices());
    }
}
