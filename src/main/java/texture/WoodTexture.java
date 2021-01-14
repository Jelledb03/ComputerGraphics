package texture;

public class WoodTexture extends Texture{
    private double D;
    private double A;
    private double M;

    public WoodTexture() {
        super(false);
    }

    public WoodTexture(double D, double A, double M) {
        super(true);
        this.D = D;
        this.A = A;
        this.M = M;
    }

    @Override
    public double texture(double x, double y, double z) {
        double r = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
        int rings = rings(r/M);
        return D + A * rings;
    }

    int rings(double prod){
        int r = (int) Math.round(prod);
        return Math.floorMod(r,2);
    }

    public double get_D() {
        return D;
    }

    public void set_D(double d) {
        D = d;
    }

    public double get_A() {
        return A;
    }

    public void set_A(double a) {
        A = a;
    }

    public double get_M() {
        return M;
    }

    public void set_M(double m) {
        M = m;
    }
}
