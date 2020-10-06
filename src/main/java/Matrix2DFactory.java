public class Matrix2DFactory {
    public Matrix2DFactory() {
    }
    public Matrix create_trans_matrix(double m13, double m23){
        double[][] trans_matrix = {
                new double[]{0, 0, m13},
                new double[]{0, 0, m23},
                new double[]{0, 0, 1}

        };
        return new Matrix(trans_matrix);
    }
}
