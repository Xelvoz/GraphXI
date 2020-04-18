package physics;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public abstract class PositionPool<T> {
    HashMap<Vertex, T> positions;
    HashSet<Vertex> ignored;

    public PositionPool(AbstractGraph<Vertex> graph) {
        positions = initializeRandomPositions(graph.getAllVertices());
        ignored = new HashSet<>();
    }

    public abstract HashMap<Vertex, T> initializeRandomPositions(Collection<Vertex> allVertices);

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

}