package physics.positions;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class PositionPool<T> {
    HashMap<Vertex, T> positions;
    HashSet<Vertex> ignored;

    public PositionPool(AbstractGraph<Vertex> graph) {
        positions = (HashMap<Vertex, T>) initializeRandomPositions(graph.getAllVertices());
        ignored = new HashSet<>();
    }

    public abstract Map<Vertex, T> initializeRandomPositions(Collection<Vertex> allVertices);

    public abstract void updatePosition(Vertex v, T pos);

    public void removePosition(Vertex v) {
        positions.remove(v);
    }

    public void ignorePosition(Vertex v) {
        ignored.add(v);
    }

    public void unignorePosition(Vertex v) {
        ignored.remove(v);
    }

    public T getPosition(Vertex v) {
        return positions.get(v);
    }

    public abstract T centerOfMass();
}