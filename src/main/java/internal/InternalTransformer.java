package internal;

public class InternalTransformer {
    public InternalTransformer() {
    }

    public Vector vector_product(Vector vector, double N) {
        double vector_x = vector.get_X() * N;
        double vector_y = vector.get_Y() * N;
        double vector_z = vector.get_Z() * N;
        return new Vector(vector_x, vector_y, vector_z);
    }

    public Vector tripleVectorSum(Vector dir_rc_n, Vector dir_rc_u, Vector dir_rc_v) {
        //Performs sum of each x,y,z coordinate
        double vector_x = dir_rc_n.get_X() + dir_rc_u.get_X() + dir_rc_v.get_X();
        double vector_y = dir_rc_n.get_Y() + dir_rc_u.get_Y() + dir_rc_v.get_Y();
        double vector_z = dir_rc_n.get_Z() + dir_rc_u.get_Z() + dir_rc_v.get_Z();
        return new Vector(vector_x, vector_y, vector_z);
    }

    public double dot_product(Vector v1, Vector v2){
        return v1.get_X() * v2.get_X() + v1.get_Y() * v2.get_Y() + v1.get_Z() * v2.get_Z();
    }

    //Calculates the normal vector of two vectors
    public Vector cross_product(Vector v1, Vector v2) {
        double cross_vector_x = v1.get_Y() * v2.get_Z() - v1.get_Z() * v2.get_Y();
        double cross_vector_y = v1.get_Z() * v2.get_X() - v1.get_X() * v2.get_Z();
        double cross_vector_z = v1.get_X() * v2.get_Y() - v1.get_Y() * v2.get_X();
        return new Vector(cross_vector_x, cross_vector_y, cross_vector_z);
    }

    public Vector substraction_to_vector(Point p1, Point p2) {
        double vector_x = p1.get_X() - p2.get_X();
        double vector_y = p1.get_Y() - p2.get_Y();
        double vector_z = p1.get_Z() - p2.get_Z();
        return new Vector(vector_x, vector_y, vector_z);
    }

    public Vector inverse_vector(Vector ray) {
        double vector_x = -ray.get_X();
        double vector_y = -ray.get_Y();
        double vector_z = -ray.get_Z();
        return new Vector(vector_x,vector_y,vector_z);
    }
}
