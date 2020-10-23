package rendering2D;

import config.Config;
import internal.HitObject;

public class PixelFactory {
    public PixelFactory() {
    }

    public Pixel createPixel(int x, int y, HitObject hitObject){
        if(hitObject.is_collided()){
            //Draws object
            return new Pixel(x, y, Config.DEFAULT_OBJECT_COLOR);
        }else{
            //Draws background
            return new Pixel(x, y, Config.DEFAULT_BACKGROUND_COLOR);
        }
    }
}
