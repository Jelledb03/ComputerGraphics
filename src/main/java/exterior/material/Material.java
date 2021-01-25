package exterior.material;

import config.Config;

public class Material {
    private double local_coeff;
    private double reflection_coeff;
    private double refraction_coeff;
    private double material_type_speed;
    private double diffuse_reflection_coeff;
    private double fallof;
    private double specular_reflection_coeff;
    private double ambient_reflection_coeff;

    public Material() {
        this.local_coeff = Config.DEFAULT_LOCAL_COEFF;
        this.reflection_coeff = Config.DEFAULT_REFLECTION_COEFF;
        this.refraction_coeff = Config.DEFAULT_REFRACTION_COEFF;
        this.material_type_speed = Config.DEFAULT_AIR_SPEED;
        this.diffuse_reflection_coeff = Config.DEFAULT_DIFFUSE_REFLECTION_COEFF;
        this.fallof = Config.DEFAULT_FALLOF;
        this.specular_reflection_coeff = Config.DEFAULT_SPECULAR_REFLECTION_COEFF;
        this.ambient_reflection_coeff = Config.DEFAULT_AMBIENT_REFLECTION_COEFF;
    }

    public Material(double local_coeff, double reflection_coeff, double refraction_coeff, double material_type_speed, double diffuse_reflection_coeff, double fallof, double specular_reflection_coeff, double ambient_reflection_coeff) {
        this.local_coeff = local_coeff;
        this.reflection_coeff = reflection_coeff;
        this.refraction_coeff = refraction_coeff;
        this.material_type_speed = material_type_speed;
        this.diffuse_reflection_coeff = diffuse_reflection_coeff;
        this.fallof = fallof;
        this.specular_reflection_coeff = specular_reflection_coeff;
        this.ambient_reflection_coeff = ambient_reflection_coeff;
    }

    public double get_local_coeff() {
        return local_coeff;
    }

    public void set_local_coeff(double local_coeff) {
        this.local_coeff = local_coeff;
    }

    public double get_reflection_coeff() {
        return reflection_coeff;
    }

    public void set_reflection_coeff(double reflection_coeff) {
        this.reflection_coeff = reflection_coeff;
    }

    public double get_refraction_coeff() {
        return refraction_coeff;
    }

    public void set_refraction_coeff(double refraction_coeff) {
        this.refraction_coeff = refraction_coeff;
    }

    public double get_material_type_speed() {
        return material_type_speed;
    }

    public void set_material_type_speed(double material_type_speed) {
        this.material_type_speed = material_type_speed;
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

    public void set_fallof(double falloff) {
        this.fallof = falloff;
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
}
