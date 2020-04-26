package ui.actions;

import graphs.structure.base.Edge;
import graphs.structure.base.Vertex;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import physics.vectors.Vector2D;

public class AddEdgeAction extends AbstractAction {
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
                if (u != null) {
                    if (v != u) {
                        graph.addEdge(new Edge<>(v, u));

                    }
                    v = null;
                    u = null;
                }
            } else {
                v = selectedNode(new Vector2D(evt.getX(), evt.getY()));
            }
        };
    }

    @Override
    public void drawActionHighlightsInVertices(GraphicsContext context, Vertex vertex) {
        if (vertex == v || vertex == u) {
            Vector2D pos = theForce.getPositionPool().getPosition(vertex);
            Vector2D start = pos.add(new Vector2D(vertex.getRadius(), vertex.getRadius()).scalar(-1));
            Paint resetP = context.getStroke();
            double resetLW = context.getLineWidth();
            context.setStroke(Color.BLACK);
            context.setLineWidth(5);
            context.strokeOval(start.getX(), start.getY(), vertex.getDiameter(), vertex.getDiameter());
            context.setLineWidth(resetLW);
            context.setStroke(resetP);
        }
    }
}
