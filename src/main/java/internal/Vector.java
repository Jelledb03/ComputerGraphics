package internal;

public class Vector {
    private double[][] vector;
    private double x;
    private double y;
    private double z;

    //Creates a vector with x and y coordinates
    public Vector(double x, double y) {
        this.vector = new double[][]{
                new double[]{x},
                new double[]{y},
                new double[]{0}
        };
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    //Creates a vector with x and y coordinates
    public Vector(double x, double y, double z) {
        this.vector = new double[][]{
                new double[]{x},
                new double[]{y},
                new double[]{z},
                new double[]{0}
        };
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Have to implement checkers that it is indeed a point? Otherwise not allow it?
    public Vector(double[][] vector) {
        if(vector.length == 3 && vector[0].length == 1){
            this.vector = vector;
            this.x = vector[0][0];
            this.y = vector[1][0];
            this.z = 0;
        }
        else if(vector.length == 4 && vector[0].length == 1){
            this.vector = vector;
            this.x = vector[0][0];
            this.y = vector[1][0];
            this.z = vector[2][0];
        }else {
            //Can we return a error message in another else loop, where the point can't be created because it isn't a point
            throw new IllegalArgumentException("Vector size needs to be right");
        }
    }

    public double[][] get_vector() {
        return vector;
    }

    public void set_vector(double[][] vector) {
        this.vector = vector;
    }

    public double get_X() {
        return x;
    }

    public void set_X(double x) {
        this.x = x;
    }

    public double get_Y() {
        return y;
    }

    public void set_Y(double y) {
        this.y = y;
    }

    public double get_Z() {
        return z;
    }

    public void set_Z(double z) {
        this.z = z;
    }
}