package internal;

import exterior.texture.Texture;
import exterior.texture.WoodTexture;

import java.awt.*;

public class HitObject {
    //This will create a hit object that will include the hit point in the 3D world, the hit time (when the ray hits, have to search for the lowest)
    //Later this object will also include a color and potentially a parameter that indicates reflection or refraction
    private Point hit_point;
    private Vector normal_vector;
    private Color color;
    private double hit_time;
    private boolean collided;
    private IlluminationObject r_illuminationObject;
    private IlluminationObject g_illuminationObject;
    private IlluminationObject b_illuminationObject;
    private double local_coeff;
    private double refraction_coeff;
    private double reflection_coeff;
    private double c;

    //Used for lowest hit time hitobject in world (only the collided boolean is used)
    public HitObject() {
        this.hit_point = new Point(0, 0, 0);
        this.hit_time = 0;
        this.collided = false;
        this.r_illuminationObject = new IlluminationObject(new WoodTexture());
        this.g_illuminationObject = new IlluminationObject(new WoodTexture());
        this.b_illuminationObject = new IlluminationObject(new WoodTexture());
    }

    public HitObject(Point hit_point, Vector normal_vector, Color color, double hit_time, double local_coeff, double reflection_coeff, double refraction_coeff, double c, Texture texture) {
        this.hit_point = hit_point;
        this.normal_vector = normal_vector;
        this.color = color;
        this.hit_time = hit_time;
        this.collided = true;
        this.r_illuminationObject = new IlluminationObject(texture);
        this.g_illuminationObject = new IlluminationObject(texture);
        this.b_illuminationObject = new IlluminationObject(texture);
        this.local_coeff = local_coeff;
        this.reflection_coeff = reflection_coeff;
        this.refraction_coeff = refraction_coeff;
        this.c = c;
    }

    public HitObject(Point hit_point, Vector normal_vector, Color color, double hit_time, IlluminationObject r_illuminationObject, IlluminationObject g_illuminationObject, IlluminationObject b_illuminationObject,double local_coeff, double reflection_coeff, double refraction_coeff, double c) {
        this.hit_point = hit_point;
        this.normal_vector = normal_vector;
        this.color = color;
        this.hit_time = hit_time;
        this.collided = true;
        this.r_illuminationObject = r_illuminationObject;
        this.g_illuminationObject = g_illuminationObject;
        this.b_illuminationObject = b_illuminationObject;
        this.local_coeff = local_coeff;
        this.reflection_coeff = reflection_coeff;
        this.refraction_coeff = refraction_coeff;
        this.c = c;
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

    public Vector get_normal_vector() {
        return normal_vector;
    }

    public void set_normal_vector(Vector normal_vector) {
        this.normal_vector = normal_vector;
    }

    public Color get_color() {
        return color;
    }

    public void set_color(Color color) {
        this.color = color;
    }

    public IlluminationObject get_r_illuminationObject() {
        return r_illuminationObject;
    }

    public void set_r_illuminationObject(IlluminationObject r_illuminationObject) {
        this.r_illuminationObject = r_illuminationObject;
    }

    public IlluminationObject get_g_illuminationObject() {
        return g_illuminationObject;
    }

    public void set_g_illuminationObject(IlluminationObject g_illuminationObject) {
        this.g_illuminationObject = g_illuminationObject;
    }

    public IlluminationObject get_b_illuminationObject() {
        return b_illuminationObject;
    }

    public void set_b_illuminationObject(IlluminationObject b_illuminationObject) {
        this.b_illuminationObject = b_illuminationObject;
    }

    public double get_local_coeff() {
        return local_coeff;
    }

    public void set_local_coeff(double local_coeff) {
        this.local_coeff = local_coeff;
    }

    public double get_refraction_coeff() {
        return refraction_coeff;
    }

    public void set_refraction_coeff(double refraction_coeff) {
        this.refraction_coeff = refraction_coeff;
    }

    public double get_reflection_coeff() {
        return reflection_coeff;
    }

    public void set_reflection_coeff(double reflection_coeff) {
        this.reflection_coeff = reflection_coeff;
    }

    public double get_c() {
        return c;
    }

    public void set_c(double c) {
        this.c = c;
    }
}
