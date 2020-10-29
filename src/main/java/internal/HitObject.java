package internal;

public class HitObject {
    //This will create a hit object that will include the hit point in the 3D world, the hit time (when the ray hits, have to search for the lowest)
    //Later this object will also include a color and potentially a parameter that indicates reflection or refraction
    private Point hit_point;
    private double hit_time;
    private boolean collided;
    private double diffuse_reflection_coeff; //p392

    //Used for lowest hit time hitobject in world (only the collided boolean is used)
    public HitObject() {
        this.hit_point = new Point(0, 0, 0);
        this.hit_time = 0;
        this.collided = false;
        this.diffuse_reflection_coeff = 0.01;
    }

    public HitObject(Point hit_point, double hit_time) {
        this.hit_point = hit_point;
        this.hit_time = hit_time;
        this.collided = true;
        this.diffuse_reflection_coeff = 0.01;
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

    public boolean is_collided() {
        return collided;
    }

    public void set_collided(boolean collided) {
        this.collided = collided;
    }

    public double getDiffuse_reflection_coeff() {
        return diffuse_reflection_coeff;
    }

    public void setDiffuse_reflection_coeff(double diffuse_reflection_coeff) {
        this.diffuse_reflection_coeff = diffuse_reflection_coeff;
    }
}
