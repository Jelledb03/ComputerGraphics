package world;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import internal.*;
import internal.Point;
import shapes.Object;

public class World {
    private Camera camera;
    private List<Object> objects;
    private List<Light> lights;
    private InternalTransformer internalTransformer;

    public World(Camera camera, List<Object> objects) {
        this.camera = camera;
        this.objects = objects;
        this.internalTransformer = new InternalTransformer();
        this.lights = new ArrayList<>();
    }

    public World(Camera camera) {
        this.camera = camera;
        this.objects = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.internalTransformer = new InternalTransformer();
    }

    public World() {
        this.camera = new Camera();
        this.objects = new ArrayList<>();
        this.lights = new ArrayList<>();
        this.internalTransformer = new InternalTransformer();
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    public HitObject calculateClosestHitObject(Ray ray, int iterator) {
        //Hierin gaan we de dichstbijzijnde hitobject vinden en teruggeven
        //Zullen met een ray over alle objecten in de list gaan en elke keer de hittime nakijken en het hitobject met kleinste hittime bijhouden
        HitObject lowest_time_hitObject = new HitObject();
        for (Object object : objects) {
            HitObject curr_hitObject = object.hit_reg(ray);
            if ((!lowest_time_hitObject.is_collided())) { // && tempHitPoint.getHitTime() > getCamera().getDistanceN()
                lowest_time_hitObject = curr_hitObject;
            } else {
                if (!lowest_time_hitObject.is_collided() && (curr_hitObject.get_hit_time() < lowest_time_hitObject.get_hit_time())) {
                    lowest_time_hitObject = curr_hitObject;
                }
            }
        }
        List<Integer> reflected_colors = new ArrayList<>();
        //reflection colors are zero when lowest_time_hitobject is not collided
        reflected_colors.add(0);
        reflected_colors.add(0);
        reflected_colors.add(0);
        if(lowest_time_hitObject.is_collided()) {
            IlluminationObject[] illuminationObjects = new IlluminationObject[]{lowest_time_hitObject.get_r_illuminationObject(), lowest_time_hitObject.get_g_illuminationObject(),lowest_time_hitObject.get_b_illuminationObject()};
            List<Double> local_intensities = calculate_local_intensity(ray.get_dir(),lowest_time_hitObject,illuminationObjects, iterator);
            List<Integer> local_colors = new ArrayList<>();
            local_colors.add((int)(lowest_time_hitObject.get_color().getRed()*local_intensities.get(0)));
            local_colors.add((int)(lowest_time_hitObject.get_color().getGreen()*local_intensities.get(1)));
            local_colors.add((int)(lowest_time_hitObject.get_color().getBlue()*local_intensities.get(2)));
            if(iterator < 3){
                //verhoudingen som moet 1 zijn, nog vergeten!!!!
                //Met kleur werken
                //som van kleur met shading, reflectie en refractie
                //shading met intensiteit

                //Calculate reflection by calling recursive calculateClosestHitObject function
                //First calculate reflection ray
                //r = dir - 2 * (dir * m) * m
                //Ph point is hitPoint
                Point Ph = lowest_time_hitObject.get_hit_point();
                Vector dir = ray.get_dir();
                Vector dir_norm = dir.normalize();
                Vector m = lowest_time_hitObject.get_normal_vector();
                Vector m_norm = m.normalize();
                double dot_product = -2 * internalTransformer.dot_product(dir_norm, m_norm);
                Vector second_vector = internalTransformer.vector_product(m_norm, dot_product);
                Vector r = internalTransformer.vector_sum(dir_norm, second_vector);
                Ray reflection_ray = new Ray(Ph, r);
                //Second calculate the next hit object and its intensity parameters
                iterator++;
                HitObject reflected_hitObject = calculateClosestHitObject(reflection_ray, iterator);
                List<Double> reflected_intensities = new ArrayList<>();
                if(reflected_hitObject.is_collided()) {
                    reflected_intensities.add(reflected_hitObject.get_r_illuminationObject().get_intensity());
                    reflected_intensities.add(reflected_hitObject.get_g_illuminationObject().get_intensity());
                    reflected_intensities.add(reflected_hitObject.get_b_illuminationObject().get_intensity());
                    //Calculated reflected colors
                    reflected_colors.set(0,(int)(reflected_hitObject.get_color().getRed()*reflected_intensities.get(0)));
                    reflected_colors.set(1,(int)(reflected_hitObject.get_color().getGreen()*reflected_intensities.get(1)));
                    reflected_colors.set(2,(int)(reflected_hitObject.get_color().getBlue()*reflected_intensities.get(2)));
                    Color reflected_hitObject_color = new Color(reflected_colors.get(0), reflected_colors.get(1), reflected_colors.get(2));
                    if(reflected_colors.get(0) != 0 || reflected_colors.get(1) != 0 || reflected_colors.get(2) != 0){
                        System.out.println(reflected_hitObject_color);
                    }
                }
            }
            //Will calculated the actual color of the hitObject (sum of local_color + reflected_color + refracted_color

            int total_r_color = (int)(lowest_time_hitObject.get_local_coeff() * local_colors.get(0)) + (int)(lowest_time_hitObject.get_reflection_coeff() * reflected_colors.get(0));
            int total_g_color = (int)(lowest_time_hitObject.get_local_coeff() * local_colors.get(1)) + (int)(lowest_time_hitObject.get_reflection_coeff() * reflected_colors.get(1));
            int total_b_color = (int)(lowest_time_hitObject.get_local_coeff() * local_colors.get(2)) + (int)(lowest_time_hitObject.get_reflection_coeff() * reflected_colors.get(2));
            Color lowest_time_hitObject_color = new Color(total_r_color, total_g_color, total_b_color);
            lowest_time_hitObject.set_color(lowest_time_hitObject_color);
            //lowest_time_hitObject.get_r_illuminationObject().set_intensity(local_intensities.get(0));
            //lowest_time_hitObject.get_g_illuminationObject().set_intensity(local_intensities.get(1));
            //lowest_time_hitObject.get_b_illuminationObject().set_intensity(local_intensities.get(2));
        }
        //if (lowest_time_hitObject.is_collided()) {
            //System.out.println("creating hit objects");
            //System.out.println(lowest_time_hitObject.get_hit_time());
            //System.out.println(lowest_time_hitObject.get_hit_point().get_X() + " " + lowest_time_hitObject.get_hit_point().get_Y() + " " + lowest_time_hitObject.get_hit_point().get_Z());
        //}
        return lowest_time_hitObject;
    }

    //Nog wat aanpassingen doen voor color (RGB zie p390, maar basics blijven hetzelfde)
    public List<Double> calculate_local_intensity(Vector ray, HitObject hitObject, IlluminationObject[] illuminationObjects, int iterator){
        //First we will loop through all the lights
        // It is possible that more than one light shines to the eye through an object (have to sum it)
        double total_diff_coefficient = 0;
        double total_spec_coefficient = 0;
        double total_ambient = 0;
        double total_lambert = 0;
        double total_phong = 0;
        for(Light light: lights){
            Point L = light.getLightPoint();
            Point P = hitObject.get_hit_point();
            //Need three vectors
            //normal vector m to the surface at P
            //vector v from P to the viewer's eye
            //vector s from P to the light source
            Vector s = internalTransformer.substraction_to_vector(L, P); // L- P
            Vector v = internalTransformer.vector_product(ray,-1);
            // normal vector op het object nodig
            // vector van middelpunt bol op het hitpoint
            // normaal is vector die loodrecht staat op object
            Vector m = hitObject.get_normal_vector();
            // Id is independent of the angle between m and v
            // It is dependent on the orientation of the eye relative to the point source
            // cos(phi) = the dot product between normalized versions of s and m
            // Id = Is * rhod max(s*m/|s||m| , 0 )
            Vector s_norm = s.normalize();
            Vector m_norm = m.normalize();
            double lambert = internalTransformer.dot_product(s_norm, m_norm);
            double total = 0;
            //Have to calculate the diffuse component for this light and eye
            if(lambert > 0){
                //Lambert will be calculated with light intensity and lambert, diffuse reflection coeff will be added later
                total_lambert += light.getLight_source_intensity() * lambert;
                //double diff_coeff = light.getLight_source_intensity() * illuminationObject.get_diffuse_reflection_coeff() * lambert;
                //total_diff_coefficient += diff_coeff;
                //total += diff_coeff;
            }// if dot prod is negative the eye is faced away from the light
            // Calculation of direction r
            // PHONG
            //Use halfway vector calculation
            Vector h = internalTransformer.vector_sum(s,v);
            Vector h_norm = h.normalize();
            double phong = internalTransformer.dot_product(h_norm, m_norm);
            if(phong > 0){
                total_phong += light.getLight_source_intensity() * phong;
                //double spec_coeff = light.getLight_source_intensity() * illuminationObject.get_specular_reflection_coeff() * Math.pow(phong, illuminationObject.get_fallof());
                //total_spec_coefficient += spec_coeff;
                //total += spec_coeff;
            }
            double ambient_coeff = light.getLight_source_intensity();
            total_ambient += ambient_coeff;
            total += ambient_coeff;
        }
        //total lambert and phong calculated
        List<Double> total_intensities = new ArrayList<>();
        for (IlluminationObject illuminationObject: illuminationObjects
             ) {
            double total_diffuse_coeff = illuminationObject.get_diffuse_reflection_coeff() * total_lambert;
            double total_specular_coeff = illuminationObject.get_specular_reflection_coeff() * Math.pow(total_phong, illuminationObject.get_fallof());
            double total_ambient_coeff = illuminationObject.get_ambient_reflection_coeff() * total_ambient;
            double total_local_intensity = total_diffuse_coeff + total_specular_coeff + total_ambient_coeff;
            /** Nog eens navragen of dit correct is of hoe ik dit beter zou kunnen oplossen **/
            total_intensities.add(total_local_intensity);
        }
        return total_intensities;
    }

    public void add_object(Object object) {
        this.objects.add(object);
    }

    public void add_light(Light light){
        this.lights.add(light);
    }
}
