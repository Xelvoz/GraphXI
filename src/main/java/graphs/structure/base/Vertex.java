package graphs.structure.base;

import javafx.scene.paint.Color;
import utils.RNG;

import java.util.Objects;

public class Vertex {
    private String label;
    private Color color;
    private double radius;

    public Vertex(String label) {
        this.label = label;
        color = Color.hsb(RNG.integer(0, 360), 0.8, 1);
        radius = RNG.integer(10, 11);
    }

    public Vertex(long id) {
        this(String.format("%d", id));
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDiameter() {
        return radius * 2;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(label, vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return label;
    }
}
