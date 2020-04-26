package ui.actions;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Edge;
import graphs.structure.base.Vertex;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import physics.forces.TheForce;
import physics.vectors.Vector2D;

public abstract class AbstractAction {
    protected AbstractGraph<Vertex> graph;
    protected TheForce theForce;

    public AbstractAction() {
    }

    public abstract EventHandler<MouseEvent> onMove();

    public abstract EventHandler<MouseEvent> onRelease();

    public abstract EventHandler<MouseEvent> onClick();

    public abstract EventHandler<MouseEvent> onPressed();

    public boolean disableGraphCentering() {
        return false;
    }

    public Vertex selectedNode(Vector2D mouse) {
        for (Vertex v : graph.getAllVertices()) {
            if (theForce.getPositionPool().getPosition(v).squareDistance(mouse) <= v.getRadius() * v.getRadius()) {
                return v;
            }
        }
        return null;
    }

    public void drawActionHighlightsInEdges(GraphicsContext context, Edge<Vertex> edge) {
    }

    public void drawActionHighlightsInVertices(GraphicsContext context, Vertex vertex) {
    }

    public void setGraph(AbstractGraph<Vertex> graph) {
        this.graph = graph;
    }

    public void setTheForce(TheForce theForce) {
        this.theForce = theForce;
    }
}
