package internal;


import config.Config;

//This will contain all parameters needed for every illumination object
//Will create a R, G and B object for every hitObject
public class IlluminationObject {
    private double diffuse_reflection_coeff; //p392
    private double fallof;
    private double specular_reflection_coeff;
    private double ambient_reflection_coeff;
    private double intensity;

    public IlluminationObject(){
        this.diffuse_reflection_coeff = Config.DEFAULT_DIFFUSE_REFLECTION_COEFF;
        this.fallof = Config.DEFAULT_FALLOF;
        this.specular_reflection_coeff = Config.DEFAULT_SPECULAR_REFLECTION_COEFF;
        this.ambient_reflection_coeff = Config.DEFAULT_AMBIENT_REFLECTION_COEFF;
        this.intensity = 0;
    }

    public IlluminationObject(double diffuse_reflection_coeff, double fallof, double specular_reflection_coeff, double ambient_reflection_coeff) {
        this.diffuse_reflection_coeff = diffuse_reflection_coeff;
        this.fallof = fallof;
        this.specular_reflection_coeff = specular_reflection_coeff;
        this.ambient_reflection_coeff = ambient_reflection_coeff;
        this.intensity = 0;
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
}
