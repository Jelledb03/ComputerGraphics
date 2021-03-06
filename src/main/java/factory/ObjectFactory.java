package factory;

import config.Config;
import exterior.material.Material;
import internal.InternalTransformer;
import internal.Matrix;
import internal.Point;
import internal.Ray;
import internal.Vector;
import render.Renderer;
import shapes.Cube;
import shapes.Cylinder;
import shapes.Sphere;
import shapes.Square;
import exterior.texture.FloorTexture;
import exterior.texture.Noise;
import exterior.texture.Texture;
import exterior.texture.WoodTexture;
import world.Camera;
import world.Light;
import world.World;

import java.awt.*;

public class ObjectFactory {
    InternalTransformer internalTransformer;
    public ObjectFactory() {
        internalTransformer = new InternalTransformer();
    }
    public Vector create_vector(double x, double y, double z){
        return new Vector(x,y,z);
    }
    public Point create_point(double x, double y, double z){
        return new Point(x,y,z);
    }
    public Camera create_camera(Point eye, Vector u, Vector v, Vector n, double N){
        Camera camera = new Camera();
        camera.set_N(N);
        camera.set_eye(eye);
        camera.set_n(n);
        camera.set_u(u);
        camera.set_v(v);
        return camera;
    }
    public Camera create_camera(Point eye, Point look, Vector up, double N){
        return new Camera(eye,look,up,N);
    }
    public Light create_light(Point lightPoint, double light_intensity){
        return new Light(lightPoint, light_intensity);
    }

    public Renderer create_renderer(){
        return new Renderer();
    }

    //creates ray from the eye of the camera, the medium and the col,row of the 2D screen
    public Ray create_ray(Camera camera, double medium, int col, int row){
        //p615 12.6
        //dir_rc_n = -N*n
        Vector dir_rc_n = internalTransformer.vector_product(camera.get_n(), -camera.get_N());
        //dir_rc_u = W*(2c/nCols - 1)*u
        //uc = W*(2c/nCols - 1)
        //nCols = screen width
        double uc = (double) camera.get_w() * ( (2*(double)col/(double)Config.SCREEN_WIDTH) - 1);
        Vector dir_rc_u = internalTransformer.vector_product(camera.get_u(), uc);
        //dir_rc_v = H*(2r/nRows - 1)*v
        //vc = H*(2r/nRows - 1)
        //nRows = screen height
        double vc = (double) camera.get_h() * ( (2*(double)row/(double)Config.SCREEN_HEIGHT) - 1);
        Vector dir_rc_v = internalTransformer.vector_product(camera.get_v(), vc);

        //create dir vector (som van alle dir rc vectoren)
        Vector dir_rc = internalTransformer.tripleVectorSum(dir_rc_n, dir_rc_u, dir_rc_v);
//        create ray met camera eye en dir vector
//        System.out.println(camera.get_eye());
//        System.out.println(dir_rc.get_X() + " " + dir_rc.get_Y() + " " + dir_rc.get_Z());
        return new Ray(camera.get_eye(), dir_rc, medium);
    }

    public WoodTexture create_wood_texture(double D, double A, double M, double phi, double K, double N){
        return new WoodTexture(D,A,M, phi, K, N);
    }

    public World create_world(Camera camera){
        return new World(camera);
    }

    //creates sphere
    public Sphere create_sphere(Matrix transform_matrix, Matrix inv_transform_matrix, Material material, Color color){
        return new Sphere(transform_matrix, inv_transform_matrix, material, color);
    }

    //creates square
    public Square create_square(Matrix transform_matrix, Matrix inv_transform_matrix, Material material, Color color){
        return new Square(transform_matrix, inv_transform_matrix, material, color);
    }

    //creates cylinder
    public Cylinder create_cylinder(Matrix transform_matrix, Matrix inv_transform_matrix, Material material, Color color, double s){
        return new Cylinder(transform_matrix, inv_transform_matrix, material, color, s);
    }

    //creates cube
    public Cube create_cube(Matrix transform_matrix, Matrix inv_transform_matrix, Material material, Color color){
        return new Cube(transform_matrix, inv_transform_matrix, material, color);
    }

    //creates sphere wood texture
    public Sphere create_sphere(Matrix transform_matrix, Matrix inv_transform_matrix, Material material, Color color, Texture wood_texture){
        return new Sphere(transform_matrix, inv_transform_matrix, material, color, wood_texture);
    }

    //creates cube wood texture
    public Cube create_cube(Matrix transform_matrix, Matrix inv_transform_matrix, Material material, Color color, Texture wood_texture){
        return new Cube(transform_matrix, inv_transform_matrix, material, color, wood_texture);
    }

    public Cylinder create_cylinder(Matrix transform_matrix, Matrix inv_transform_matrix, Material material, Color color, Texture wood_texture, double s) {
        return new Cylinder(transform_matrix, inv_transform_matrix, material, color, wood_texture, s);
    }

    public Texture create_noise(double scale) {
        return new Noise(scale);
    }

    public Texture create_floor_texture(double scaling_factor) {
        return new FloorTexture(scaling_factor);
    }
}
