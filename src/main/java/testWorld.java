import config.Config;
import factory.ObjectFactory;
import factory.Matrix3DFactory;
import internal.*;
import internal.Point;
import render.Renderer;
import shapes.BooleanObject;
import shapes.Cube;
import shapes.Cylinder;
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
        Point eye = objectFactory.create_point(40, 140, 5);
        Vector n = objectFactory.create_vector(-0.5, 1, 0);
        Vector u = objectFactory.create_vector(0, 0, 1);
        //Vector v = internalTransformer.cross_product(n, u);
        Vector v = objectFactory.create_vector(1, 1, 0);
        Camera camera = objectFactory.create_camera(eye, u, v, n, N);
        //Ray ray = objectFactory.create_ray(camera, Config.DEFAULT_AIR_SPEED, 25, 25);
        World world = objectFactory.create_world(camera);
        Point lightPoint = objectFactory.create_point(100, 150, 100);
        //Licht niet zo sterk zetten, mag veel lager
        //Maximaal 0.99
        Light light = objectFactory.create_light(lightPoint, 0.99);
        world.add_light(light);

        //Create textures
        Texture wood_texture = objectFactory.create_wood_texture(0.3, 0.5, 0.2, 90, 2, 5);
        Texture noise = objectFactory.create_noise(50);

        //objects Transformation matrices
        //standard scaling factor
        double standard_sx = 30; //x
        double standard_sy = 30; //y
        double standard_sz = 30; //z
        Matrix object_standard_matrix = matrix3DFactory.create_scal_matrix(standard_sx, standard_sy, standard_sz);
        Matrix object_standard_inv_matrix = matrix3DFactory.create_inv_scal_matrix(standard_sx, standard_sy, standard_sz);

        //standard translation factor
        double standard_m14 = 5; //x
        double standard_m24 = 0; //y
        double standard_m34 = 0; //z
        Matrix standard_translation_matrix = matrix3DFactory.create_trans_matrix(standard_m14, standard_m24, standard_m34);
        Matrix standard_translation_inv_matrix = matrix3DFactory.create_inv_trans_matrix(standard_m14, standard_m24, standard_m34);

        object_standard_matrix = multiply_matrices(matrixTransformer, standard_translation_matrix, object_standard_matrix);
        object_standard_inv_matrix = multiply_matrices(matrixTransformer, standard_translation_inv_matrix, object_standard_inv_matrix);

        Matrix stam_transformation_matrix = object_standard_matrix;
        Matrix stam_transformation_inv_matrix = object_standard_inv_matrix;

        double alpha = Math.PI/2;
        Matrix stam_roll_transformation_matrix = matrix3DFactory.create_y_roll_matrix(alpha);
        Matrix stam_roll_inv_transformation_matrix = matrix3DFactory.create_inv_y_roll_matrix(alpha);

        stam_transformation_matrix = multiply_matrices(matrixTransformer, stam_roll_transformation_matrix, stam_transformation_matrix);
        stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, stam_roll_inv_transformation_matrix, stam_transformation_inv_matrix);

        //Scaling Tree Stam
        double sx = 0.2; //x
        double sy = 0.2; //y
        double sz = 1.5; //z
        Matrix tree_stam_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix tree_stam_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);

        stam_transformation_matrix = multiply_matrices(matrixTransformer, tree_stam_scaling_transformation_matrix, stam_transformation_matrix);
        stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_stam_scaling_inv_transformation_matrix, stam_transformation_inv_matrix);

        //Translation Cylinder tree head lower
        double m14 = 0.2; //x
        double m24 = 0; //y
        double m34 = 0; //z
        Matrix translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Matrix tree_head_lower_transformation_matrix = multiply_matrices(matrixTransformer, translation_transformation_matrix, object_standard_matrix);
        Matrix tree_head_lower_transformation_inv_matrix = multiply_matrices(matrixTransformer, translation_inv_transformation_matrix, object_standard_inv_matrix);

        alpha = Math.PI * 1.5;
        Matrix cylinder_roll_transformation_matrix = matrix3DFactory.create_y_roll_matrix(alpha);
        Matrix cylinder_roll_inv_transformation_matrix = matrix3DFactory.create_inv_y_roll_matrix(alpha);

        tree_head_lower_transformation_matrix = multiply_matrices(matrixTransformer, cylinder_roll_transformation_matrix, tree_head_lower_transformation_matrix);
        tree_head_lower_transformation_inv_matrix = multiply_matrices(matrixTransformer, cylinder_roll_inv_transformation_matrix, tree_head_lower_transformation_inv_matrix);

        //Scaling tree head lower
        sx = 0.5; //x
        sy = 0.5; //y
        sz = 0.8; //z
        Matrix tree_head_lower_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix tree_head_lower_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);

        //Scaling tree head upper
        sx = 0.4; //x
        sy = 0.4; //y
        sz = 0.8; //z
        Matrix tree_head_upper_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix tree_head_upper_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);

        tree_head_lower_transformation_matrix = multiply_matrices(matrixTransformer, tree_head_lower_scaling_transformation_matrix, tree_head_lower_transformation_matrix);
        tree_head_lower_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_head_lower_scaling_inv_transformation_matrix, tree_head_lower_transformation_inv_matrix);

        //Translation Cylinder tree head upper
        m14 = -0.2; //x
        m24 = 0; //y
        m34 = 0; //z
        translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Matrix tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, translation_transformation_matrix, object_standard_matrix);
        Matrix tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, translation_inv_transformation_matrix, object_standard_inv_matrix);

        tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, cylinder_roll_transformation_matrix, tree_head_upper_transformation_matrix);
        tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, cylinder_roll_inv_transformation_matrix, tree_head_upper_transformation_inv_matrix);

        tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, tree_head_upper_scaling_transformation_matrix, tree_head_upper_transformation_matrix);
        tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_head_upper_scaling_inv_transformation_matrix, tree_head_upper_transformation_inv_matrix);

        //Cube
        //Rotation Matrix (x-roll) om vlak naar oog te draaien (en niet hoek)

        //World Cube Scaling (wordt de grote omvangende kubus waarin de wereld zit)
        double cube_sx = 300; //x
        double cube_sy = 300; //y
        double cube_sz = 300; //z
        Matrix cube_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(cube_sx, cube_sy, cube_sz);
        Matrix cube_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(cube_sx, cube_sy, cube_sz);

        Color objectColor = Color.GRAY;
        Color objectColor_2 = Color.RED;
        Color grass_color = new Color(58, 89, 39);

        //REFRACTION

