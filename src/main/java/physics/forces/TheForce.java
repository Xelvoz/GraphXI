package physics.forces;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;
import physics.positions.PositionPool2D;
import physics.vectors.Vector2D;

public abstract class TheForce {
    PositionPool2D positionPool;
    AbstractGraph<Vertex> graph;

    public TheForce(AbstractGraph<Vertex> graph) {
        this.graph = graph;
        positionPool = new PositionPool2D(graph);
    }

    public abstract void applyForces();

    public void centerGraph(double canvasHeight, double canvasWidth) {
        Vector2D centerOfMass = positionPool.centerOfMass();
        for (Vertex v : graph.getAllVertices()) {
            positionPool.updatePosition(
                    v,
                    centerOfMass
                            .scalar(-1)
                            .add(
                                    new Vector2D(
                                            canvasWidth / 2.0,
                                            canvasHeight / 2.0
                                    )
                            )
            );
        }
    }

    public PositionPool2D getPositionPool() {
        return positionPool;
    }

    public void setPositionPool(PositionPool2D positionPool) {
        this.positionPool = positionPool;
    }

    public AbstractGraph<Vertex> getGraph() {
        return graph;
    }

    public void setGraph(AbstractGraph<Vertex> graph) {
        this.graph = graph;
    }
}
