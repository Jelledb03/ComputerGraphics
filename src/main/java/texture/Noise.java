package texture;

import java.util.Random;

public class Noise extends Texture {
    private int[] index;
    private double[] noiseTable;
    private double scale;

    public Noise() {
        super(false);
    }

    public Noise(double scale) {
        super(true);
        this.scale = scale;
        Random random = new Random();
        int i;
        index = new int[256];
        for(i = 0; i < 256; i++){
            index[i] = i;
        }
        for(i = 0; i < 256; i++){
            int which = random.nextInt(256);
            int tmp = index[i];
            //Swap values
            index[which] = index[i];
            index[i] = tmp;
        }
        noiseTable = new double[256];
        for(i = 0; i < 256; i ++){
            double random_val = random.nextInt(32767) / 32767.99;
            noiseTable[i] = random_val;
        }
    }

    private int PERM(long x){
        return (int) (x & 255);
    }

    private int INDEX(long ix, long iy, long iz){
        return PERM(ix + PERM(iy + PERM(iz)));
    }

    private double latticeNoise(long i, long j, long k){
        return noiseTable[INDEX(i,j,k)];
    }

    private double lerp(double f, double A, double B){
        return A + f * (B - A);
    }

    public double noise(double scale, double x, double y, double z){
        double[][][] d = new double[2][2][2];
        double internal_x = x * scale + 10000; // offset avoids negative values
        double internal_y = y * scale + 10000;
        double internal_z = z * scale + 10000;
        long ix = (long) internal_x; long iy = (long) internal_y; long iz = (long) internal_z;
        double tx,ty,tz,x0,x1,x2,x3,y0,y1;
        tx = internal_x - ix; ty = internal_y - iy; tz = internal_z - iz; //fractional parts
        double mtx = 1.0 - tx; double mty = 1.0 - ty; double mtz = 1.0 - tz;

        for(int k = 0; k <= 1; k++){
            for(int j = 0; j <= 1; j++){
                for(int i = 0; i <= 1; i++){
                    d[k][j][i] = latticeNoise(ix + i, iy + j, iz + k);
                }
            }
        }

        x0 = lerp(tx, d[0][0][0], d[0][0][1]);
        x1 = lerp(tx, d[0][1][0], d[0][1][1]);
        x2 = lerp(tx, d[1][0][0], d[1][0][1]);
        x3 = lerp(tx, d[1][1][0], d[1][1][1]);
        y0 = lerp(tx, x0, x1);
        y1 = lerp(tx, x2, x3);
        return lerp(tz, y0, y1);
    }

    public double turb(double scale, double x, double y, double z){
        return (0.5)*noise(scale,x,y,z) + (0.25)*noise(2*scale,x,y,z) + (0.125)*noise(4*scale,x,y,z);
    }

    @Override
    public double texture(double x, double y, double z) {
        return turb(scale, x, y, 0);
    }
}