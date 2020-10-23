package matrix_related;

import internal.Matrix;

public class Matrix2DFactory {
    public Matrix2DFactory() {
    }
    public Matrix create_transl_matrix(double m13, double m23){
        double[][] transf_matrix = {
                new double[]{1, 0, m13},
                new double[]{0, 1, m23},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_transl_matrix(double m13, double m23){
        double[][] transf_matrix = {
                new double[]{1, 0, -m13},
                new double[]{0, 1, -m23},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_rot_matrix(double phi){
        double[][] transf_matrix = {
                new double[]{Math.cos(phi), Math.sin(phi), 0},
                new double[]{-Math.sin(phi), Math.cos(phi), 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_rot_matrix(double phi){
        double[][] transf_matrix = {
                new double[]{Math.cos(phi), -Math.sin(phi), 0},
                new double[]{Math.sin(phi), Math.cos(phi), 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_scal_matrix(double sx, double sy){
        double[][] transf_matrix = {
                new double[]{sx, 0, 0},
                new double[]{0, sy, 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_scal_matrix(double sx, double sy){
        double[][] transf_matrix = {
                new double[]{1/sx, 0, 0},
                new double[]{0, 1/sy, 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_x_shear_matrix(double h){
        double[][] transf_matrix = {
                new double[]{1, h, 0},
                new double[]{0, 1, 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_x_shear_matrix(double h){
        double[][] transf_matrix = {
                new double[]{1, 0, 0},
                new double[]{-h, 1, 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_y_shear_matrix(double g){
        double[][] transf_matrix = {
                new double[]{1, 0, 0},
                new double[]{g, 1, 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_y_shear_matrix(double g){
        double[][] transf_matrix = {
                new double[]{1, -g, 0},
                new double[]{0, 1, 0},
                new double[]{0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
}
