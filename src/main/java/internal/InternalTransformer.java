package internal;

public class InternalTransformer {
    public InternalTransformer() {
    }
    public Vector vector_product(Vector vector, double N){
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
}
