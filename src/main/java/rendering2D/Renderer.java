package rendering2D;

import config.Config;
import internal.HitObject;
import internal.InternalFactory;
import internal.Ray;
import world.World;

import javax.swing.*;

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
        InternalFactory internalFactory = new InternalFactory();
        PixelFactory pixelFactory = new PixelFactory();
        int counter= 0;
        int counter_cap=0;
        for (int col = 0; col < Config.SCREEN_WIDTH; col++) {
            for (int row = 0; row < Config.SCREEN_HEIGHT; row++) {
                Ray ray = internalFactory.createRay(world.getCamera(),col,row);
                HitObject hitObject = world.calculateClosestHitObject(ray);
                if(hitObject.is_collided()) {
                    counter++;
                    if (hitObject.get_normal_vector().get_X() == 0 && hitObject.get_normal_vector().get_Y() == 0 && hitObject.get_normal_vector().get_Z() == 1) {
                        counter_cap++;
                    }
                    //System.out.println(hitObject.get_intensity());
                }
                Pixel pixel = pixelFactory.createPixel(col, row, hitObject);
                screen.render_pixel(pixel);
            }
        }
        System.out.println(counter);
        System.out.println("counter cap: " + counter_cap);
        //After rendering all the pixels to the screen, repaint the screen
        screen.repaint();
    }
}
