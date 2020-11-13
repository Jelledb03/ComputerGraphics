import factory.Matrix3DFactory;
import factory.ObjectFactory;
import internal.Matrix;
import internal.Point;
import factory.Matrix2DFactory;
import internal.MatrixTransformer;
import internal.Vector;
import world.Camera;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        //Scaling
        double sx = 2; //x
        double sy = 2; //y
        double sz = 2; //z
        Matrix3DFactory matrix3DFactory = new Matrix3DFactory();
        MatrixTransformer matrixTransformer = new MatrixTransformer();
        ObjectFactory objectFactory = new ObjectFactory();
        Matrix object_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix object_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);
        //Translation
        double m14_cube = -2; //x
        double m24_cube = 0; //y
        double m34_cube = -1; //z
        Matrix cube_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14_cube, m24_cube, m34_cube);
        Matrix cube_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14_cube, m24_cube, m34_cube);

        double[][] matrix = matrixTransformer.multiplyMatrices(cube_translation_transformation_matrix.get_matrix(),object_scaling_transformation_matrix.get_matrix());
        System.out.println(Arrays.deepToString(matrix));

        Point point = new Point(1, 1, 1);
        double[][] transformed_point = matrixTransformer.multiplyMatrices(matrix, point.get_point());
        System.out.println(Arrays.deepToString(transformed_point));


        //afstand van camera tot de viewpoint



        //afstand van camera tot de viewpoint
        double N = 1000;
        Point eye = objectFactory.create_point(10, 10, 0);
        Point look = objectFactory.create_point(0,0,0);
        Vector up = objectFactory.create_vector(0, 1, 0);
        Camera camera = objectFactory.create_camera(eye, look, up, N);
        int ze = 2;
    }
}