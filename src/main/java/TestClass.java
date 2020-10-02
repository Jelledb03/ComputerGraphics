import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

public class TestClass {
    public static void main(String[] args) {
        int[][] matrixA = {
                new int[]{1, 15, 20},
                new int[]{-12, 7, -9},
                new int[]{3, 5, -8}
        };

        int[][] matrixB = {
                new int[]{12, -7, 13},
                new int[]{2, 11, 10},
                new int[]{-6, 17, 3}
        };
        TransformationFactory transformationFactory = new TransformationFactory();
        int[][] transformedMatrix = transformationFactory.createTransformationMatrix(matrixA, matrixB);
        System.out.println("Matrix A = " + Arrays.deepToString(matrixA));
        System.out.println("Matrix B = " + Arrays.deepToString(matrixB));
        System.out.println("A x B = " + Arrays.deepToString(transformedMatrix));
    }
}