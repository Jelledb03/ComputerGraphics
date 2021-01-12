package internal;

import java.util.List;

public class Intersection {
    private List<HitObject> hitobjects;
    private int hits;

    public Intersection(List<HitObject> hitobjects, int hits) {
        this.hitobjects = hitobjects;
        this.hits = hits;
    }

    public List<HitObject> get_hitobjects() {
        return hitobjects;
    }

    public void set_hitobjects(List<HitObject> hitobjects) {
        this.hitobjects = hitobjects;
    }

    public int get_hits() {
        return hits;
    }

    public void set_hits(int hits) {
        this.hits = hits;
    }
}
