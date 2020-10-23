import internal.Matrix;
import internal.Point;
import matrix_related.Matrix2DFactory;
import matrix_related.MatrixTransformer;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        double pi = Math.PI;
        Matrix2DFactory matrixFactory = new Matrix2DFactory();
        MatrixTransformer matrixTransformer = new MatrixTransformer();

        int[][] matrixA = {
                new int[]{1, 15, 20, 50},
                new int[]{-12, 7, -9, 20},
                new int[]{3, 5, -8, 30}
        };

        double[][] matrixTest = {
                new double[]{1, 15, 20, 50},
                new double[]{-12, 7, -9, 20},
                new double[]{3, 5, -8, 30}
        };

        int[][] matrixB = {
                new int[]{12, -7, 13},
                new int[]{2, 11, 10},
                new int[]{-6, 17, 3}
        };

        Matrix matrix = new Matrix(matrixTest);

        System.out.println("Row size: " + matrix.get_row_size());
        System.out.println("Column size: " + matrix.get_column_size());

        //matrix factory tests

        //double m13 = 2;
        //double m23 = 5;
        //internal.Matrix transformed_matrix = matrixFactory.create_transl_matrix(m13, m23);

        //System.out.println(Arrays.deepToString(transformed_matrix.get_matrix()));

        double phi = pi/6;
        Matrix transformed_matrix = matrixFactory.create_rot_matrix(phi);
        System.out.println(Arrays.deepToString(transformed_matrix.get_matrix()));
        System.out.println(transformed_matrix.get_column_size());
        System.out.println(transformed_matrix.get_row_size());

        double sx = 1;
        double sy = 2;
        transformed_matrix = matrixFactory.create_scal_matrix(sx,sy);
        System.out.println(Arrays.deepToString(transformed_matrix.get_matrix()));

        transformed_matrix = matrixFactory.create_inv_scal_matrix(sx,sy);
        System.out.println(Arrays.deepToString(transformed_matrix.get_matrix()));

        //internal.Matrix transformer test with internal.Point and MatrixFactory
        //PointA coordinates
        double x = 50;
        double y = 50;

        //Transformation Parameters
        double m13 = 50;
        double m23 = 0;

        Point pointA = new Point(x,y);
        Matrix translationMatrix = matrixFactory.create_transl_matrix(m13, m23);
        System.out.println(Arrays.deepToString(pointA.get_point()));
        System.out.println(Arrays.deepToString(translationMatrix.get_matrix()));

        double[][] transformedMatrix = matrixTransformer.multiplyMatrices(translationMatrix.get_matrix(), pointA.get_point());

        System.out.println(Arrays.deepToString(transformedMatrix));

        GUI gui = new GUI(500,500);
        gui.setVisible(true);
    }
}