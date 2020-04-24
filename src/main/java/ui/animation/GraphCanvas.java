package ui.animation;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Edge;
import graphs.structure.base.Vertex;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import physics.positions.PositionPool2D;
import physics.vectors.Vector2D;

public class GraphCanvas extends Canvas {

    public void draw(AbstractGraph<Vertex> graph, PositionPool2D positionPool) {
        GraphicsContext context = getGraphicsContext2D();
        context.clearRect(0, 0, getWidth(), getHeight());

        for (Edge<Vertex> e : graph.getAllEdges()) {
            Vertex v = e.getFirstEnd();
            Vertex u = e.getSecondEnd();
            Vector2D p1 = positionPool.getPosition(v);
            Vector2D p2 = positionPool.getPosition(u);
            context.setStroke(e.getColor());
            context.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        }

        for (Vertex v : graph.getAllVertices()) {
            Vector2D pp = positionPool.getPosition(v);
            Vector2D p = pp.add(new Vector2D(-v.getRadius(), -v.getRadius()));
            context.setFill(v.getColor());
            context.fillOval(p.getX(), p.getY(), v.getDiameter(), v.getDiameter());
            context.strokeText(v.getLabel(), pp.getX(), p.getY());
        }
    }
}
