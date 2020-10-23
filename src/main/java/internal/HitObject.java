package internal;

public class HitObject {
    //This will create a hit object that will include the hit point in the 3D world, the hit time (when the ray hits, have to search for the lowest)
    //Later this object will also include a color and potentially a parameter that indicates reflection or refraction
    private Point hit_point;
    private double hit_time;

    public HitObject(Point hit_point, double hit_time) {
        this.hit_point = hit_point;
        this.hit_time = hit_time;
    }

    public Point get_hit_point() {
        return hit_point;
    }

    public void set_hit_point(Point hit_point) {
        this.hit_point = hit_point;
    }

    public double get_hit_time() {
        return hit_time;
    }

    public void set_hit_time(double hit_time) {
        this.hit_time = hit_time;
    }
}
