package render;

import config.Config;
import internal.HitObject;
import factory.ObjectFactory;
import internal.Ray;
import shapes.BooleanObject;
import world.World;

import javax.swing.*;
import java.util.ArrayList;

//Gaat voor elke pixel een ray creeren en zoeken voor een hitobject
public class Renderer {
    private Screen screen;
    JFrame jFrame;

    //Verschil JFrame en JPanel
    //Uw JFrame gaat uw volledige "game/scherm" laten zien. Dus ook de rand rond uw 2D wereld
    //Uw JPanel gaat enkel de 2D wereld (WIDTH x HEIGHT pixels) bevatten en deze wordt toegevoegd aan de JFrame
    public Renderer() {
        this.screen = new Screen(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.jFrame = new JFrame("Computer Graphics");
        this.jFrame.setSize(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        this.jFrame.add(screen);
        this.jFrame.setVisible(true);
    }

    public void render_pixel(Pixel pixel){
        screen.render_pixel(pixel);
    }

    //Voor elke pixel in het 2D screen gaan we een ray creeren vanuit de camera
    //Nadat alle berekening gedaan zijn moet het scherm gerepaint worden
    public void render_screen(World world){
        ObjectFactory objectFactory = new ObjectFactory();
        PixelFactory pixelFactory = new PixelFactory();
        int counter= 0;
        int counter_cap=0;
        for (int col = 0; col < Config.SCREEN_WIDTH; col++) {
            for (int row = 0; row < Config.SCREEN_HEIGHT; row++) {
                //Step 1
                Ray ray = objectFactory.create_ray(world.get_camera(), Config.DEFAULT_AIR_SPEED, col,row);
                //Iterator will help me to keep calling calculateClosestHitObject for a number of reflections (only call it later on if iterator < 5)
                int iterator = 0;
                //Start of step 2
                HitObject hitObject = world.calculateClosestHitObject(ray, iterator);
                //Clean hit times of the boolean objects
                for(BooleanObject booleanObject: world.get_booleanObjects()){
                    booleanObject.set_hit_times(new ArrayList<>());
                }
                if(hitObject.is_collided()) {
                    counter++;
                    /*
                    if (hitObject.get_normal_vector().get_X() == 0 && hitObject.get_normal_vector().get_Y() == 0 && hitObject.get_normal_vector().get_Z() == 1) {
                        counter_cap++;
                    }*/
                    //System.out.println(hitObject.get_intensity());
                }
                Pixel pixel = pixelFactory.createPixel(col, row, hitObject);
                //Step 6
                screen.render_pixel(pixel);
            }
        }
        System.out.println(counter);
        //System.out.println("counter cap: " + counter_cap);
        //After rendering all the pixels to the screen, repaint the screen
        screen.repaint();
    }
}
