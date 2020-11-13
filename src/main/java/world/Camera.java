package world;

import config.Config;
import internal.InternalTransformer;
import internal.Point;
import internal.Vector;

public class Camera {
    //Stelt het oog voor vanuit waar we naar de 3D wereld gaan kijken
    private Point eye;
    //Zijn de drie vectoren die de richting naar waar het oog kijkt beschrijven
    private Vector u;
    private Vector v;
    private Vector n;

    //Deze zijn de helft van de breedte en hoogte van de viewport
    // (2D monitor scherm wat je dus gaat illustreren)
    private int W;
    private int H;

    //N slaat op de afstand dat de camera zich van de viewport bevindt
    private double N;
    private InternalTransformer internalTransformer = new InternalTransformer();

    public Camera() {
        this.eye = new Point(1, 1, 1);
        this.u = new Vector(0, 1, 0);
        this.v = new Vector(0, 0, 1);
        this.n = new Vector(1, 0, 0);
        this.W = Config.SCREEN_WIDTH/2;
        this.H = Config.SCREEN_HEIGHT/2;
        this.N = 1;
    }

    public Camera(Point eye, Vector u, Vector v, Vector n, int W, int H, double N) {
        this.eye = eye;
        this.u = u;
        this.v = v;
        this.n = n;
        this.W = W;
        this.H = H;
        this.N = N;
    }

    public Camera(Point eye, Point look, Vector up, double N){
        this.eye = eye;
        this.n = internalTransformer.substraction_to_vector(eye, look);
        this.u = internalTransformer.cross_product(up, this.n);
        this.v = internalTransformer.cross_product(this.n, this.u);
        this.W = Config.SCREEN_WIDTH/2;
        this.H = Config.SCREEN_HEIGHT/2;
        this.N = N;
    }

    public Point get_eye() {
        return eye;
    }

    public void set_eye(Point eye) {
        this.eye = eye;
    }

    public Vector get_u() {
        return u;
    }

    public void set_u(Vector u) {
        this.u = u;
    }

    public Vector get_v() {
        return v;
    }

    public void set_v(Vector v) {
        this.v = v;
    }

    public Vector get_n() {
        return n;
    }

    public void set_N(double n) {
        N = n;
    }

    public double get_N() {
        return N;
    }

    public void set_n(Vector n) {
        this.n = n;
    }

    public int get_w() {
        return W;
    }

    public void set_w(int w) {
        W = w;
    }

    public int get_h() {
        return H;
    }

    public void set_h(int h) {
        H = h;
    }
}
