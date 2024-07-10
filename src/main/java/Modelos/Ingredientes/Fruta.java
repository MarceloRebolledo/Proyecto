package Modelos.Ingredientes;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Fruta fruta = (Fruta) o;
        return citrica == fruta.citrica && Objects.equals(color, fruta.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, citrica);
    }

    @Override
    public String toString() {
        return "Fruta{" +
                "color='" + color + '\'' +
                ", citrica=" + citrica +
                "} " + super.toString();
    }
}
