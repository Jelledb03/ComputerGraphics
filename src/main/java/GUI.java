import config.Config;
import exterior.material.Material;
import factory.ObjectFactory;
import factory.Matrix3DFactory;
import internal.*;
import internal.Point;
import render.Renderer;
import shapes.*;
import exterior.texture.Texture;
import world.Camera;
import world.Light;
import world.World;

public class GUI {
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
        Point lightPoint = objectFactory.create_point(-50, 50, 50);
        //Licht niet zo sterk zetten, mag veel lager
        //Maximaal 0.99
        Light light = objectFactory.create_light(lightPoint, 0.99);
        world.add_light(light);

        //Create textures
        Texture wood_texture = objectFactory.create_wood_texture(0.3, 0.5, 0.2, 90, 2, 5);
        Texture noise = objectFactory.create_noise(50);
        Texture floor_texture = objectFactory.create_floor_texture(2.5);

        //Create materials
        Material default_material = new Material();

        //objects Transformation matrices
        //standard scaling factor
        double standard_sx = 30; //x
        double standard_sy = 30; //y
        double standard_sz = 30; //z
        Matrix object_standard_matrix = matrix3DFactory.create_scal_matrix(standard_sx, standard_sy, standard_sz);
        Matrix object_standard_inv_matrix = matrix3DFactory.create_inv_scal_matrix(standard_sx, standard_sy, standard_sz);

        //standard translation factor
        double standard_m14 = 5; //x
        double standard_m24 = -1; //y
        double standard_m34 = 0; //z
        Matrix standard_translation_matrix = matrix3DFactory.create_trans_matrix(standard_m14, standard_m24, standard_m34);
        Matrix standard_translation_inv_matrix = matrix3DFactory.create_inv_trans_matrix(standard_m14, standard_m24, standard_m34);

        object_standard_matrix = multiply_matrices(matrixTransformer, standard_translation_matrix, object_standard_matrix);
        object_standard_inv_matrix = multiply_matrices(matrixTransformer, standard_translation_inv_matrix, object_standard_inv_matrix);

        //HOUSE BEGIN
        //House
        Matrix house_transformation_matrix = object_standard_matrix;
        Matrix house_transformation_inv_matrix = object_standard_inv_matrix;

        //Door
        Matrix door_transformation_matrix = object_standard_matrix;
        Matrix door_transformation_inv_matrix = object_standard_matrix;

        double m14 = 1.49; //x
        double m24 = 0; //y
        double m34 = -0.6; //z
        Matrix door_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix door_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        door_transformation_matrix = multiply_matrices(matrixTransformer, door_translation_transformation_matrix, object_standard_matrix);
        door_transformation_inv_matrix = multiply_matrices(matrixTransformer, door_translation_inv_transformation_matrix, object_standard_inv_matrix);

