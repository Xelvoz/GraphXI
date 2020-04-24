package physics.forces;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;
import physics.vectors.Vector2D;

public class SpringsAndParticles extends TheForce {

    double springLength = 20;
    double stepMagnitude = 5;

    public SpringsAndParticles(AbstractGraph<Vertex> graph) {
        super(graph);
    }

    @Override
    public void initialize(double canvasWidth, double canvasHeight) {
        super.initialize(canvasWidth, canvasHeight);
        springLength = graph.numberOfEdges() * Math.sqrt(graph.numberOfNodes() / (canvasHeight * canvasWidth));
    }

    @Override
    public void applyForces() {
        for (Vertex v : graph.getAllVertices()) {
            Vector2D totalForce = Vector2D.ZERO;
            for (Vertex u : graph.getAllVertices()) {
                if (v != u) {
                    totalForce = totalForce.add(particleForce(v, u));
                    if (graph.hasNeighbor(v, u)) {
                        totalForce = totalForce.add(springForce(v, u));
                    }
                }
            }
            positionPool.updatePosition(v, totalForce.scalar(stepMagnitude));
        }
    }

    private Vector2D springForce(Vertex v, Vertex u) {
        Vector2D v1 = positionPool.getPosition(v);
        Vector2D v2 = positionPool.getPosition(u);
        double distance = v1.distance(v2);
        return v1
                .scalar(-1)
                .add(v2)
                .normalize()
                .scalar(Math.log(distance / springLength));
    }

    private Vector2D particleForce(Vertex v, Vertex u) {
        Vector2D v1 = positionPool.getPosition(v);
        Vector2D v2 = positionPool.getPosition(u);
        double k = Math.max(v.getRadius(), u.getRadius());
        double squareDistance = v1.distance(v2);
        return v1
                .scalar(-1)
                .add(v2)
                .normalize()
                .scalar(-3 * k / squareDistance);
    }
}
