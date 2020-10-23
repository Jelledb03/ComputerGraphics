package shapes;

import internal.*;
import internal.MatrixTransformer;

import java.util.Arrays;

//Wanneer we dit als abstract classificieren kunnen we hier geen objecten van initialiseren
public abstract class Object {
    private Matrix transformation_matrix;
    private Matrix inverse_transformation_matrix;

    public Object(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        this.transformation_matrix = transformation_matrix;
        this.inverse_transformation_matrix = inverse_transformation_matrix;
    }

    public Matrix get_transformation_matrix() {
        return transformation_matrix;
    }

    public void set_transformation_matrix(Matrix transformation_matrix) {
        this.transformation_matrix = transformation_matrix;
    }

    public Matrix get_inverse_transformation_matrix() {
        return inverse_transformation_matrix;
    }

    public void set_inverse_transformation_matrix(Matrix inverse_transformation_matrix) {
        this.inverse_transformation_matrix = inverse_transformation_matrix;
    }

    //Zal een hitpoint time terug geven van wanneer de ray dit object hit
    //Nadat dit voor alle objecten gebeurd is zal er moeten nagekeken worden wat de laagste hit time is en hiermee het hitpoint berekenen
    //Want in dit hitpoint zal de ray daadwerkelijk komen en raken. De andere hitpoints kunnen mogelijk zijn van objecten achter dit object (zijn we niet in geintresseerd)
    //Verder uitwerken in de overgeerfde klassen
    public HitObject hit_reg(Ray ray) {
        //Kan ik hier initialiseren of meegeven als parameter (not sure wat het beste is)
        Point S = ray.get_eye();
        Vector c = ray.get_dir();
        MatrixTransformer matrixTransformer = new MatrixTransformer();
        //1. Have to transform ray with inverse transformation off sphere
        //System.out.println(Arrays.deepToString(c.get_vector()));
        //System.out.println(Arrays.deepToString(this.get_inverse_transformation_matrix().get_matrix()));
        double[][] point_s = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), S.get_point());
        double[][] vector_c = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), c.get_vector());
        //System.out.println("point: " + Arrays.deepToString(point_s));
        //System.out.println("vector: " + Arrays.deepToString(vector_c));
        Point S_t = new Point(point_s);
        Vector c_t = new Vector(vector_c);

        //2. Calculate hit_time with transformed ray
        double t_hit = sphere_hit_detec(S_t, c_t);
        if (t_hit == 0) {
            return new HitObject();
        } else {
            //3. Going to create a hit object here and calculate hit point with original ray
            Point hitPoint = calculate_hit_point(S, c, t_hit);
            if (hitPoint == null) {
                //This can happen, for example when looking for an intersection with a square
                return new HitObject();
            } else {
                return new HitObject(hitPoint, t_hit);
            }
        }
    }

    abstract double sphere_hit_detec(Point S_t, Vector c_t);

    abstract Point calculate_hit_point(Point S, Vector c, double t_hit);
}
