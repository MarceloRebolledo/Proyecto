package Modelos.Ingredientes;

import java.util.Objects;

public class Verdura extends Ingrediente{
    private String tipoVerdura;

    public Verdura() {

    }

    public Verdura(String nombre, String descripcion, String medida, String tipoVerdura) {
        super(nombre, descripcion, medida);
        this.tipoVerdura = tipoVerdura;
        this.setTipo(this.getClass().getSimpleName());
    }

    public void setTipoVerdura(String tipoVerdura) {
        this.tipoVerdura = tipoVerdura;
    }

    public String getTipoVerdura() {
        return tipoVerdura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Verdura verdura = (Verdura) o;
        return Objects.equals(tipoVerdura, verdura.tipoVerdura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipoVerdura);
    }

    @Override
    public String toString() {
        return "Verdura{" +
                "tipo='" + tipoVerdura + '\'' +
                "} " + super.toString();
    }
}
