package render;

import config.Config;

import java.awt.*;

//Een pixel wordt voorgesteld met een x en y positie
public class Pixel {
    private int x_pos;
    private int y_pos;
    private Color color;

    public Pixel() {
        this.x_pos = 0;
        this.y_pos = 0;
        this.color = Config.DEFAULT_BACKGROUND_COLOR;
    }

    public Pixel(int x_pos, int y_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.color = Config.DEFAULT_BACKGROUND_COLOR;
    }

    public Pixel(int x_pos, int y_pos, Color color) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.color = color;
    }

    public int getX_pos() {
        return x_pos;
    }

    public void setX_pos(int x_pos) {
        this.x_pos = x_pos;
    }

    public int getY_pos() {
        return y_pos;
    }

    public void setY_pos(int y_pos) {
        this.y_pos = y_pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
