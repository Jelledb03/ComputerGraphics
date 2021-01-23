package internal;

public class Hit {
    private double t_hit;
    private boolean isEntering;

    public Hit(double t_hit, boolean isEntering) {
        this.t_hit = t_hit;
        this.isEntering = isEntering;
    }

    public double get_t_hit() {
        return t_hit;
    }

    public void set_t_hit(double t_hit) {
        this.t_hit = t_hit;
    }

    public boolean isEntering() {
        return isEntering;
    }

    public void setEntering(boolean entering) {
        isEntering = entering;
    }
}
