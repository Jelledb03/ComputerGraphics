import internal.*;
import internal.Matrix3DFactory;
import shapes.Cylinder;
import shapes.Sphere;
import shapes.Square;

public class TestHitPoint {
    public static void main(String[] args) {
        //work objects
        Matrix3DFactory matrix3DFactory = new Matrix3DFactory();
        //internal.Ray parameters
        Point eye = new Point(0.5, -0.5, 0.5);
        Vector direction = new Vector(-0.5, 0.5, 0);
        Ray ray = new Ray(eye, direction);

        //objects.Sphere Transformation matrices
        double m14 = 0;
        double m24 = 0;
        double m34 = 0;

        //cylinder parameter
        double s = 0;

        Matrix object_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix object_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Sphere genericSphere = new Sphere(object_transformation_matrix, object_inv_transformation_matrix);
        Square genericSquare = new Square(object_transformation_matrix, object_inv_transformation_matrix);
        Cylinder genericCylinder = new Cylinder(object_transformation_matrix, object_inv_transformation_matrix, s);

//        internal.HitObject hit_Sphere = genericSphere.hit_reg(ray);
//        System.out.println(hit_Sphere.get_hit_time());
//
//        System.out.println(hit_Sphere.get_hit_point().get_X() + " " + hit_Sphere.get_hit_point().get_Y() + " " + hit_Sphere.get_hit_point().get_Z());
//
//        internal.HitObject hit_Square = genericSquare.hit_reg(ray);
//        System.out.println(hit_Square.get_hit_time());
//
//        System.out.println(hit_Square.get_hit_point().get_X() + " " + hit_Square.get_hit_point().get_Y() + " " + hit_Square.get_hit_point().get_Z());

        HitObject hit_Cylinder = genericCylinder.hit_reg(ray);
        System.out.println(hit_Cylinder.get_hit_time());

        System.out.println(hit_Cylinder.get_hit_point().get_X() + " " + hit_Cylinder.get_hit_point().get_Y() + " " + hit_Cylinder.get_hit_point().get_Z());

    }
}
