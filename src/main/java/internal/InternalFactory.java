package internal;

import config.Config;
import world.Camera;

public class InternalFactory {
    InternalTransformer internalTransformer;
    public InternalFactory() {
        internalTransformer = new InternalTransformer();
    }
    //creates ray from the eye of the camera and
    public Ray createRay(Camera camera, int col, int row){
        //p615 12.6
        //dir_rc_n = -N*n
        Vector dir_rc_n = internalTransformer.vector_product(camera.get_n(), -camera.get_N());
        //dir_rc_u = W*(2c/nCols - 1)*u
        //uc = W*(2c/nCols - 1)
        //nCols = screen width
        double uc = (double) camera.get_w() * ( (2*(double)col/(double)Config.SCREEN_WIDTH) - 1);
        Vector dir_rc_u = internalTransformer.vector_product(camera.get_u(), uc);
        //dir_rc_v = H*(2r/nRows - 1)*v
        //vc = H*(2r/nRows - 1)
        //nRows = screen height
        double vc = (double) camera.get_h() * ( (2*(double)row/(double)Config.SCREEN_HEIGHT) - 1);
        Vector dir_rc_v = internalTransformer.vector_product(camera.get_v(), vc);

        //create dir vector (som van alle dir rc vectoren)
        Vector dir_rc = internalTransformer.tripleVectorSum(dir_rc_n, dir_rc_u, dir_rc_v);
//        create ray met camera eye en dir vector
//        System.out.println(camera.get_eye());
//        System.out.println(dir_rc.get_X() + " " + dir_rc.get_Y() + " " + dir_rc.get_Z());
        return new Ray(camera.get_eye(), dir_rc);
    }
}
