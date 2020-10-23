package matrix_related;

public class MatrixTransformer {
    public double[][] createTransformationMatrix(double[][] MatrixA, double[][] MatrixB) {
        return multiplyMatrices(MatrixA, MatrixB);
    }

    //Calculates the entire return matrix by multiplying both matrices
    public double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        double[][] result = new double[firstMatrix.length][secondMatrix[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(firstMatrix, secondMatrix, row, col);
            }
        }
        return result;
    }

    //Calculates one cell of the result matrix by multiplying the row of the firstMatrix with the column of the secondMatrix
    double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double matrix_cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            double first_cell = firstMatrix[row][i];
            double second_cell = secondMatrix[i][col];
            matrix_cell += first_cell * second_cell;
        }
        return matrix_cell;
    }
}
