package Modelos.Ingredientes;

import java.util.Objects;

public class Masa extends Ingrediente{

    private boolean integral;
    private String tipoMasa;

    public Masa() {

    }

    public Masa(String nombre, String descripcion, String medida, boolean integral, String tipoMasa) {
        super(nombre, descripcion, medida);
        this.integral = integral;
        this.tipoMasa = tipoMasa;
        this.setTipo(this.getClass().getSimpleName());
    }

    public boolean isIntegral() {
        return integral;
    }

    public String getTipoMasa() {
        return tipoMasa;
    }

    public void setIntegral(boolean integral) {
        this.integral = integral;
    }

    public void setTipoMasa(String tipoMasa) {
        this.tipoMasa = tipoMasa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Masa masa = (Masa) o;
        return integral == masa.integral && Objects.equals(tipoMasa, masa.tipoMasa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), integral, tipoMasa);
    }

    @Override
    public String toString() {
        return "Masa{" +
                "integral=" + integral +
                ", tipoMasa='" + tipoMasa + '\'' +
                "} " + super.toString();
    }
}
