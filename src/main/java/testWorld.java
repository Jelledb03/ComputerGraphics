import config.Config;
import internal.*;
import internal.Point;
import rendering2D.Renderer;
import shapes.Cube;
import shapes.Cylinder;
import shapes.Sphere;
import world.Camera;
import world.Light;
import world.World;

import java.awt.*;

public class testWorld {
    public static void main(String[] args) {
        InternalFactory internalFactory = new InternalFactory();
        Matrix3DFactory matrix3DFactory = new Matrix3DFactory();
        MatrixTransformer matrixTransformer = new MatrixTransformer();
        Camera camera = new Camera();
        //afstand van camera tot de viewpoint
        camera.set_N(1000);
        Point eye = new Point(10, 10, 10);
        camera.set_eye(eye);
        Vector n = new Vector(1, 1, 1);
        Vector u = new Vector(-1, 1, 0);
        Vector v = new Vector(1, 1, -1);
        camera.set_n(n);
        camera.set_u(u);
        camera.set_v(v);
        Ray ray = internalFactory.createRay(camera,Config.DEFAULT_AIR_SPEED, 25, 25);
        World world = new World(camera);
        Point lightPoint = new Point(10, 10, 10);
        //Licht niet zo sterk zetten, mag veel lager
        //Maximaal 0.99
        Light light = new Light(lightPoint, 0.99);
        world.add_light(light);

        //objects Transformation matrices
        //Scaling
        double sx = 2; //x
        double sy = 2; //y
        double sz = 2; //z
        Matrix object_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix object_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);
        //Translation
        double m14_cube = -2; //x
        double m24_cube = 0; //y
        double m34_cube = -1; //z
        Matrix cube_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14_cube, m24_cube, m34_cube);
        Matrix cube_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14_cube, m24_cube, m34_cube);

        //Translation
        double m14 = -1.5; //x
        double m24 = -1.5; //y
        double m34 = -2; //z
        Matrix object_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix object_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        //Rotation Matrix

        //Cube Scaling (wordt de grote omvangende kubus waarin de wereld zit)
        double cube_sx = 10; //x
        double cube_sy = 10; //y
        double cube_sz = 10; //z
        Matrix cube_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(cube_sx, cube_sy, cube_sz);
        Matrix cube_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(cube_sx, cube_sy, cube_sz);

        //Rotation Matrix (z-roll) om vlak naar oog te draaien (en niet hoek)
        double beta = Math.PI/4;
        Matrix object_z_roll_transformation_matrix = matrix3DFactory.create_z_roll_matrix(beta);
        Matrix object_z_roll_inv_transformation_matrix = matrix3DFactory.create_inv_z_roll_matrix(beta);

        //Rotation Matrix (x-roll) om vlak naar oog te draaien (en niet hoek)
        double alpha = Math.PI/2;
        Matrix object_x_roll_transformation_matrix = matrix3DFactory.create_x_roll_matrix(alpha);
        Matrix object_x_roll_inv_transformation_matrix = matrix3DFactory.create_inv_x_roll_matrix(alpha);

        //Rotation Matrix (y-roll) om vlak naar oog te draaien (en niet hoek)
        Matrix object_y_roll_transformation_matrix = matrix3DFactory.create_y_roll_matrix(alpha);
        Matrix object_y_roll_inv_transformation_matrix = matrix3DFactory.create_inv_y_roll_matrix(alpha);

        double[][] full_cube_matrix_array = matrixTransformer.multiplyMatrices(cube_translation_transformation_matrix.get_matrix(), object_z_roll_transformation_matrix.get_matrix());
        double[][] full_cube_inv_matrix_array = matrixTransformer.multiplyMatrices(cube_translation_inv_transformation_matrix.get_matrix(), object_z_roll_inv_transformation_matrix.get_matrix());

        Matrix full_cube_matrix = new Matrix(full_cube_matrix_array);
        Matrix full_cube_inv_matrix = new Matrix(full_cube_inv_matrix_array);

        double[][] object_matrix = matrixTransformer.multiplyMatrices(cube_translation_transformation_matrix.get_matrix(), object_scaling_transformation_matrix.get_matrix());
        double[][] object_inv_matrix = matrixTransformer.multiplyMatrices(cube_translation_inv_transformation_matrix.get_matrix(), object_scaling_inv_transformation_matrix.get_matrix());

        Matrix sphere2_matrix = new Matrix(object_matrix);
        Matrix sphere2_inv_matrix = new Matrix(object_inv_matrix);

        Color objectColor = Color.GREEN;
        Color objectColor_2 = Color.PINK;

        Sphere sphere = new Sphere(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, 0, 0, 1, Config.DEFAULT_GLASS_SPEED, objectColor_2);
        world.add_object(sphere);

        Sphere sphere_2 = new Sphere(object_translation_transformation_matrix, object_translation_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_GLASS_SPEED, objectColor);
        world.add_object(sphere_2);

        //Cube cube = new Cube(object_translation_transformation_matrix, object_translation_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_GLASS_SPEED, objectColor);
        //world.add_object(cube);

        //Cube cube = new Cube(full_cube_matrix, full_cube_inv_matrix, 1, 0, 0, Config.DEFAULT_GLASS_SPEED, objectColor);
        //world.add_object(cube);

        //Sphere sphere_2 = new Sphere(sphere2_matrix, sphere2_inv_matrix, objectColor_2);
        //world.add_object(sphere_2);

        //Cylinder cylinder = new Cylinder(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, objectColor, 0.5);
        //world.add_object(cylinder);

        //Cube cube = new Cube(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, objectColor);
        //world.add_object(cube);

        Cube world_cube = new Cube(cube_scaling_transformation_matrix, cube_scaling_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, Config.DEFAULT_BACKGROUND_COLOR);
        world.add_object(world_cube);

        Renderer renderer = new Renderer();
        renderer.render_screen(world);
        System.out.println("rendered");
    }
}
