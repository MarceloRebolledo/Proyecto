package Modelos.Ingredientes;

public class Fruta extends Ingrediente{
    private String color;
    private boolean citrica;

    public Fruta() {

    }

    public Fruta(String nombre, String descripcion, String medida, String color, boolean citrica) {
        super(nombre, descripcion, medida);
        this.color = color;
        this.citrica = citrica;
        this.setTipo(this.getClass().getSimpleName());
    }

    public String getColor() {
        return color;
    }

    public boolean isCitrica() {
        return citrica;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCitrica(boolean citrica) {
        this.citrica = citrica;
    }

    @Override
    public String toString() {
        return "Fruta{" +
                "color='" + color + '\'' +
                ", citrica=" + citrica +
                "} " + super.toString();
    }
}
