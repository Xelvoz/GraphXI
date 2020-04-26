package ui.actions;

import graphs.structure.base.Vertex;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import physics.vectors.Vector2D;

public class MoveNodeAction extends AbstractAction {
    Vertex selectedNode;

    public MoveNodeAction() {
        super();
    }


    @Override
    public EventHandler<MouseEvent> onMove() {
        return (MouseEvent evt) -> {
            if (selectedNode != null) {
                Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
                super.theForce.getPositionPool().ignorePosition(selectedNode);
                super.theForce.getPositionPool().setPosition(selectedNode, mousePos);
            }
        };
    }

    @Override
    public EventHandler<MouseEvent> onRelease() {
        return (MouseEvent evt) -> {
            super.theForce.getPositionPool().unignorePosition(selectedNode);
        };
    }

    @Override
    public EventHandler<MouseEvent> onClick() {
        return (e) -> {
        };
    }

    @Override
    public EventHandler<MouseEvent> onPressed() {
        return (MouseEvent evt) -> {
            Vector2D mousePos = new Vector2D(evt.getX(), evt.getY());
            selectedNode = selectedNode(mousePos);
        };
    }
}
