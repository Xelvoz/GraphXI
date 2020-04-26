package ui.actions;

import graphs.structure.base.Edge;
import graphs.structure.base.Vertex;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import physics.vectors.Vector2D;

public class RemoveEdgeAction extends AbstractAction {
    Vertex v;
    Vertex u;

    @Override
    public EventHandler<MouseEvent> onMove() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> onRelease() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> onClick() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> onPressed() {
        return (evt) -> {
            if (v != null) {
                u = selectedNode(new Vector2D(evt.getX(), evt.getY()));
                if (u != null && v != u) {
                    graph.removeEdge(new Edge<>(v, u));
                    v = null;
                    u = null;
                }
            } else {
                v = selectedNode(new Vector2D(evt.getX(), evt.getY()));
            }
        };
    }
}
