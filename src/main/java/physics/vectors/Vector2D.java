package physics.vectors;

public class Vector2D {
    public static Vector2D ZERO = new Vector2D(0, 0);
    private final double x;
    private final double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector2D add(Vector2D v) {
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    public Vector2D scalar(double k) {
        return new Vector2D(x * k, y * k);
    }

    public double norm() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2D normalize() {
        return scalar(1.0 / norm());
    }

    public double squareDistance(Vector2D v) {
        double t = x - v.x;
        double u = y - v.y;
        return Math.pow(t, 2) + Math.pow(u, 2);
    }

    public double distance(Vector2D v) {
        return Math.sqrt(squareDistance(v));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}
