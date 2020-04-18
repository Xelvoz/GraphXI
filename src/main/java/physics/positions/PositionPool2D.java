package physics.positions;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;
import physics.Vector2D;
import utils.RNG;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class PositionPool2D extends PositionPool<Vector2D> {

    public PositionPool2D(AbstractGraph<Vertex> graph) {
        super(graph);
    }

    @Override
    public Map<Vertex, Vector2D> initializeRandomPositions(Collection<Vertex> allVertices) {
        return allVertices.stream().collect(Collectors.toMap(
                (Vertex v) -> v, (Object x) -> new Vector2D(
                        RNG.real(-1500, 1500),
                        RNG.real(-1500, 1500)
                )
        ));
    }

    @Override
    public void updatePosition(Vertex v, Vector2D pos) {
        if (positions.containsKey(v) && !ignored.contains(v)) {
            Vector2D oldPos = positions.get(v);
            positions.replace(v, oldPos.add(pos));
        }
    }

    @Override
    public Vector2D centerOfMass() {
        return positions.values().stream().reduce(Vector2D.ZERO, Vector2D::add).scalar(1.0 / positions.size());
    }
}
