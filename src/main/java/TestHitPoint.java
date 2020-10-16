public class TestHitPoint {
    public static void main(String[] args) {
        //work objects
        Matrix3DFactory matrix3DFactory = new Matrix3DFactory();
        //Ray parameters
        Point eye = new Point(3, 4, 5);
        Vector direction = new Vector(-3.5, -4, -4.5);
        Ray ray = new Ray(eye, direction);

        //Sphere Transformation matrices
        double m14 = 0;
        double m24 = 0;
        double m34 = 0;

        Matrix sphere_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix sphere_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Sphere genericSphere = new Sphere(sphere_transformation_matrix, sphere_inv_transformation_matrix);

        double t_hit = genericSphere.sphere_hit_detec(ray);
        System.out.println(t_hit);

        Point hitPoint = hit_point_calculation(ray, t_hit);

        System.out.println(hitPoint.get_X() + " " + hitPoint.get_Y() + " " + hitPoint.get_Z());
    }

    public static Point hit_point_calculation(Ray ray, double t_hit){
        Point S = ray.get_eye();
        Vector c = ray.get_dir();
        double x = S.get_X() + c.get_X()*t_hit;
        double y = S.get_Y() + c.get_Y()*t_hit;
        double z = S.get_Z() + c.get_Z()*t_hit;
        return new Point(x, y, z);
    }
}
