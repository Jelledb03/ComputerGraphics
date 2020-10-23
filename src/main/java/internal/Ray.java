package internal;

public class Ray {
    private Point eye;
    private Vector dir;

    public Ray(Point eye, Vector dir) {
        this.eye = eye;
        this.dir = dir;
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
}
