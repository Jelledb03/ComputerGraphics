package config;

import java.awt.*;

public class Config {
    //hoogte en breedte van het scherm wordt hier gedefinieerd
    public static int SCREEN_WIDTH = 1920;
    public static int SCREEN_HEIGHT = 1080;

    public static int MAX_ITERATION = 0;

    public static Color DEFAULT_BACKGROUND_COLOR = Color.pink;
    public static Color DEFAULT_OBJECT_COLOR = Color.WHITE;
    public static Color WOODCOLOR = new Color(156,124,86);
    public static Color SKYCOLOR = new Color(74, 112, 139);
    public static Color BRICKCOLOR = new Color(138, 51, 53);
    public static Color DOVEWHITE = new Color(203, 194, 190);
    public static Color GRASSCOLOR = new Color(58, 89, 39);
    public static Color MOONCOLOR = new Color(245, 243, 206);
    public static Color BENCHCOLOR = new Color(139, 231, 139);


    public static double DEFAULT_DIFFUSE_REFLECTION_COEFF = 0.2775;
    public static double DEFAULT_FALLOF = 89.6;
    public static double DEFAULT_SPECULAR_REFLECTION_COEFF = 0.773911;
    public static double DEFAULT_AMBIENT_REFLECTION_COEFF = 0.23125;

    public static double DEFAULT_LOCAL_COEFF = 1;
    public static double DEFAULT_REFLECTION_COEFF = 0;
    public static double DEFAULT_REFRACTION_COEFF = 0;

    //Refraction Coefficients
    public static double DEFAULT_AIR_SPEED = 0.9997;
    public static double DEFAULT_GLASS_SPEED = 0.555;
    public static double DEFAULT_WATER_SPEED = 0.7519;
    public static double DEFAULT_SUGAR_SPEED = 0.7246;
    public static double DEFAULT_SODIUM_CHLORIDE_SPEED = 0.6493;
    public static double DEFAULT_DIAMOND_SPEED = 0.4133;
}
