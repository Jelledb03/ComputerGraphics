public class Vector {
    private double[][] vector;

    //Creates a vector with x and y coordinates
    public Vector(double x, double y) {
        this.vector = new double[][]{
                new double[]{x},
                new double[]{y},
                new double[]{0}
        };
    }

    //Creates a vector with x and y coordinates
    public Vector(double x, double y, double z) {
        this.vector = new double[][]{
                new double[]{x},
                new double[]{y},
                new double[]{z},
                new double[]{0}
        };
    }

    public double[][] get_vector() {
        return vector;
    }

    public void set_vector(double[][] vector) {
        this.vector = vector;
    }
}