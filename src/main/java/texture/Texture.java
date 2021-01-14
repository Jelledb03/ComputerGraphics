package texture;

public abstract class Texture {
    private boolean texture;

    public Texture(boolean texture) {
        this.texture = texture;
    }

    public boolean isTexture() {
        return texture;
    }

    public void setTexture(boolean texture) {
        this.texture = texture;
    }

    public abstract double texture(double x, double y, double z);
}
