import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawDemo extends Frame {

    public DrawDemo(){
        super("Computer Graphics Demo");
        prepareGUI();
    }

    public static void main(String[] args){
        DrawDemo drawDemo = new DrawDemo();
        drawDemo.setVisible(true);
    }

    private void prepareGUI(){
        setSize(500,500);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int x = 50;
        int y = 50;
        drawPoint(g2, x, y);
        x = 70;
        y = 80;
        drawPoint(g2, x, y);
    }

    public void drawPoint(Graphics2D g2, int x, int y){
        g2.drawLine(x, y, x, y);
    }
}