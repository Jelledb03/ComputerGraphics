package world;

import internal.Point;

public class Light {
    private Point lightPoint;
    private double light_source_intensity;

    public Light(Point lightPoint, double light_source_intensity) {
        this.lightPoint = lightPoint;
        this.light_source_intensity = light_source_intensity;
    }

    public Point getLightPoint() {
        return lightPoint;
    }

    public void setLightPoint(Point lightPoint) {
        this.lightPoint = lightPoint;
    }

    public double getLight_source_intensity() {
        return light_source_intensity;
    }

    public void setLight_source_intensity(double light_source_intensity) {
        this.light_source_intensity = light_source_intensity;
    }
}
