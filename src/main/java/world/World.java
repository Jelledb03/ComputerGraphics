package world;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import config.Config;
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

        //Gaan we toch anders doen, we gaan eerst alle intersecties vinden (met hun timing)
        //Hiervoor moeten we dus een Intersection object aanmaken
        Intersection intersection = new Intersection();

        for (Object object : objects) {
            object.hit_reg(ray, intersection);
            //HitObject curr_hitObject = object.hit_reg(ray);
//            if ((!lowest_time_hitObject.is_collided())) { // && tempHitPoint.getHitTime() > getCamera().getDistanceN()
//                lowest_time_hitObject = curr_hitObject;
//            } else {
//                if (!lowest_time_hitObject.is_collided() && (curr_hitObject.get_hit_time() < lowest_time_hitObject.get_hit_time())) {
//                    lowest_time_hitObject = curr_hitObject;
//                }
//            }
        }
        //We now have calculated all the hitobjects for this ray over every object
        //Now we have to find the lowest hit time
        int lowest_hit_time_index = -1;
        double lowest_t_hit = Double.POSITIVE_INFINITY;
        for(double t_hit: intersection.get_hit_times()){
            if(t_hit < lowest_t_hit){
                lowest_t_hit = t_hit;
            }
        }
        lowest_hit_time_index = intersection.get_hit_times().indexOf(lowest_t_hit);
        HitObject lowest_time_hitObject = new HitObject();
        if(lowest_hit_time_index > -1){
            lowest_time_hitObject = intersection.get_hit_objects().get(lowest_hit_time_index);
        }
        List<Integer> reflected_colors = new ArrayList<>();
        //reflection colors are zero when lowest_time_hitobject is not collided
        reflected_colors.add(0);
        reflected_colors.add(0);
        reflected_colors.add(0);
        List<Integer> refracted_colors = new ArrayList<>();
        //reflection colors are zero when lowest_time_hitobject is not collided
        refracted_colors.add(0);
        refracted_colors.add(0);
        refracted_colors.add(0);
        //Step 5
        //Check for emptiness of hits arraylist (empty means zero collisions)!!! afterwards check if the hitObject is collided (Dont think this is necessary but can't be a wrong thing to do)
        if (lowest_time_hitObject.is_collided()) {
            IlluminationObject[] illuminationObjects = new IlluminationObject[]{lowest_time_hitObject.get_r_illuminationObject(), lowest_time_hitObject.get_g_illuminationObject(), lowest_time_hitObject.get_b_illuminationObject()};
            List<Double> local_intensities = calculate_local_intensity(ray.get_dir(), lowest_time_hitObject, illuminationObjects, iterator);
            List<Integer> local_colors = new ArrayList<>();
            local_colors.add((int) (lowest_time_hitObject.get_color().getRed() * local_intensities.get(0)));
            local_colors.add((int) (lowest_time_hitObject.get_color().getGreen() * local_intensities.get(1)));
            local_colors.add((int) (lowest_time_hitObject.get_color().getBlue() * local_intensities.get(2)));
            Color local_color = new Color(local_colors.get(0),local_colors.get(1),local_colors.get(2));
            if(local_color.getRed() == 0 && local_color.getGreen() == 0 && local_color.getBlue() == 0){
                System.out.println(local_color);
            }
            if (iterator < Config.MAX_ITERATION) {
                //Met kleur werken
                //som van kleur met shading, reflectie en refractie
                //shading met intensiteit
                //REFLECTION
                //Calculate reflection by calling recursive calculateClosestHitObject function
                //First calculate reflection ray
                //r = dir - 2 * (dir * m) * m
                //Ph point is hitPoint
                Point Ph = lowest_time_hitObject.get_hit_point();
                Vector dir = ray.get_dir();
                Vector dir_norm = dir.normalize();
                Vector m = lowest_time_hitObject.get_normal_vector();
                Vector m_norm = m.normalize();
                double dot_product_dir_m = internalTransformer.dot_product(dir_norm, m_norm);
                double second_vector_mult_term = -2 * dot_product_dir_m;
                Vector second_vector = internalTransformer.vector_product(m_norm, second_vector_mult_term);
                Vector r = internalTransformer.vector_sum(dir_norm, second_vector);
                //This means that whenever we start in a big cube (in our world), the speed in that cube will be equal to air speed
                double c = ray.get_c();
                Ray reflection_ray = new Ray(Ph, r, c);
                //Second calculate the next hit object and its intensity parameters
                iterator++;
                HitObject reflected_hitObject = calculateClosestHitObject(reflection_ray, iterator);
                if (reflected_hitObject.is_collided()) {
                    reflected_colors = find_colors(reflected_hitObject);
                    Color reflected_color = new Color(reflected_colors.get(0),reflected_colors.get(1),reflected_colors.get(2));
                    if(reflected_color.getRed() == 0 && reflected_color.getGreen() == 0 && reflected_color.getBlue() == 0){
                        int hello = 0;
                    }
            }
                //REFRACTION
                //We are first going to calculate cos(phi2)
                //If this cos is lower or equal to 0, phi2 angle is equal to 90 or bigger we are in the critical angle range and there will be total reflection (zero refraction)
                //If that occurs we don't have to focus on calculating direction t and a new ray, because there will be no refraction

                //cos(phi2)
                /*
                    Point Ph = lowest_time_hitObject.get_hit_point();
                    Vector dir = ray.get_dir();
                    Vector dir_norm = dir.normalize();
                    Vector m = lowest_time_hitObject.get_normal_vector();
                    Vector m_norm = m.normalize();
                    double dot_product_dir_m = internalTransformer.dot_product(dir_norm, m_norm);*/
                double c1 = ray.get_c();
                double c2 = lowest_time_hitObject.get_c();
                double deviation_term = c2 / c1;
                double speed_calc_refr = Math.pow(deviation_term, 2);
                double second_term_refr = (1 - Math.pow(dot_product_dir_m, 2));
                second_term_refr = speed_calc_refr * second_term_refr;
                double cos_phi_2 = Math.sqrt(1 - second_term_refr);
                //Check cos phi 2
                if (cos_phi_2 > 0) {
                    //This means that there will be refraction so a new ray has to be calculated to be sent
                    Vector first_vector_refr = internalTransformer.vector_product(dir_norm, deviation_term);
                    double second_vector_term_refr = (deviation_term * dot_product_dir_m) - cos_phi_2;
                    Vector second_vector_refr = internalTransformer.vector_product(m_norm, second_vector_term_refr);
                    Vector t = internalTransformer.vector_sum(first_vector_refr, second_vector_refr);
                    Ray refraction_ray = new Ray(Ph, t, c2);
                    //Calculates refracted hit object
                    HitObject refracted_hitObject = calculateClosestHitObject(refraction_ray, iterator);
                    if (refracted_hitObject.is_collided()) {
                        refracted_colors = find_colors(refracted_hitObject);
                        Color refracted_color = new Color(refracted_colors.get(0),refracted_colors.get(1),refracted_colors.get(2));
                        if(refracted_color.getRed() == 0 && refracted_color.getGreen() == 0 && refracted_color.getBlue() == 0){
                            int hello = 0;
                        }
                    }
                }
            }
            //Will calculated the actual color of the hitObject (sum of local_color + reflected_color + refracted_color

            int total_r_color = (int) (lowest_time_hitObject.get_local_coeff() * local_colors.get(0)) + (int) (lowest_time_hitObject.get_reflection_coeff() * reflected_colors.get(0)) + (int) (lowest_time_hitObject.get_refraction_coeff() * refracted_colors.get(0));
            int total_g_color = (int) (lowest_time_hitObject.get_local_coeff() * local_colors.get(1)) + (int) (lowest_time_hitObject.get_reflection_coeff() * reflected_colors.get(1)) + (int) (lowest_time_hitObject.get_refraction_coeff() * refracted_colors.get(1));
            int total_b_color = (int) (lowest_time_hitObject.get_local_coeff() * local_colors.get(2)) + (int) (lowest_time_hitObject.get_reflection_coeff() * reflected_colors.get(2)) + (int) (lowest_time_hitObject.get_refraction_coeff() * refracted_colors.get(2));
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

    private List<Integer> find_colors(HitObject hitObject) {
        List<Integer> colors = new ArrayList<>();
        //Calculated reflected colors
        colors.add(hitObject.get_color().getRed());
        colors.add(hitObject.get_color().getGreen());
        colors.add(hitObject.get_color().getBlue());
        Color color = new Color(colors.get(0), colors.get(1), colors.get(2));
        /*if(colors.get(0) != 0 || colors.get(1) != 0 || colors.get(2) != 0){
            System.out.println(color);
        }*/
        return colors;
    }

    //Nog wat aanpassingen doen voor color (RGB zie p390, maar basics blijven hetzelfde)
    public List<Double> calculate_local_intensity(Vector ray, HitObject hitObject, IlluminationObject[] illuminationObjects, int iterator) {
        //First we will loop through all the lights
        // It is possible that more than one light shines to the eye through an object (have to sum it)
        double total_diff_coefficient = 0;
        double total_spec_coefficient = 0;
        double total_ambient = 0;
        double total_lambert = 0;
        double total_phong = 0;
        Point P = hitObject.get_hit_point();
        for (Light light : lights) {
            Point L = light.getLightPoint();
            //Need three vectors
            //normal vector m to the surface at P
            //vector v from P to the viewer's eye
            //vector s from P to the light source
            Vector s = internalTransformer.substraction_to_vector(L, P); // L- P
            Vector v = internalTransformer.vector_product(ray, -1);
            // normal vector op het object nodig
            // vector van middelpunt bol op het hitpoint
            // normaal is vector die loodrecht staat op hitpoint
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
            if (lambert > 0) {
                //Lambert will be calculated with light intensity and lambert, diffuse reflection coeff will be added later
                total_lambert += light.getLight_source_intensity() * lambert;
                //double diff_coeff = light.getLight_source_intensity() * illuminationObject.get_diffuse_reflection_coeff() * lambert;
                //total_diff_coefficient += diff_coeff;
                //total += diff_coeff;
            }// if dot prod is negative the eye is faced away from the light
            // Calculation of direction r
            // PHONG
            //Use halfway vector calculation
            Vector h = internalTransformer.vector_sum(s, v);
            Vector h_norm = h.normalize();
            double phong = internalTransformer.dot_product(h_norm, m_norm);
            if (phong > 0) {
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
        for (IlluminationObject illuminationObject : illuminationObjects
        ) {
            //Check invoeren of we met een texture zitten
            //If yes moeten we de juiste texture functie uitvoeren
            double total_local_intensity = 0;
            if(illuminationObject.get_texture().isTexture()){
                double x = P.get_X();
                double y = P.get_Y();
                double z = P.get_Z();
                double texture_param = illuminationObject.get_texture().texture(x,y,z);
                double total_diffuse_coeff = illuminationObject.get_diffuse_reflection_coeff() * total_lambert;
                double total_specular_coeff = illuminationObject.get_specular_reflection_coeff() * Math.pow(total_phong, illuminationObject.get_fallof());
                double total_ambient_coeff = illuminationObject.get_ambient_reflection_coeff() * total_ambient;
                total_local_intensity = texture_param * ( total_ambient_coeff + total_diffuse_coeff ) + total_specular_coeff;
            }else {
                double total_diffuse_coeff = illuminationObject.get_diffuse_reflection_coeff() * total_lambert;
                double total_specular_coeff = illuminationObject.get_specular_reflection_coeff() * Math.pow(total_phong, illuminationObject.get_fallof());
                double total_ambient_coeff = illuminationObject.get_ambient_reflection_coeff() * total_ambient;
                total_local_intensity = total_diffuse_coeff + total_specular_coeff + total_ambient_coeff;
            }
            /** Nog eens navragen of dit correct is of hoe ik dit beter zou kunnen oplossen **/
            total_intensities.add(total_local_intensity);
        }
        return total_intensities;
    }

    public void add_object(Object object) {
        this.objects.add(object);
    }

    public void add_light(Light light) {
        this.lights.add(light);
    }
}
