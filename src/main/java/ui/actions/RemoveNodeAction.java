package ui.actions;

import graphs.structure.base.Vertex;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import physics.vectors.Vector2D;

public class RemoveNodeAction extends AbstractAction {
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
        return event -> {
            Vertex v = selectedNode(new Vector2D(event.getX(), event.getY()));
            if (v != null) {
                graph.removeVertex(v);
                theForce.getPositionPool().removePosition(v);
                theForce.getPositionPool().unignorePosition(v);
            }
        };
    }
}
