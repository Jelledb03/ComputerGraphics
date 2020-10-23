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

    public HitObject calculateClosestHitObject(Ray ray){
        //Hierin gaan we de dichstbijzijnde hitobject vinden en teruggeven
        //Zullen met een ray over alle objecten in de list gaan en elke keer de hittime nakijken en het hitobject met kleinste hittime bijhouden
        return null;
    }
}
