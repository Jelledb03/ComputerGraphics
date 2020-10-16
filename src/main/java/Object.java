//Wanneer we dit als abstract classificieren kunnen we hier geen objecten van initialiseren
public abstract class Object {
    private Matrix transformation_matrix;
    private Matrix inverse_transformation_matrix;

    public Object(Matrix transformation_matrix, Matrix inverse_transformation_matrix) {
        this.transformation_matrix = transformation_matrix;
        this.inverse_transformation_matrix = inverse_transformation_matrix;
    }

    public Matrix get_transformation_matrix() {
        return transformation_matrix;
    }

    public void set_transformation_matrix(Matrix transformation_matrix) {
        this.transformation_matrix = transformation_matrix;
    }

    public Matrix get_inverse_transformation_matrix() {
        return inverse_transformation_matrix;
    }

    public void set_inverse_transformation_matrix(Matrix inverse_transformation_matrix) {
        this.inverse_transformation_matrix = inverse_transformation_matrix;
    }

    //Zal een hitpoint time terug geven van wanneer de ray dit object hit
    //Nadat dit voor alle objecten gebeurd is zal er moeten nagekeken worden wat de laagste hit time is en hiermee het hitpoint berekenen
    //Want in dit hitpoint zal de ray daadwerkelijk komen en raken. De andere hitpoints kunnen mogelijk zijn van objecten achter dit object (zijn we niet in geintresseerd)
    //Verder uitwerken in de overgeerfde klassen
    public abstract double hit_reg(Ray ray);
}
