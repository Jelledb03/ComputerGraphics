package world;

import java.util.ArrayList;
import java.util.List;

import internal.HitObject;
import internal.Ray;
import shapes.Object;

public class World {
    private Camera camera;
    private List<Object> objects;

    public World(Camera camera, List<Object> objects) {
        this.camera = camera;
        this.objects = objects;
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
        //if (lowest_time_hitObject.is_collided()) {
            //System.out.println("creating hit objects");
            //System.out.println(lowest_time_hitObject.get_hit_time());
            //System.out.println(lowest_time_hitObject.get_hit_point().get_X() + " " + lowest_time_hitObject.get_hit_point().get_Y() + " " + lowest_time_hitObject.get_hit_point().get_Z());
        //}
        return lowest_time_hitObject;
    }

    public void add_object(Object object) {
        this.objects.add(object);
    }
}
