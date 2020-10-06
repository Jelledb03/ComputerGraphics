public class Point {
    double[] point;

    //Creates a point with x and y coordinates
    public Point(double x, double y) {
        this.point = new double[]{x,y,1};
    }
    public Point(double x, double y, double z) {
        this.point = new double[]{x,y,z,1};
    }
}
