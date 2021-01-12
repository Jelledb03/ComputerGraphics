package internal;

import java.util.ArrayList;
import java.util.List;

public class Intersection {
    private List<HitObject> hit_objects;
    private List<Double> hit_times;
    private int hits;
    private int lowest_hit_time_index;

    public Intersection() {
        this.hit_objects = new ArrayList<>();
        this.hit_times = new ArrayList<>();
        this.hits = 0;
        lowest_hit_time_index = 0;
    }

    public List<HitObject> get_hitobjects() {
        return hit_objects;
    }

    public void set_hitobjects(List<HitObject> hitobjects) {
        this.hit_objects = hitobjects;
    }

    public List<Double> getHit_times() {
        return hit_times;
    }

    public void setHit_times(List<Double> hit_times) {
        this.hit_times = hit_times;
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
