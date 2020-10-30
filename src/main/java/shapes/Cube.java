package shapes;

import internal.Matrix;
import internal.Point;
import internal.Vector;

import java.awt.*;

public class Cube extends Object {
    public Cube(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }

    public Cube(Matrix transformation_matrix, Matrix inverse_transformation_matrix, Color color) {
        super(transformation_matrix, inverse_transformation_matrix, color);
    }

    @Override
    double sphere_hit_detec(Point S_t, Vector c_t) {
        return 0;
    }

    @Override
    Point calculate_hit_point(Point S, Vector c, double t_hit) {
        return null;
    }

    @Override
    Vector calculate_normal_vector(Point hitPoint) {
        return null;
    }
}
