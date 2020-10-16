public class Sphere extends Object {
    public Sphere(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        super(transformation_matrix, inverse_transformation_matrix);
    }

    //Hier gaan we dan daadwerkelijk de hitregistratie doen
    //Op het moment enkel geimplementeerd voor generic spheres
    //Moet nog aangepast worden naar hitregistratie met alle transformeerde spheres zie p620
    @Override
    public double hit_reg(Ray ray) {
        return sphere_hit_detec(ray);
    }

    public double sphere_hit_detec(Ray ray){
        //Hier gaan we A,B en C eerst berekenen
        Point S = ray.get_eye();
        Vector c = ray.get_dir();
        double A = Math.pow(c.get_X(), 2) + Math.pow(c.get_Y(), 2) + Math.pow(c.get_Z(), 2);
        double B = c.get_X() * S.get_X() + c.get_Y() * S.get_Y() + c.get_Z() * S.get_Z();
        double C = Math.pow(S.get_X(), 2) + Math.pow(S.get_Y(), 2) + Math.pow(S.get_Z(), 2) - 1;
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
}
