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
            Vertex v = e.getKey();
            Vertex u = e.getValue();
            Vector2D p1 = positionPool.getPosition(v);
            Vector2D p2 = positionPool.getPosition(u);
            context.setStroke(e.getColor());
            context.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
            Vector2D middle = p1.add(p2).scalar(0.5);
            context.strokeText(e.toString(), middle.getX(), middle.getY());
        }

        for (Vertex v : graph.getAllVertices()) {
            Vector2D p = positionPool.getPosition(v).add(new Vector2D(-v.getRadius(), -v.getRadius()));
            context.setFill(v.getColor());
            context.fillOval(p.getX(), p.getY(), v.getDiameter(), v.getDiameter());
        }
    }
}
