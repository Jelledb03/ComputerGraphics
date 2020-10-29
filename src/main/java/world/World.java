package world;

import java.util.ArrayList;
import java.util.List;

import internal.*;
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
    }

    public World(Camera camera) {
        this.camera = camera;
        this.objects = new ArrayList<>();
    }

    public World() {
        this.camera = new Camera();
        this.objects = new ArrayList<>();
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

    public HitObject calculateClosestHitObject(Ray ray) {
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
        double diffuse_component = calculate_diffuse_component(ray.get_dir(), lowest_time_hitObject);
        //if (lowest_time_hitObject.is_collided()) {
            //System.out.println("creating hit objects");
            //System.out.println(lowest_time_hitObject.get_hit_time());
            //System.out.println(lowest_time_hitObject.get_hit_point().get_X() + " " + lowest_time_hitObject.get_hit_point().get_Y() + " " + lowest_time_hitObject.get_hit_point().get_Z());
        //}
        return lowest_time_hitObject;
    }

    public double calculate_diffuse_component(Vector ray, HitObject hitObject){
        //First we will loop through all the lights
        // It is possible that more than one light shines to the eye through an object (have to sum it)
        double total_diff_coefficient = 0;
        for(Light light: lights){
            Point L = light.getLightPoint();
            Point P = hitObject.get_hit_point();
            //Need three vectors
            //normal vector m to the surface at P
            //vector v from P to the viewer's eye
            //vector s from P to the light source
            Vector s = internalTransformer.substraction_to_vector(L, P);
            Vector v = internalTransformer.inverse_vector(ray);
            Vector m = internalTransformer.cross_product(v,s);
            // Id is independent of the angle between m and v
            // It is dependent on the orientation of the eye relative to the point source
            // cos(phi) = the dot product between normalized versions of s and m
            // Id = Is * rhod max(s*m/|s||m| , 0 )
            Vector s_norm = s.normalize();
            Vector m_norm = m.normalize();
            double dot_prod = internalTransformer.dot_product(s_norm, m_norm);
            //Have to calculate the diffuse component for this light and eye
            if(dot_prod > 0){
                double diff_coeff = light.getLight_source_intensity() * hitObject.getDiffuse_reflection_coeff() * dot_prod;
                total_diff_coefficient += diff_coeff;
            }// if dot prod is negative the eye is faced away from the light
        }
        return 0.5;
    }


    public void add_object(Object object) {
        this.objects.add(object);
    }
}
