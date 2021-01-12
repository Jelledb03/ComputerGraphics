package shapes;

import internal.Matrix;
import internal.Point;
import internal.Vector;

import java.awt.*;

public class Square extends Object {
    //In boek p630 spreken ze over de hitregistratie met een square.
    //De generieke square ligt op de z=0 plane en heeft coordinaten -1 <= Px <= 1 en -1 <= Py <= 1
    //Om nu na te gaan of er een intersectie is met de square gaat er eerst gekeken worden of er een intersectie is met
    //het generieke vlak (wat de z=0 plane is). Daarna wordt er gekeken met de hittime of het hit point in de square ligt
    //Zoja is er een intersectie met de square en moet er een hit object worden aangemaakt. Zo nee is er geen intersectie.

    public Square(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double c, Color color) {
        super(transformation_matrix, inverse_transformation_matrix, c, color);
    }

    public Square(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }

    public Square(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double local_coeff,double reflection_coeff,double refraction_coeff,double c, Color color) {
        super(transformation_matrix, inverse_transformation_matrix,local_coeff,reflection_coeff,refraction_coeff, c, color);
    }

    @Override
    double object_hit_detec(Point S_t, Vector c_t) {
        //Hier krijgen we al de invers getransformeerde ray binnen (dus stap 1 is completed)
        //Nu moeten we de hit_time vinden en deze terug geven.
        if (S_t.get_Z() == 0) {
            //Geen intersectie
            // hit_time 0 teruggeven
            return 0;
        } else {
            //Dit berekent de hit_time wanneer er een intersectie is en returned deze
            return -(S_t.get_Z() / c_t.get_Z());
        }
    }

    @Override
    Point calculate_hit_point(Point S, Vector c, double t_hit) {
        //Hier wordt dan stap 3 uitgevoerd en wordt er nagekeken of de hitpoint in de square zit. Als dit niet zo is null teruggeven en laten verwerken door main functie hit_reg
        double x = S.get_X() + c.get_X()*t_hit;
        double y = S.get_Y() + c.get_Y()*t_hit;
        double z = S.get_Z() + c.get_Z()*t_hit;
        //Check if hitpoint is in square
        if((x>=-1 && x<=1) && (y>=-1 && y<=1)){
            //Hitpoint is in square
            return new Point(x, y, z);
        }else{
            //Hitpoint is not in square
            return null;
        }


    }

    @Override
    Vector calculate_normal_vector(Point hitPoint, int surface) {
        return null;
    }
}
