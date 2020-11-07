package internal;

public class Ray {
    private Point eye;
    private Vector dir;
    private double c; //holds the relative speed of the ray compared to the speed of light

    public Ray(Point eye, Vector dir, double c) {
        this.eye = eye;
        this.dir = dir;
        this.c = c;
    }

    public Point get_eye() {
        return eye;
    }

    public void set_eye(Point eye) {
        this.eye = eye;
    }

    public Vector get_dir() {
        return dir;
    }

    public void set_dir(Vector dir) {
        this.dir = dir;
    }

    public double get_c() {
        return c;
    }

    public void set_c(double c) {
        this.c = c;
    }
}
