package internal;

import internal.Matrix;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Matrix3DFactory {
    public Matrix3DFactory() {
    }
    public Matrix create_trans_matrix(double m14, double m24, double m34){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, m14},
                new double[]{0, 1, 0, m24},
                new double[]{0, 0, 1, m34},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_trans_matrix(double m14, double m24, double m34){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, -m14},
                new double[]{0, 1, 0, -m24},
                new double[]{0, 0, 1, -m34},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_x_roll_matrix(double beta){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, cos(beta), -sin(beta), 0},
                new double[]{0, sin(beta), cos(beta), 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_x_roll_matrix(double beta){
        //double m22 = (cos(2*beta) + 1) /(2*cos(beta));
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, cos(beta), sin(beta), 0},
                new double[]{0, -sin(beta), cos(beta), 0},
                new double[]{0, 0, 0, 1}
        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_y_roll_matrix(double beta){
        double[][] transf_matrix = {
                new double[]{cos(beta), 0, sin(beta), 0},
                new double[]{0, 1, 0, 0},
                new double[]{-sin(beta), 0, cos(beta), 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_y_roll_matrix(double beta){
        //double m22 = (cos(2*beta) + 1) /(2*cos(beta));
        double[][] transf_matrix = {
                new double[]{cos(beta), 0, -sin(beta), 0},
                new double[]{0, 1, 0, 0},
                new double[]{sin(beta), 0, cos(beta), 0},
                new double[]{0, 0, 0, 1}
        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_z_roll_matrix(double beta){
        double[][] transf_matrix = {
                new double[]{cos(beta), -sin(beta), 0, 0},
                new double[]{sin(beta), cos(beta), 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_z_roll_matrix(double beta){
        double[][] transf_matrix = {
                new double[]{cos(beta), sin(beta), 0, 0},
                new double[]{-sin(beta), cos(beta), 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_scal_matrix(double sx, double sy, double sz){
        double[][] transf_matrix = {
                new double[]{sx, 0, 0, 0},
                new double[]{0, sy, 0, 0},
                new double[]{0, 0, sz, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_scal_matrix(double sx, double sy, double sz){
        double[][] transf_matrix = {
                new double[]{1/sx, 0, 0, 0},
                new double[]{0, 1/sy, 0, 0},
                new double[]{0, 0, 1/sz, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }

    //How to implement shear functions?
    //with px is being offset by margin of y
    //Q = (f*Py + Px, Py, Pz)
    public Matrix create_y_off_x_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, f, 0, 0},
                new double[]{0, 1, 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_y_off_x_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, -f, 0, 0},
                new double[]{0, 1, 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    //with px is being offset by margin of z
    //Q = (f*Pz + Px, Py, Pz)
    public Matrix create_z_off_x_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, f, 0},
                new double[]{0, 1, 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_z_off_x_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, -f, 0},
                new double[]{0, 1, 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    //with py is being offset by margin of x
    //Q = (Px, f*Px + Py, Pz)
    public Matrix create_x_off_y_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{f, 1, 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_x_off_y_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{-f, 1, 0, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    //with py is being offset by margin of z
    //Q = (Px, f*Pz + Py, Pz)
    public Matrix create_z_off_y_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, 1, f, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_z_off_y_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, 1, -f, 0},
                new double[]{0, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    //with px is being offset by margin of y
    //Q = (Px, Py, f*Px + Pz)
    public Matrix create_x_off_z_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, 1, 0, 0},
                new double[]{f, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_x_off_z_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, 1, 0, 0},
                new double[]{-f, 0, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    //with px is being offset by margin of y
    //Q = (Px, Py, f*Py + Pz)
    public Matrix create_y_off_z_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, 1, 0, 0},
                new double[]{0, f, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
    public Matrix create_inv_y_off_z_shear_matrix(double f){
        double[][] transf_matrix = {
                new double[]{1, 0, 0, 0},
                new double[]{0, 1, 0, 0},
                new double[]{0, -f, 1, 0},
                new double[]{0, 0, 0, 1}

        };
        return new Matrix(transf_matrix);
    }
}
