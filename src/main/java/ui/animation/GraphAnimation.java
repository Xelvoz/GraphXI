package ui.animation;

import graphs.structure.AbstractGraph;
import graphs.structure.base.Vertex;
import javafx.animation.AnimationTimer;
import physics.forces.TheForce;
import physics.positions.PositionPool2D;

public class GraphAnimation extends AnimationTimer {
    private GraphCanvas canvas;
    private TheForce theForce;

    public GraphAnimation(GraphCanvas graphCanvas, TheForce theForce) {
        canvas = graphCanvas;
        this.theForce = theForce;
    }

    @Override
    public void handle(long now) {
        canvas.draw(theForce.getGraph(), theForce.getPositionPool());
        theForce.applyForces();
        if (!canvas.getAction().disableGraphCentering())
            theForce.centerGraph();
    }

    public TheForce getTheForce() {
        return theForce;
    }

    public void setTheForce(TheForce theForce) {
        this.theForce = theForce;
    }

    public AbstractGraph<Vertex> getGraph() {
        return theForce.getGraph();
    }

    public PositionPool2D getPositionPool() {
        return theForce.getPositionPool();
    }

    public void setCanvas(GraphCanvas canvas) {
        this.canvas = canvas;
    }
}
