package internal;


import config.Config;
import exterior.material.Material;
import exterior.texture.Texture;

//This will contain all parameters needed for every illumination object
//Will create a R, G and B object for every hitObject
public class IlluminationObject {
    private Material material;
    private double intensity;
    private Texture texture;

    public IlluminationObject(){
        this.material = new Material();
        this.intensity = 0;
    }

    public IlluminationObject(Texture texture){
        this.material = new Material();
        this.intensity = 0;
        this.texture = texture;
    }

    public IlluminationObject(Material material, Texture texture) {
        this.material = material;
        this.texture = texture;
        this.intensity = 0;
    }

    public Material get_material() {
        return material;
    }

    public void set_material(Material material) {
        this.material = material;
    }

    public double get_intensity() {
        return intensity;
    }

    public void set_intensity(double intensity) {
        this.intensity = intensity;
    }

    public Texture get_texture() {
        return texture;
    }

    public void set_texture(Texture texture) {
        this.texture = texture;
    }
}