//        Sphere sphere = objectFactory.create_sphere(object_scaling_transformation_matrix, object_scaling_inv_transformation_matrix, 0, 0, 1, Config.DEFAULT_AIR_SPEED, objectColor_2);
//        world.add_object(sphere);
//


        //REFLECTION

//        Sphere sphere = objectFactory.create_sphere(reflection_translation_transformation_matrix, reflection_translation_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, objectColor_2, wood_texture);
//        world.add_object(sphere);

        Cylinder cylinder = objectFactory.create_cylinder(tree_head_lower_transformation_matrix,tree_head_lower_transformation_inv_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, grass_color, 0);
        world.add_object(cylinder);

        Cylinder cylinder2 = objectFactory.create_cylinder(tree_head_upper_transformation_matrix,tree_head_upper_transformation_inv_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, grass_color, 0);
        world.add_object(cylinder2);

        Cylinder cylinder_stam = objectFactory.create_cylinder(stam_transformation_matrix, stam_transformation_inv_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, Config.WOODCOLOR, noise,1);
        world.add_object(cylinder_stam);

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

        //Boolean objects
//        BooleanObject booleanObject1 = new BooleanObject(cube, sphere,BooleanObjectType.DIFFERENCE);
//        world.add_boolean_object(booleanObject1);

        Cube world_cube = objectFactory.create_cube(cube_scaling_transformation_matrix, cube_scaling_inv_transformation_matrix, 1, 0, 0, Config.DEFAULT_AIR_SPEED, Config.SKYCOLOR);
        world.add_object(world_cube);

        Renderer renderer = objectFactory.create_renderer();
        renderer.render_screen(world);
        System.out.println("rendered");
    }

    static Matrix multiply_matrices(MatrixTransformer matrixTransformer, Matrix matrix_1, Matrix matrix_2){
        double[][] transformed_scaling_matrix_array = matrixTransformer.multiplyMatrices(matrix_1.get_matrix(),matrix_2.get_matrix());
        return new Matrix(transformed_scaling_matrix_array);
    }
}
