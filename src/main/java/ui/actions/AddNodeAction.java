package ui.actions;

import graphs.structure.base.Vertex;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import physics.vectors.Vector2D;
import utils.Counter;

public class AddNodeAction extends AbstractAction {
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
            String label = String.format("Unnamed#%d", Counter.getID());
            Vector2D pos = new Vector2D(evt.getX(), evt.getY());
            Vertex newNode = new Vertex(label);
            graph.addVertex(newNode);
            theForce.getPositionPool().addPosition(newNode, pos);
        };
    }
}
