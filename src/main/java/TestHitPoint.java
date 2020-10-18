public class TestHitPoint {
    public static void main(String[] args) {
        //work objects
        Matrix3DFactory matrix3DFactory = new Matrix3DFactory();
        //Ray parameters
        Point eye = new Point(2, 2, 2);
        Vector direction = new Vector(-1, -1, -1);
        Ray ray = new Ray(eye, direction);

        //Sphere Transformation matrices
        double m14 = 0;
        double m24 = 0;
        double m34 = 0;

        Matrix sphere_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix sphere_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Sphere genericSphere = new Sphere(sphere_transformation_matrix, sphere_inv_transformation_matrix);

        HitObject hit = genericSphere.sphere_hit_detec(ray);
        System.out.println(hit.get_hit_time());


        System.out.println(hit.get_hit_point().get_X() + " " + hit.get_hit_point().get_Y() + " " + hit.get_hit_point().get_Z());
    }
}
