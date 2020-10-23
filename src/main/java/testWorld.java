import internal.InternalFactory;
import internal.InternalTransformer;
import internal.Point;
import internal.Ray;
import world.Camera;

public class testWorld {
    public static void main(String[] args) {
        InternalFactory internalFactory = new InternalFactory();
        Camera camera = new Camera();
        camera.set_N(5);
        Ray ray = internalFactory.createRay(camera, 25, 25);
    }
}
