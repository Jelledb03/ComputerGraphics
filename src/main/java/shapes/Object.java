package shapes;

import config.Config;
import internal.*;
import internal.MatrixTransformer;
import internal.Point;

import java.awt.*;
import java.util.ArrayList;

//Wanneer we dit als abstract classificieren kunnen we hier geen objecten van initialiseren
public abstract class Object {
    private Matrix transformation_matrix;
    private Matrix inverse_transformation_matrix;
    private InternalTransformer internalTransformer;
    private Color color;
    private double local_coeff;
    private double refraction_coeff;
    private double reflection_coeff;
    private double c; //holds the relative speed of the ray compared to the speed of light
    private int surface = 0;

    public Object(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double local_coeff, double reflection_coeff, double refraction_coeff, double c, Color color) {
        this.transformation_matrix = transformation_matrix;
        this.inverse_transformation_matrix = inverse_transformation_matrix;
        this.internalTransformer = new InternalTransformer();
        this.local_coeff = local_coeff;
        this.reflection_coeff = reflection_coeff;
        this.refraction_coeff = refraction_coeff;
        this.c = c;
        this.color = color;
    }

    public Object(Matrix transformation_matrix, Matrix inverse_transformation_matrix, double c, Color color) {
        this.transformation_matrix = transformation_matrix;
        this.inverse_transformation_matrix = inverse_transformation_matrix;
        this.internalTransformer = new InternalTransformer();
        this.local_coeff = Config.DEFAULT_LOCAL_COEFF;
        this.reflection_coeff = Config.DEFAULT_REFLECTION_COEFF;
        this.refraction_coeff = Config.DEFAULT_REFRACTION_COEFF;
        this.c = c;
        this.color = color;
    }

    public Object(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        this.transformation_matrix = transformation_matrix;
        this.inverse_transformation_matrix = inverse_transformation_matrix;
        this.internalTransformer = new InternalTransformer();
        this.local_coeff = Config.DEFAULT_LOCAL_COEFF;
        this.reflection_coeff = Config.DEFAULT_REFLECTION_COEFF;
        this.refraction_coeff = Config.DEFAULT_REFRACTION_COEFF;
        this.color = Config.DEFAULT_OBJECT_COLOR;
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

    public InternalTransformer getInternalTransformer() {
        return internalTransformer;
    }

    public void setInternalTransformer(InternalTransformer internalTransformer) {
        this.internalTransformer = internalTransformer;
    }

    //Zal een hitpoint time terug geven van wanneer de ray dit object hit
    //Nadat dit voor alle objecten gebeurd is zal er moeten nagekeken worden wat de laagste hit time is en hiermee het hitpoint berekenen
    //Want in dit hitpoint zal de ray daadwerkelijk komen en raken. De andere hitpoints kunnen mogelijk zijn van objecten achter dit object (zijn we niet in geintresseerd)
    //Verder uitwerken in de overgeerfde klassen
    public void hit_reg(Ray ray, Intersection intersection) {
        //Kan ik hier initialiseren of meegeven als parameter (not sure wat het beste is)
        Point S = ray.get_eye();
        Vector c = ray.get_dir();
        MatrixTransformer matrixTransformer = new MatrixTransformer();
        //1. Have to transform ray with inverse transformation off sphere
        //System.out.println(Arrays.deepToString(c.get_vector()));
        //System.out.println(Arrays.deepToString(this.get_inverse_transformation_matrix().get_matrix()));
        double[][] point_s_t = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), S.get_point());
        double[][] vector_c_t = matrixTransformer.multiplyMatrices(this.get_inverse_transformation_matrix().get_matrix(), c.get_vector());
        //System.out.println("point: " + Arrays.deepToString(point_s));
        //System.out.println("vector: " + Arrays.deepToString(vector_c));
        Point S_t = new Point(point_s_t);
        Vector c_t = new Vector(vector_c_t);

        //2. Calculate hit_time with transformed ray
        //Step 2
        //Zou ipv hier de smallest hit time te vinden alle hit times registreren
        //Geef die hier alle t_hits terug
        ArrayList<Double> hit_times = object_hit_detec(S_t, c_t, intersection);
        if(!hit_times.isEmpty()){
            //There were hitpoints, so loop through!
            for(double t_hit: hit_times){
                //Going to calculate hit point and normal vector for every hit time!
            }
        }
        //Now we have every hit time of the object, we will loop through them to create the hitobjects for every .
        if (t_hit == 0) {
            //Geen hit, dus
            return new HitObject();
        } else {
            //3. Going to create a hit object here and calculate hit point with original ray
            //Going to replace this with finding the lowest hit time and set the variable lowest_hit_time_index
            Point hitPoint = calculate_hit_point(S, c, t_hit);
            if (hitPoint == null) {
                //This can happen, for example when looking for an intersection with a square
                return new HitObject();
            } else {
                //Calculate normal_vector
                Point transformed_hit_point = calculate_hit_point(S_t, c_t, t_hit);
                Vector transformed_normal_vector = calculate_normal_vector(transformed_hit_point, surface);
                //Ik denk dat je die dan gewoon moet transformeren
                double[][] normal_vector_d = matrixTransformer.multiplyMatrices(this.get_transformation_matrix().get_matrix(), transformed_normal_vector.get_vector());
                Vector normal_vector = new Vector(normal_vector_d);
                Vector normal_vector_norm = normal_vector.normalize();
                //IlluminationObject r_illumination_object = new IlluminationObject(0.7038, 12.8, 0.256777, 0.19125);
                //IlluminationObject g_illumination_object = new IlluminationObject(0.27048, 12.8, 0.137622, 0.0735);
                //IlluminationObject b_illumination_object = new IlluminationObject(0.0828, 12.8, 0.086014, 0.0225);
                //return new HitObject(hitPoint, normal_vector_norm, this.color, t_hit,r_illumination_object,g_illumination_object,b_illumination_object,local_coeff, reflection_coeff, refraction_coeff, this.c);
                return new HitObject(hitPoint, normal_vector_norm, this.color, t_hit, local_coeff, reflection_coeff, refraction_coeff, this.c);
            }
        }
    }

    abstract Vector calculate_normal_vector(Point hitPoint, int surface);

    abstract ArrayList<Double> object_hit_detec(Point S_t, Vector c_t, Intersection intersection);

    abstract Point calculate_hit_point(Point S, Vector c, double t_hit);

    public Color get_color() {
        return color;
    }

    public void set_color(Color color) {
        this.color = color;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public double get_c() {
        return c;
    }

    public void set_c(double c) {
        this.c = c;
    }
}
