package shapes;

import internal.Matrix;
import internal.Point;
import internal.Vector;

import java.awt.*;

public class Cylinder extends Object {
    private double s;

    public Cylinder(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double c, Color color, double s) {
        super(transformation_matrix, inverse_transformation_matrix, c, color);
        this.s = s;
    }

    public Cylinder(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double s) {
        super(transformation_matrix, inverse_transformation_matrix);
        this.s = s;
    }

    public Cylinder(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double local_coeff, double reflection_coeff, double refraction_coeff, double c, Color color, double s) {
        super(transformation_matrix, inverse_transformation_matrix, local_coeff, reflection_coeff, refraction_coeff, c, color);
        this.s = s;
    }

    @Override
    double sphere_hit_detec(Point S_t, Vector c_t) {
        //Wall Check
        //A = c²x + c²y - d²
        //B = Sx*cx + Sy*cy - F*d
        //C = S²x + S²y - F²
        //
        //d = (s-1)cz
        //F = 1 + (s-1)Sz
        double d = (s - 1) * c_t.get_Z();
        double F = 1 + (s - 1) * S_t.get_Z();
        double A = Math.pow(c_t.get_X(), 2) + Math.pow(c_t.get_Y(), 2) - Math.pow(d, 2);
        double B = c_t.get_X() * S_t.get_X() + c_t.get_Y() * S_t.get_Y() - F * d;
        double C = Math.pow(S_t.get_X(), 2) + Math.pow(S_t.get_Y(), 2) - Math.pow(F, 2);
        double Discriminant = Math.pow(B, 2) - A * C;
//        System.out.println(A);
//        System.out.println(B);
//        System.out.println(C);
//        System.out.println(Discriminant);
        double t_hit1 = 0;
        double t_hit2 = 0;
        //lowest hit time;
        double t_hit_min = 0;
        if (Discriminant < 0) {
            //System.out.println("Geen hitpunten");
        } else if (Discriminant == 0) {
            t_hit1 = t_hit2 = (-B) / A;
        } else {
            t_hit1 = (-B) / A + Math.sqrt(Discriminant) / A;
            t_hit2 = (-B) / A - Math.sqrt(Discriminant) / A;
        }
        //System.out.println(t_hit1);
        //System.out.println(t_hit2);
        //Moeten deze internal.Point objecten aanmaken om na te gaan of de hitpoints wel in de cylinder liggen.
        Point hit_point_1 = calculate_hit_point(S_t, c_t, t_hit1);
        Point hit_point_2 = calculate_hit_point(S_t, c_t, t_hit2);
        boolean correct_hit_1 = false;
        boolean correct_hit_2 = false;
        //betekent dat de hitpoint in de cylinder ligt
        if (hit_point_1.get_Z() > 0 && hit_point_1.get_Z() < 1)
            correct_hit_1 = true;
        if (hit_point_2.get_Z() > 0 && hit_point_2.get_Z() < 1)
            correct_hit_2 = true;
        if (correct_hit_1 && correct_hit_2) {
            //Uiteindelijk willen we hier enkel de kleinste hit time returnen
            //Surface is hier 0
            //Misschien hier nog nakijken of hit_time groter is dan 0. Wel nog eens navragen
            t_hit_min = Math.min(t_hit1, t_hit2);
        } else if (correct_hit_1) {
            t_hit_min = t_hit1;
        } else if (correct_hit_2) {
            t_hit_min = t_hit2;
        }

        /*

        //Base
        //Intersectie bepalen met z=0
        //Intersectie als x² + y² < 1
        //Oog mag niet op z=0 zitten (want dan is er al een intersectie op tijdstip 0 en er moet ook een z beweging zijn van de ray)
        if (S_t.get_Z() != 0 && c_t.get_Z() !=0) {
            //Dit berekent de hit_time wanneer er een intersectie is en returned deze
            t_hit1 = -(S_t.get_Z() / c_t.get_Z());
            hit_point_1 = calculate_hit_point(S_t, c_t, t_hit1);
            double x = hit_point_1.get_X();
            double y = hit_point_1.get_Y();
            //Betekent dat hitpoint in de base van de cylinder ligt
            if(Math.pow(x, 2) + Math.pow(y, 2) < 1)
                t_hit_min = Math.min(t_hit_min, t_hit1);
                //Hit zal op base eerst gebeuren
                if(t_hit_min == t_hit1)
                    this.setSurface(1);
        }
        //Cap
        //Intersectie bepalen met z=1
        //Intersectie als x² + y² < s²
        //Oog mag niet op z=1 zitten (want dan is er al een intersectie op tijdstip 0 en er moet ook een z beweging zijn van de ray)
        //Ik denk dat die if statement niet juist is
        //Iets is hier verkeerd mee
        if (S_t.get_Z() != 1 && c_t.get_Z() !=0) {
            //Dit berekent de hit_time wanneer er een intersectie is en returned deze
            t_hit1 = (1 - S_t.get_Z()) / c_t.get_Z();
            hit_point_1 = calculate_hit_point(S_t, c_t, t_hit1);
            double x = hit_point_1.get_X();
            double y = hit_point_1.get_Y();
            //Betekent dat hitpoint in de cap van de cylinder ligt
            if(Math.pow(x, 2) + Math.pow(y, 2) < Math.pow(s, 2))
                t_hit_min = Math.min(t_hit_min, t_hit1);
            //hit zal gebeuren op cap
            if(t_hit_min == t_hit1)
                this.setSurface(2);
        }

         */

        return t_hit_min;
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
        switch (surface) {
            case (0)://Surface is wall
                //normal to wall at point P(x,y,z) = (x,y,(-(s-1)(1+(s-1)z)))
                double normal_x = hitPoint.get_X();
                double normal_y = hitPoint.get_Y();
                double normal_z = (-(s - 1) * (1 + (s - 1) * hitPoint.get_Z()));
                return new Vector(normal_x, normal_y, normal_z);
            case (1)://Surface is Base //Dus punt ligt op vlak z = 0
                return new Vector(0, 0, -1);
            default://Surface is Cap //Dus punt ligt op vlak z = 1
                return new Vector(0, 0, 1);
        }
    }
}
