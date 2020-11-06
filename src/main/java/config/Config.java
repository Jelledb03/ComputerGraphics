package config;

import java.awt.*;

public class Config {
    //hoogte en breedte van het scherm wordt hier gedefinieerd
    public static int SCREEN_WIDTH = 1920;
    public static int SCREEN_HEIGHT = 1080;

    public static int MAX_ITERATION = 10;

    public static Color DEFAULT_BACKGROUND_COLOR = Color.BLUE;
    public static Color DEFAULT_OBJECT_COLOR = Color.WHITE;

    public static double DEFAULT_DIFFUSE_REFLECTION_COEFF = 0.2775;
    public static double DEFAULT_FALLOF = 89.6;
    public static double DEFAULT_SPECULAR_REFLECTION_COEFF = 0.773911;
    public static double DEFAULT_AMBIENT_REFLECTION_COEFF = 0.23125;

    public static double DEFAULT_LOCAL_COEFF = 0.5;
    public static double DEFAULT_REFLECTION_COEFF = 0.5;
    public static double DEFAULT_REFRACTION_COEFF = 0.3;
}
