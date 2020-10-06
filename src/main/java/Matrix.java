public class Matrix {
    private int row_size;
    private int column_size;
    private double[][] matrix;

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
        row_size = matrix.length;
        column_size = matrix[0].length;
    }

    public int get_row_size() {
        return row_size;
    }

    public void set_row_size(int row_size) {
        this.row_size = row_size;
    }

    public int get_column_size() {
        return column_size;
    }

    public void set_rolumn_size(int column_size) {
        this.column_size = column_size;
    }

    public double[][] get_matrix() {
        return matrix;
    }

    public void set_matrix(double[][] matrix) {
        this.matrix = matrix;
    }
}
