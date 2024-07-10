package Modelos.Ingredientes;

import java.util.Objects;

public class Liquido extends Ingrediente{
    private boolean alcoholico;
    private String procedencia;

    public Liquido() {

    }

    public Liquido(String nombre, String descripcion, String medida, boolean alcoholico, String procedencia) {
        super(nombre, descripcion, medida);
        this.alcoholico = alcoholico;
        this.procedencia = procedencia.toLowerCase();
        this.setTipo(this.getClass().getSimpleName());
    }

    public boolean isAlcoholico() {
        return alcoholico;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setAlcoholico(boolean alcoholico) {
        this.alcoholico = alcoholico;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Liquido liquido = (Liquido) o;
        return alcoholico == liquido.alcoholico && Objects.equals(procedencia, liquido.procedencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), alcoholico, procedencia);
    }

    @Override
    public String toString() {
        return "Liquido{" +
                "alcoholico=" + alcoholico +
                ", procedencia='" + procedencia + '\'' +
                "} " + super.toString();
    }
}
