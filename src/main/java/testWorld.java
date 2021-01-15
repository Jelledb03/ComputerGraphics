import config.Config;
import factory.ObjectFactory;
import factory.Matrix3DFactory;
import internal.*;
import internal.Point;
import render.Renderer;
import shapes.Cube;
import shapes.Sphere;
import texture.Texture;
import world.Camera;
import world.Light;
import world.World;

import java.awt.*;

public class testWorld {
    public static void main(String[] args) {
        ObjectFactory objectFactory = new ObjectFactory();
        Matrix3DFactory matrix3DFactory = new Matrix3DFactory();
        MatrixTransformer matrixTransformer = new MatrixTransformer();
        InternalTransformer internalTransformer = new InternalTransformer();

        double N = 1000;
        //We kijken op het moment vanboven (positief y) het object.
        //Aanpassen van z brengt het object van links naar rechts wat klopt
        Point eye = objectFactory.create_point(-10, 20, 2);
        Vector n = objectFactory.create_vector(-0.5, 1, 0);
        Vector u = objectFactory.create_vector(0, 0, 1);
        //Vector v = internalTransformer.cross_product(n, u);
        Vector v = objectFactory.create_vector(1, 1, 0);
        Camera camera = objectFactory.create_camera(eye, u, v, n, N);
        //Ray ray = objectFactory.create_ray(camera, Config.DEFAULT_AIR_SPEED, 25, 25);
        World world = objectFactory.create_world(camera);
        Point lightPoint = objectFactory.create_point(10, 15, 10);
        //Licht niet zo sterk zetten, mag veel lager
        //Maximaal 0.99
        Light light = objectFactory.create_light(lightPoint, 0.99);
        world.add_light(light);

        //Create textures
        Texture wood_texture = objectFactory.create_wood_texture(0.3, 0.5, 0.2, 90, 2, 5);
        Texture noise = objectFactory.create_noise(50);

        //objects Transformation matrices
        //object test matrix
        double tx = 1; //x
        double ty = 1; //y
        double tz = 3; //z
        Matrix object_transformation_matrix = matrix3DFactory.create_scal_matrix(tx, ty, tz);
        Matrix object_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(tx, ty, tz);
        //Scaling
        double sx = 3; //x
        double sy = 3; //y
        double sz = 5; //z
        Matrix object_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix object_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);
        //Translation cube
        double m14_cube = 0; //x
        double m24_cube = 0; //y
        double m34_cube = -4; //z
        Matrix cube_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14_cube, m24_cube, m34_cube);
        Matrix cube_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14_cube, m24_cube, m34_cube);

        //Translation Refraction
        double m14 = -1.5; //x
        double m24 = -1.5; //y
        double m34 = -2; //z
        Matrix refraction_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix refraction_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        //Translation Reflection
        double m14_refl = 3; //x
        double m24_refl = -4; //y
        double m34_refl = 0; //z
        Matrix reflection_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14_refl, m24_refl, m34_refl);
        Matrix reflection_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14_refl, m24_refl, m34_refl);

        //Rotation Matrix

        //Cube Scaling (wordt de grote omvangende kubus waarin de wereld zit)
        double cube_sx = 30; //x
        double cube_sy = 30; //y
        double cube_sz = 30; //z
        Matrix cube_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(cube_sx, cube_sy, cube_sz);
        Matrix cube_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(cube_sx, cube_sy, cube_sz);

        //Rotation Matrix (z-roll) om vlak naar oog te draaien (en niet hoek)
        double beta = Math.PI / 4;
        Matrix object_z_roll_transformation_matrix = matrix3DFactory.create_z_roll_matrix(beta);
        Matrix object_z_roll_inv_transformation_matrix = matrix3DFactory.create_inv_z_roll_matrix(beta);

        //Rotation Matrix (x-roll) om vlak naar oog te draaien (en niet hoek)
        double alpha = Math.PI / 2;
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

        Color objectColor = Color.GRAY;
        Color objectColor_2 = Color.RED;
        Color woodColor = new Color(193,154,107);

        //REFRACTION

//        Sphere sphere = objectFactory.create_sphere(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, 0, 0, 1, Config.DEFAULT_AIR_SPEED, objectColor_2);
//        world.add_object(sphere);
//
//        Sphere sphere_2 = objectFactory.create_sphere(refraction_translation_transformation_matrix, refraction_translation_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_GLASS_SPEED, objectColor);
//        world.add_object(sphere_2);

        //REFLECTION

//        Sphere sphere = objectFactory.create_sphere(reflection_translation_transformation_matrix, reflection_translation_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, objectColor_2, wood_texture);
//        world.add_object(sphere);

//        Sphere sphere = objectFactory.create_sphere(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, objectColor_2, noise);
//        world.add_object(sphere);

        Cube cube = objectFactory.create_cube(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, woodColor, noise);
        world.add_object(cube);

        //Cylinder cylinder = objectFactory.create_cylinder(object_transformation_matrix, object_inv_transformation_matrix,1, 0, 0, Config.DEFAULT_GLASS_SPEED, objectColor, 1);
        //world.add_object(cylinder);

        //Sphere sphere_2 = objectFactory.create_sphere(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, 0, 1, 0, Config.DEFAULT_GLASS_SPEED, objectColor);
        //world.add_object(sphere_2);

        //REFRACTION CUBE

//        Sphere sphere = objectFactory.create_sphere(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, 0, 0, 1, Config.DEFAULT_AIR_SPEED, objectColor_2);
//        world.add_object(sphere);
//
        //Cube cube = objectFactory.create_cube(cube_translation_transformation_matrix, cube_translation_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_GLASS_SPEED, objectColor);
        //world.add_object(cube);

        //Cube cube = objectFactory.create_cube(full_cube_matrix, full_cube_inv_matrix, 1, 0, 0, Config.DEFAULT_GLASS_SPEED, objectColor);
        //world.add_object(cube);



        Cube world_cube = objectFactory.create_cube(cube_scaling_transformation_matrix, cube_scaling_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, Config.DEFAULT_BACKGROUND_COLOR);
        world.add_object(world_cube);

        Renderer renderer = objectFactory.create_renderer();
        renderer.render_screen(world);
        System.out.println("rendered");
    }
}
