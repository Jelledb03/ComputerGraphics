public class Object {
    private Matrix transformation_matrix;
    private Matrix inverse_transformation_matrix;

    public Object(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        this.transformation_matrix = transformation_matrix;
        this.inverse_transformation_matrix = inverse_transformation_matrix;
    }

    public Matrix get_transformation_matrix() {
        return transformation_matrix;
    }

    public void set_transformation_matrix(Matrix transformation_matrix) {
        this.transformation_matrix = transformation_matrix;
    }

    public Matrix get_inverse_transformation_matrix() {
        return inverse_transformation_matrix;
    }

    public void set_inverse_transformation_matrix(Matrix inverse_transformation_matrix) {
        this.inverse_transformation_matrix = inverse_transformation_matrix;
    }
}