        double sx = 0.5; //x
        double sy = 0.001; //y
        double sz = 0.2; //z
        Matrix door_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix door_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);

        door_transformation_matrix = multiply_matrices(matrixTransformer, door_scaling_transformation_matrix, door_transformation_matrix);
        door_transformation_inv_matrix = multiply_matrices(matrixTransformer, door_scaling_inv_transformation_matrix, door_transformation_inv_matrix);

        //HOUSE END

        //FIRST TREE BEGIN
        m14 = 0; //x
        m24 = 0; //y
        m34 = -3; //z
        Matrix tree_stam_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix tree_stam_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Matrix stam_transformation_matrix = multiply_matrices(matrixTransformer, tree_stam_translation_transformation_matrix, object_standard_matrix);
        Matrix stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_stam_translation_inv_transformation_matrix, object_standard_inv_matrix);

        double alpha = Math.PI / 2;
        Matrix stam_roll_transformation_matrix = matrix3DFactory.create_y_roll_matrix(alpha);
        Matrix stam_roll_inv_transformation_matrix = matrix3DFactory.create_inv_y_roll_matrix(alpha);

        stam_transformation_matrix = multiply_matrices(matrixTransformer, stam_roll_transformation_matrix, stam_transformation_matrix);
        stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, stam_roll_inv_transformation_matrix, stam_transformation_inv_matrix);

        //Scaling Tree Stam
        sx = 0.2; //x
        sy = 0.2; //y
        sz = 1.5; //z
        Matrix tree_stam_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix tree_stam_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);

        stam_transformation_matrix = multiply_matrices(matrixTransformer, tree_stam_scaling_transformation_matrix, stam_transformation_matrix);
        stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_stam_scaling_inv_transformation_matrix, stam_transformation_inv_matrix);

        //Translation Cylinder tree head lower
        m14 = 0.2; //x
        m24 = 0; //y
        m34 = -3; //z
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
        m34 = -3; //z
        translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Matrix tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, translation_transformation_matrix, object_standard_matrix);
        Matrix tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, translation_inv_transformation_matrix, object_standard_inv_matrix);

        tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, cylinder_roll_transformation_matrix, tree_head_upper_transformation_matrix);
        tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, cylinder_roll_inv_transformation_matrix, tree_head_upper_transformation_inv_matrix);

        tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, tree_head_upper_scaling_transformation_matrix, tree_head_upper_transformation_matrix);
        tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_head_upper_scaling_inv_transformation_matrix, tree_head_upper_transformation_inv_matrix);

        //FIRST TREE ENDING

        //SECOND TREE BEGIN
        m14 = 0; //x
        m24 = 0; //y
        m34 = 3; //z
        Matrix second_tree_stam_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix second_tree_stam_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Matrix second_stam_transformation_matrix = multiply_matrices(matrixTransformer, second_tree_stam_translation_transformation_matrix, object_standard_matrix);
        Matrix second_stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, second_tree_stam_translation_inv_transformation_matrix, object_standard_inv_matrix);

        second_stam_transformation_matrix = multiply_matrices(matrixTransformer, stam_roll_transformation_matrix, second_stam_transformation_matrix);
        second_stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, stam_roll_inv_transformation_matrix, second_stam_transformation_inv_matrix);

        second_stam_transformation_matrix = multiply_matrices(matrixTransformer, tree_stam_scaling_transformation_matrix, second_stam_transformation_matrix);
        second_stam_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_stam_scaling_inv_transformation_matrix, second_stam_transformation_inv_matrix);

        //Translation Cylinder tree head lower
        m14 = 0.2; //x
        m24 = 0; //y
        m34 = 3; //z
        Matrix moon_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        Matrix moon_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Matrix second_tree_head_lower_transformation_matrix = multiply_matrices(matrixTransformer, moon_translation_transformation_matrix, object_standard_matrix);
        Matrix second_tree_head_lower_transformation_inv_matrix = multiply_matrices(matrixTransformer, moon_translation_inv_transformation_matrix, object_standard_inv_matrix);

        second_tree_head_lower_transformation_matrix = multiply_matrices(matrixTransformer, cylinder_roll_transformation_matrix, second_tree_head_lower_transformation_matrix);
        second_tree_head_lower_transformation_inv_matrix = multiply_matrices(matrixTransformer, cylinder_roll_inv_transformation_matrix, second_tree_head_lower_transformation_inv_matrix);

        second_tree_head_lower_transformation_matrix = multiply_matrices(matrixTransformer, tree_head_lower_scaling_transformation_matrix, second_tree_head_lower_transformation_matrix);
        second_tree_head_lower_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_head_lower_scaling_inv_transformation_matrix, second_tree_head_lower_transformation_inv_matrix);

        //Translation Cylinder tree head upper
        m14 = -0.2; //x
        m24 = 0; //y
        m34 = 3; //z
        moon_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        moon_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        Matrix second_tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, moon_translation_transformation_matrix, object_standard_matrix);
        Matrix second_tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, moon_translation_inv_transformation_matrix, object_standard_inv_matrix);

        second_tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, cylinder_roll_transformation_matrix, second_tree_head_upper_transformation_matrix);
        second_tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, cylinder_roll_inv_transformation_matrix, second_tree_head_upper_transformation_inv_matrix);

        second_tree_head_upper_transformation_matrix = multiply_matrices(matrixTransformer, tree_head_upper_scaling_transformation_matrix, second_tree_head_upper_transformation_matrix);
        second_tree_head_upper_transformation_inv_matrix = multiply_matrices(matrixTransformer, tree_head_upper_scaling_inv_transformation_matrix, second_tree_head_upper_transformation_inv_matrix);

        //TREE ENDING

        //MOON BEGIN
        Matrix moon_transformation_matrix = object_standard_matrix;
        Matrix moon_transformation_inv_matrix = object_standard_inv_matrix;

        //Scaling Moon
        sx = 0.5; //x
        sy = 0.5; //y
        sz = 0.5; //z
        Matrix moon_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix moon_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);

        moon_transformation_matrix = multiply_matrices(matrixTransformer, moon_scaling_transformation_matrix, moon_transformation_matrix);
        moon_transformation_inv_matrix = multiply_matrices(matrixTransformer, moon_scaling_inv_transformation_matrix, moon_transformation_inv_matrix);

        //Translation Moon
        m14 = -6; //x
        m24 = 0; //y
        m34 = 4; //z
        moon_translation_transformation_matrix = matrix3DFactory.create_trans_matrix(m14, m24, m34);
        moon_translation_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14, m24, m34);

        moon_transformation_matrix = multiply_matrices(matrixTransformer, moon_translation_transformation_matrix, moon_transformation_matrix);
        moon_transformation_inv_matrix = multiply_matrices(matrixTransformer, moon_translation_inv_transformation_matrix, moon_transformation_inv_matrix);

        //Moon END

        //World Cube Scaling (wordt de grote omvangende kubus waarin de wereld zit)
        double cube_sx = 300; //x
        double cube_sy = 300; //y
        double cube_sz = 300; //z
        Matrix cube_scaling_transformation_matrix = matrix3DFactory.create_scal_matrix(cube_sx, cube_sy, cube_sz);
        Matrix cube_scaling_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(cube_sx, cube_sy, cube_sz);

        //FIRST TREE

        Cylinder tree_head_lower = objectFactory.create_cylinder(tree_head_lower_transformation_matrix, tree_head_lower_transformation_inv_matrix, default_material, Config.GRASSCOLOR, 0);
        world.add_object(tree_head_lower);

        Cylinder tree_head_upper = objectFactory.create_cylinder(tree_head_upper_transformation_matrix, tree_head_upper_transformation_inv_matrix, default_material, Config.GRASSCOLOR, 0);
        world.add_object(tree_head_upper);

        Cylinder tree_stam = objectFactory.create_cylinder(stam_transformation_matrix, stam_transformation_inv_matrix, default_material, Config.WOODCOLOR, noise, 1);
        world.add_object(tree_stam);

        //SECOND TREE

        Cylinder second_tree_head_lower = objectFactory.create_cylinder(second_tree_head_lower_transformation_matrix, second_tree_head_lower_transformation_inv_matrix, default_material, Config.GRASSCOLOR, 0);
        world.add_object(second_tree_head_lower);

        Cylinder second_tree_head_upper = objectFactory.create_cylinder(second_tree_head_upper_transformation_matrix, second_tree_head_upper_transformation_inv_matrix, default_material, Config.GRASSCOLOR, 0);
        world.add_object(second_tree_head_upper);

        Cylinder second_tree_stam = objectFactory.create_cylinder(second_stam_transformation_matrix, second_stam_transformation_inv_matrix, default_material, Config.WOODCOLOR, noise, 1);
        world.add_object(second_tree_stam);

        //HOUSE

        Cube house = objectFactory.create_cube(house_transformation_matrix, house_transformation_inv_matrix, default_material, Config.BRICKCOLOR, floor_texture);
        world.add_object(house);

        Cube door = objectFactory.create_cube(door_transformation_matrix, door_transformation_inv_matrix, default_material, Config.DOVEWHITE);
        world.add_object(door);

        //SUN

        Sphere moon = objectFactory.create_sphere(moon_transformation_matrix,moon_transformation_inv_matrix, default_material, Config.MOONCOLOR);
        world.add_object(moon);

        //Boolean objects

        BooleanObject complete_house = new BooleanObject(house, door, BooleanObjectType.UNION);
        world.add_boolean_object(complete_house);

        Cube world_cube = objectFactory.create_cube(cube_scaling_transformation_matrix, cube_scaling_inv_transformation_matrix, default_material, Config.SKYCOLOR);
        world.add_object(world_cube);

        Renderer renderer = objectFactory.create_renderer();
        renderer.render_screen(world);
        System.out.println("rendered");
    }

    static Matrix multiply_matrices(MatrixTransformer matrixTransformer, Matrix matrix_1, Matrix matrix_2) {
        double[][] transformed_scaling_matrix_array = matrixTransformer.multiplyMatrices(matrix_1.get_matrix(), matrix_2.get_matrix());
        return new Matrix(transformed_scaling_matrix_array);
    }
}
