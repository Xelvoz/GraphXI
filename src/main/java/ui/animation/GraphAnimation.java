package ui.animation;

import javafx.animation.AnimationTimer;
import physics.TheForce;

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
        theForce.centerGraph(canvas.getHeight(), canvas.getWidth());
    }
}
