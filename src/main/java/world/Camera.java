package world;

import config.Config;
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
}
