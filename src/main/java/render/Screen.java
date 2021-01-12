package render;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Screen extends JPanel {

    //Deze bufferedImage wordt uw screen panel met vaste hoogte en breedte
    // waar we de rgb waardes van pixels gaan van aanpassen
    private BufferedImage screen_panel;

    public Screen(int width, int height) {
        //Creert de panel en specifieert de hoogte en breedte, maar ook het type van kleur indicatie
        super(true);
        this.screen_panel = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.setSize(width, height);
    }

    //Gaan de pixel xpos, ypos en kleur opvragen en in de screen panel steken
    public void render_pixel(Pixel pixel){
        screen_panel.setRGB(pixel.getX_pos(), pixel.getY_pos(), pixel.getColor().getRGB());
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(screen_panel, null, null);
    }

}
