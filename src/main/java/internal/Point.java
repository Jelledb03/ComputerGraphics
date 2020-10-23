package internal;

public class Point {
    private double[][] point;
    private double x;
    private double y;
    private double z;

    //Creates a point with x and y coordinates
    public Point(double x, double y) {
        this.point = new double[][]{
                new double[]{x},
                new double[]{y},
                new double[]{1}
        };
        this.x = x;
        this.y = y;
        this.z = 0;
    }
    public Point(double x, double y, double z) {
        this.point = new double[][]{
                new double[]{x},
                new double[]{y},
                new double[]{z},
                new double[]{1}
        };
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Have to implement checkers that it is indeed a point? Otherwise not allow it?
    public Point(double[][] point) {
        if(point.length == 3 && point[0].length == 1){
            this.point = point;
            this.x = point[0][0];
            this.y = point[1][0];
            this.z = 0;
        }
        else if(point.length == 4 && point[0].length == 1){
            this.point = point;
            this.x = point[0][0];
            this.y = point[1][0];
            this.z = point[2][0];
        }else {
            //Can we return a error message in another else loop, where the point can't be created because it isn't a point
            throw new IllegalArgumentException("Vector size needs to be right");
        }
    }

    public double[][] get_point() {
        return point;
    }

    public void set_point(double[][] point) {
        this.point = point;
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
