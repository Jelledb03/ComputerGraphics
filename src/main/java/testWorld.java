import internal.*;
import rendering2D.Renderer;
import shapes.Cylinder;
import shapes.Sphere;
import world.Camera;
import world.Light;
import world.World;

public class testWorld {
    public static void main(String[] args) {
        InternalFactory internalFactory = new InternalFactory();
        Matrix3DFactory matrix3DFactory = new Matrix3DFactory();
        Camera camera = new Camera();
        //afstand van camera tot de viewpoint
        camera.set_N(2500);
        Point eye = new Point(10, 10, 10);
        camera.set_eye(eye);
        Vector n = new Vector(1, 1, 1);
        Vector u = new Vector(-1, 1, 0);
        Vector v = new Vector(1, 1, -1);
        camera.set_n(n);
        camera.set_u(u);
        camera.set_v(v);
        Ray ray = internalFactory.createRay(camera, 25, 25);
        World world = new World(camera);
        Point lightPoint = new Point(10, 10, 20);
        Light light = new Light(lightPoint, 1);
        world.add_light(light);

        //objects.Sph
        // re Transformation matrices
        double sx = 1; //x
        double sy = 1; //y
        double sz = 1; //z
        Matrix object_transformation_matrix = matrix3DFactory.create_scal_matrix(sx, sy, sz);
        Matrix object_inv_transformation_matrix = matrix3DFactory.create_inv_scal_matrix(sx, sy, sz);

        Sphere sphere = new Sphere(object_transformation_matrix, object_inv_transformation_matrix);
        world.add_object(sphere);

        //Cylinder cylinder = new Cylinder(object_transformation_matrix, object_inv_transformation_matrix, 0.5);
        //world.add_object(cylinder);

//        //objects.Sphere Transformation matrices
//        double m14_2 = 10;
//        double m24_2 = 10;
//        double m34_2 = 10;
//        object_transformation_matrix = matrix3DFactory.create_trans_matrix(m14_2, m24_2, m34_2);
//        object_inv_transformation_matrix = matrix3DFactory.create_inv_trans_matrix(m14_2, m24_2, m34_2);
//
//        Sphere sphere2 = new Sphere(object_transformation_matrix, object_inv_transformation_matrix);
//        world.add_object(sphere2);

        Renderer renderer = new Renderer();
        renderer.render_screen(world);
        System.out.println("rendered");
    }
}
