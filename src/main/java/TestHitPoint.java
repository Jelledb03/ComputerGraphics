public class TestHitPoint {
    public static void main(String[] args) {
        //hit reg detector
        HitRegDetector hitRegDetector = new HitRegDetector();
        //Ray parameters
        Point eye = new Point(2, 2, 2);
        Vector direction = new Vector(-1, -1, -1);
        Ray ray = new Ray(eye, direction);

        //Sphere parameters
        Point sphereCenterPoint = new Point(0, 0, 0);
        double radius = 1;
        Sphere genericSphere = new Sphere(sphereCenterPoint, radius);

        double t_hit = hitRegDetector.sphere_hit_detec(ray);
        System.out.println(t_hit);

        Point hitPoint = hitRegDetector.hit_point_calculation(ray, t_hit);

        System.out.println(hitPoint.get_X() + " " + hitPoint.get_Y() + " " + hitPoint.get_Z());
    }
}
