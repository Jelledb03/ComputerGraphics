package shapes;

import exterior.material.Material;
import internal.*;
import internal.Point;
import exterior.texture.Texture;

import java.awt.*;
import java.util.ArrayList;

public class Cube extends Object {
    public Cube(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }

    public Cube(Matrix transformation_matrix, Matrix inverse_transformation_matrix, Color color) {
        super(transformation_matrix, inverse_transformation_matrix, color);
    }

    public Cube(Matrix transformation_matrix, Matrix inverse_transformation_matrix, Material material, Color color) {
        super(transformation_matrix, inverse_transformation_matrix, material, color);
    }

    public Cube(Matrix transformation_matrix, Matrix inverse_transformation_matrix, Material material, Color color, Texture texture) {
        super(transformation_matrix, inverse_transformation_matrix, material, color, texture);
    }

    @Override
    ArrayList<Hit> object_hit_detec(Point S_t, Vector c_t, Intersection intersection) {
        //Going through all surfaces and find the inner and outer hit time in the cube
        ArrayList<Hit> hit_times = new ArrayList<>();
        double t_hit, numer, denom;
        double t_in = Double.NEGATIVE_INFINITY, t_out = Double.POSITIVE_INFINITY;
        int surf_in = 0, surf_out = 0;
        //Will loop through all surfaces and detect lowest exit and highest entry hit time
        for (int i = 0; i < 6; i++) {
            switch (i) {
                //Surface 0
                case 0:
                    numer = 1.0 - S_t.get_Y();
                    denom = c_t.get_Y();
                    break;
                //Surface 1
                case 1:
                    numer = 1.0 + S_t.get_Y();
                    denom = -c_t.get_Y();
                    break;
                //Surface 2
                case 2:
                    numer = 1.0 - S_t.get_X();
                    denom = c_t.get_X();
                    break;
                //Surface 3
                case 3:
                    numer = 1.0 + S_t.get_X();
                    denom = -c_t.get_X();
                    break;
                //Surface 4
                case 4:
                    numer = 1.0 - S_t.get_Z();
                    denom = c_t.get_Z();
                    break;
                //Surface 5
                default:
                    numer = 1.0 + S_t.get_Z();
                    denom = -c_t.get_Z();
                    break;
            }
            //find t_in and t_out for each surface and compare
            if (Math.abs(denom) < 0.00001) {//ray is parallel
                if (numer < 0) { //ray is out
                    //No hit_time means there wasn't a hit so no new hits are added
                    return hit_times; //Ray is out, so no hit_time
                }
            } else {//ray is not parallel
                t_hit = numer / denom;
                if (denom > 0) { //exiting
                    if (t_hit < t_out) { // a new earlier exit
                        t_out = t_hit;
                        surf_out = i;
                    }
                } else {//entering
                    if (t_hit > t_in) { // a new later entrance
                        t_in = t_hit;
                        surf_in = i;
                    }
                }
            }
            if (t_in >= t_out) { // a miss
                return hit_times;
            }
        }
        //end of for loop
        //First need to check if t_in & t_out are not infinity
        if(t_in == Double.NEGATIVE_INFINITY && t_out == Double.POSITIVE_INFINITY){
            //There were no hits
            return hit_times;
        }
        int num_hits = 0;
        if (t_in > 0.00001) {
            //p635 doet hier nog veel meer, not sure if necessary
            Hit hit_in = new Hit(t_in, true);
            hit_times.add(hit_in);

        }
        if (t_out > 0.00001) {
            Hit hit_out = new Hit(t_out, false);
            hit_times.add(hit_out);
        }
        //Check which of the two values is lowest and return
        //Don't care anymore about the lowest hit time here, we are collecting all hit times.
        if (t_in < t_out) {
            this.setSurface(surf_in);
        } else {
            this.setSurface(surf_out);
        }
        return hit_times;
    }

    @Override
    Point calculate_hit_point(Point S, Vector c, double t_hit) {
        double x = S.get_X() + c.get_X() * t_hit;
        double y = S.get_Y() + c.get_Y() * t_hit;
        double z = S.get_Z() + c.get_Z() * t_hit;
        return new Point(x, y, z);
    }

    @Override
    Vector calculate_normal_vector(Point hitPoint, int surface) {
        //In principe moeten we enkel kijken naar de surface hier (omdat we werken met vlakken)
        switch (surface) {
            case (0):
                //y = 1
                return new Vector(0, 1, 0);
            case (1):
                //y = -1
                return new Vector(0, -1, 0);
            case (2):
                //x = 1
                return new Vector(1, 0, 0);
            case (3):
                //x = -1
                return new Vector(-1, 0, 0);
            case (4):
                //z = 1
                return new Vector(0, 0, 1);
            default:
                //z = -1
                return new Vector(0, 0, -1);
        }
    }
}
