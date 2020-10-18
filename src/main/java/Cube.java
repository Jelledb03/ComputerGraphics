public class Cube extends Object {
    public Cube(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }

    @Override
    double sphere_hit_detec(Point S_t, Vector c_t) {
        return 0;
    }

    @Override
    Point calculate_hit_point(Point S, Vector c, double t_hit) {
        return null;
    }
}
