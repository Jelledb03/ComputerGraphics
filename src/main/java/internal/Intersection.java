package internal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Intersection {
    private List<HitObject> hitobjects;
    private int hits;
    private int lowest_hit_time_index;

    public Intersection() {
        this.hitobjects = new ArrayList<>();
        this.hits = 0;
        lowest_hit_time_index = 0;
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

    public int get_lowest_hit_time_index() {
        return lowest_hit_time_index;
    }

    public void set_lowest_hit_time_index(int lowest_hit_time_index) {
        this.lowest_hit_time_index = lowest_hit_time_index;
    }
}
