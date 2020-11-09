import internal.Matrix;
import internal.Point;
import factory.Matrix2DFactory;
import internal.MatrixTransformer;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI extends Frame {

    public GUI(int width, int height){
        super("Computer Graphics Demo");
        prepareGUI(width, height);
    }

    private void prepareGUI(int width, int height){
        setSize(width,height);
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

        //Do we have to include all the code in here??
        /*
        int x = 50;
        int y = 50;
        drawPoint(g2, x, y);
        x = 100;
        y = 50;
        drawPoint(g2, x, y);
        */
        Matrix2DFactory matrixFactory = new Matrix2DFactory();
        MatrixTransformer matrixTransformer = new MatrixTransformer();
        //internal.Matrix transformer test with internal.Point and MatrixFactory
        //PointA coordinates
        double x = 100;
        double y = 100;

        //Transformation Parameters
        double m13 = 0;
        double m23 = 50;

        internal.Point pointA = new internal.Point(x,y);
        Matrix translationMatrix = matrixFactory.create_transl_matrix(m13, m23);

        double[][] transformedMatrix = matrixTransformer.multiplyMatrices(translationMatrix.get_matrix(), pointA.get_point());
        Point pointB = new internal.Point(transformedMatrix);
        //System.out.println(pointB.getX() + " " + pointB.getY());

        drawPoint(g2, pointA.get_X(), pointA.get_Y());
        drawPoint(g2, pointB.get_X(), pointB.get_Y());

    }

    public void drawPoint(Graphics2D g2, double x, double y){
        int draw_x = (int) x;
        int draw_y = (int) y;
        g2.drawLine(draw_x, draw_y, draw_x, draw_y);
    }
}