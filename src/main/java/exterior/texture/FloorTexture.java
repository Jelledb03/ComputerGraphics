package exterior.texture;

public class FloorTexture extends Texture{
    double scaling_factor;

    public FloorTexture(double scaling_factor) {
        super(true);
        this.scaling_factor = scaling_factor;
    }

    @Override
    public double texture(double x, double y, double z) {
        return jump(x,y,z);
    }

    private double jump(double x, double y, double z){
        int abs = Math.abs((int)(x/scaling_factor) + (int)(y/scaling_factor) + (int)(z/scaling_factor));
        return Math.floorMod(abs, 2);
    }
}
