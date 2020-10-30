package shapes;

import internal.Matrix;
import internal.Point;
import internal.Vector;

public class Sphere extends Object {
    public Sphere(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }

    //Hier gaan we dan daadwerkelijk de hitregistratie doen
//    @Override
//    public internal.HitObject hit_reg(internal.Ray ray) {
//        //Kan ik hier initialiseren of meegeven als parameter (not sure wat het beste is)
//        internal.Point S = ray.get_eye();
//        internal.Vector c = ray.get_dir();
//        internal.MatrixTransformer matrixTransformer = new internal.MatrixTransformer();
//        //1. Have to transform ray with inverse transformation off sphere
//        System.out.println(Arrays.deepToString(c.get_vector()));
//        System.out.println(Arrays.deepToString(this.get_inverse_transformation_matrix().get_matrix()));
//        double[][] point_s = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), S.get_point());
//        double[][] vector_c = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), c.get_vector());
//        System.out.println("point: " + Arrays.deepToString(point_s));
//        System.out.println("vector: " + Arrays.deepToString(vector_c));
//        internal.Point S_t = new internal.Point(point_s);
//        internal.Vector c_t = new internal.Vector(vector_c);
//
//        //2. Calculate hit_time with transformed ray
//        double t_hit = sphere_hit_detec(S_t, c_t);
//        //3. Going to create a hit object here and calculate hit point with original ray
//        internal.Point hitPoint = calculate_hit_point(S, c, t_hit);
//        return new internal.HitObject(hitPoint, t_hit);
//    }

    @Override
    double sphere_hit_detec(Point S_t, Vector c_t){
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
            //System.out.println("Geen hitpunten");
        }else if(Discriminant == 0){
            t_hit = (-B)/A;
            //System.out.println("hit");
        }else{
            double t_hit1 = (-B)/A + Math.sqrt(Discriminant)/A;
            double t_hit2 = (-B)/A - Math.sqrt(Discriminant)/A;
            //Find lowest hit time
            t_hit = Math.min(t_hit1, t_hit2);
            //System.out.println("hit");
        }
        return t_hit;
    }

    @Override
    Point calculate_hit_point(Point S, Vector c, double t_hit){
        double x = S.get_X() + c.get_X()*t_hit;
        double y = S.get_Y() + c.get_Y()*t_hit;
        double z = S.get_Z() + c.get_Z()*t_hit;
        return new Point(x, y, z);
    }

    @Override
    Vector calculate_normal_vector(Point hitPoint) {
        //Ok dus hier gaan we een normal vector berekenen van de surface op hit punt hitPoint
        //Normal vector van bol wordt berekent
        //We gaan gewoon eerst een hitpunt berekenen met de generieke bol en dan de vector transformeren met de inverse transformatie matrix.
        Point origin = new Point(0, 0, 0);
        return getInternalTransformer().substraction_to_vector(hitPoint,origin);
    }
}
