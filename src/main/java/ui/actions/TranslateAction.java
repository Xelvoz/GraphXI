package ui.actions;

import graphs.structure.base.Vertex;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import physics.vectors.Vector2D;

public class TranslateAction extends AbstractAction {
    boolean translate;
    Vector2D prevPos;

    public TranslateAction() {
        super();
    }

    @Override
    public EventHandler<MouseEvent> onMove() {
        return (evt) -> {
            if (translate) {
                if (prevPos == null) {
                    prevPos = new Vector2D(evt.getX(), evt.getY());
                } else {
                    Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
                    Vector2D newPos = prevPos.scalar(-1).add(mousePos);
                    for (Vertex v : graph.getAllVertices()) {
                        theForce.getPositionPool().updatePosition(v, newPos);
                    }
                    prevPos = new Vector2D(evt.getX(), evt.getY());
                }
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> onRelease() {
        return (evt) -> {
            translate = false;
            prevPos = null;
        };
    }

    @Override
    public EventHandler<MouseEvent> onClick() {
        return null;
    }

    @Override
    public EventHandler<MouseEvent> onPressed() {
        return (evt) -> {
            translate = true;
        };
    }

    @Override
    public boolean disableGraphCentering() {
        return true;
    }
}
