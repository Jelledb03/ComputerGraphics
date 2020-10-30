package internal;

public class HitObject {
    //This will create a hit object that will include the hit point in the 3D world, the hit time (when the ray hits, have to search for the lowest)
    //Later this object will also include a color and potentially a parameter that indicates reflection or refraction
    private Point hit_point;
    private Vector normal_vector;
    private double hit_time;
    private boolean collided;
    private double diffuse_reflection_coeff; //p392
    private double fallof;
    private double specular_reflection_coeff;
    private double ambient_reflection_coeff;
    private double intensity;

    //Used for lowest hit time hitobject in world (only the collided boolean is used)
    public HitObject() {
        this.hit_point = new Point(0, 0, 0);
        this.hit_time = 0;
        this.collided = false;
        this.diffuse_reflection_coeff = 0.4;
        this.fallof = 30;
        this.specular_reflection_coeff = 0.774597;
        this.ambient_reflection_coeff = 0.25;
        this.intensity = 0;
    }

    public HitObject(Point hit_point, Vector normal_vector, double hit_time) {
        this.hit_point = hit_point;
        this.normal_vector = normal_vector;
        this.hit_time = hit_time;
        this.collided = true;
        this.diffuse_reflection_coeff = 0.4;
        this.fallof = 30;
        this.specular_reflection_coeff = 0.774597;
        this.ambient_reflection_coeff  = 0.25;
        this.intensity = 0;
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

    public double get_diffuse_reflection_coeff() {
        return diffuse_reflection_coeff;
    }

    public void set_diffuse_reflection_coeff(double diffuse_reflection_coeff) {
        this.diffuse_reflection_coeff = diffuse_reflection_coeff;
    }

    public double get_fallof() {
        return fallof;
    }

    public void set_fallof(double fallof) {
        this.fallof = fallof;
    }

    public double get_specular_reflection_coeff() {
        return specular_reflection_coeff;
    }

    public void set_specular_reflection_coeff(double specular_reflection_coeff) {
        this.specular_reflection_coeff = specular_reflection_coeff;
    }

    public double get_ambient_reflection_coeff() {
        return ambient_reflection_coeff;
    }

    public void set_ambient_reflection_coeff(double ambient_reflection_coeff) {
        this.ambient_reflection_coeff = ambient_reflection_coeff;
    }

    public double get_intensity() {
        return intensity;
    }

    public void set_intensity(double intensity) {
        this.intensity = intensity;
    }

    public Vector get_normal_vector() {
        return normal_vector;
    }

    public void set_normal_vector(Vector normal_vector) {
        this.normal_vector = normal_vector;
    }
}
