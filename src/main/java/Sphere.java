import java.util.Arrays;

public class Sphere extends Object {
    public Sphere(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }

    //Hier gaan we dan daadwerkelijk de hitregistratie doen
    //Op het moment enkel geimplementeerd voor generic spheres
    //Moet nog aangepast worden naar hitregistratie met alle transformeerde spheres zie p620
    @Override
    public HitObject hit_reg(Ray ray) {
        //Kan ik hier initialiseren of meegeven als parameter (not sure wat het beste is)
        Point S = ray.get_eye();
        Vector c = ray.get_dir();
        MatrixTransformer matrixTransformer = new MatrixTransformer();
        //1. Have to transform ray with inverse transformation off sphere
        System.out.println(Arrays.deepToString(c.get_vector()));
        System.out.println(Arrays.deepToString(this.get_inverse_transformation_matrix().get_matrix()));
        double[][] point_s = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), S.get_point());
        double[][] vector_c = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), c.get_vector());
        System.out.println("point: " + Arrays.deepToString(point_s));
        System.out.println("vector: " + Arrays.deepToString(vector_c));
        Point S_t = new Point(point_s);
        Vector c_t = new Vector(vector_c);

        //2. Calculate hit_time with transformed ray
        double t_hit = sphere_hit_detec(S_t, c_t);
        //3. Going to create a hit object here and calculate hit point with original ray
        Point hitPoint = calculate_hit_point(S, c, t_hit);
        return new HitObject(hitPoint, t_hit);
    }

    private double sphere_hit_detec(Point S_t, Vector c_t){
        //Hier gaan we A,B en C eerst berekenen
        double A = Math.pow(c_t.get_X(), 2) + Math.pow(c_t.get_Y(), 2) + Math.pow(c_t.get_Z(), 2);
        double B = c_t.get_X() * S_t.get_X() + c_t.get_Y() * S_t.get_Y() + c_t.get_Z() * S_t.get_Z();
        double C = Math.pow(S_t.get_X(), 2) + Math.pow(S_t.get_Y(), 2) + Math.pow(S_t.get_Z(), 2) - 1;
        double Discriminant = Math.pow(B, 2) - A * C;
//        System.out.println(A);
//        System.out.println(B);
//        System.out.println(C);
        double t_hit = 0;
        if(Discriminant < 0){
            System.out.println("Geen hitpunten");
        }else if(Discriminant == 0){
            t_hit = (-B)/A;
        }else{
            double t_hit1 = (-B)/A + Math.sqrt(Discriminant)/A;
            double t_hit2 = (-B)/A - Math.sqrt(Discriminant)/A;
            //Find lowest hit time
            t_hit = Math.min(t_hit1, t_hit2);
        }
        return t_hit;
    }

    private Point calculate_hit_point(Point S, Vector c, double t_hit){
        double x = S.get_X() + c.get_X()*t_hit;
        double y = S.get_Y() + c.get_Y()*t_hit;
        double z = S.get_Z() + c.get_Z()*t_hit;
        return new Point(x, y, z);
    }
}
