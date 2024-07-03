package Modelos.Ingredientes;

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
    public String toString() {
        return "Verdura{" +
                "tipo='" + tipoVerdura + '\'' +
                "} " + super.toString();
    }
}
