package rendering2D;

import config.Config;
import internal.HitObject;

import java.awt.*;

public class PixelFactory {
    public PixelFactory() {
    }

    public Pixel createPixel(int x, int y, HitObject hitObject){
        if(hitObject.is_collided()){
            //Draws object
            Color hit_object_color = hitObject.get_color();
            int r = (int) (hit_object_color.getRed() * hitObject.get_r_illuminationObject().get_intensity());
            int g = (int) (hit_object_color.getGreen() * hitObject.get_g_illuminationObject().get_intensity());
            int b = (int) (hit_object_color.getBlue() * hitObject.get_b_illuminationObject().get_intensity());
            Color color = new Color(r,g,b);
            return new Pixel(x, y, color);
        }else{
            //Draws background
            // background color andere kleur, wordt oftewel wit of zwart
            return new Pixel(x, y, Config.DEFAULT_BACKGROUND_COLOR);
        }
    }
}
