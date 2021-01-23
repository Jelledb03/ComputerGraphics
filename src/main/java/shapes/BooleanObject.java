package shapes;

import internal.*;
import internal.Point;
import texture.Texture;

import java.awt.*;
import java.util.ArrayList;

public class BooleanObject extends Object{
    private Object left;
    private Object right;
    boolean left_inside;
    boolean right_inside;
    boolean comb_inside;

    public BooleanObject(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double local_coeff, double reflection_coeff, double refraction_coeff, double c, Color color, Texture texture) {
        super(transformation_matrix, inverse_transformation_matrix, local_coeff, reflection_coeff, refraction_coeff, c, color, texture);
    }

    public BooleanObject(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double local_coeff, double reflection_coeff, double refraction_coeff, double c, Color color) {
        super(transformation_matrix, inverse_transformation_matrix, local_coeff, reflection_coeff, refraction_coeff, c, color);
    }

    public BooleanObject(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double c, Color color) {
        super(transformation_matrix, inverse_transformation_matrix, c, color);
    }

    public BooleanObject(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }


    //These functions will not be used with Boolean Objects
    @Override
    Vector calculate_normal_vector(Point hitPoint, int surface) {
        return null;
    }

    @Override
    ArrayList<Hit> object_hit_detec(Point S_t, Vector c_t, Intersection intersection) {
        return null;
    }

    @Override
    Point calculate_hit_point(Point S, Vector c, double t_hit) {
        return null;
    }
}
