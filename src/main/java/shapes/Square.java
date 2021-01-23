package shapes;

import internal.*;
import internal.Point;
import texture.Texture;

import java.awt.*;
import java.util.ArrayList;

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

    public Square(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double local_coeff, double reflection_coeff, double refraction_coeff, double c, Color color, Texture texture) {
        super(transformation_matrix, inverse_transformation_matrix, local_coeff, reflection_coeff, refraction_coeff, c, color, texture);
    }

    @Override
    ArrayList<Hit> object_hit_detec(Point S_t, Vector c_t, Intersection intersection) {
        ArrayList<Hit> hit_times = new ArrayList<>();
        //Hier krijgen we al de invers getransformeerde ray binnen (dus stap 1 is completed)
        //Nu moeten we de hit_time vinden en deze terug geven.
        if (S_t.get_Z() != 0) {
            //Dit berekent de hit_time wanneer er een intersectie is en returned deze
            double hit_time = -(S_t.get_Z() / c_t.get_Z());
            Hit hit = new Hit(hit_time, false);
            hit_times.add(hit);
        }  //else: Geen intersectie
        return hit_times;
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
