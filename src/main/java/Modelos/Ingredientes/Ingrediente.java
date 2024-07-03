package Modelos.Ingredientes;

import java.util.Objects;

public abstract class Ingrediente {
    private String nombre;
    private String descripcion;
    private String medida;
    private String tipo;

    public Ingrediente() {

    }

    public Ingrediente(String nombre, String descripcion, String medida) {
        this.nombre = nombre.toLowerCase();
        this.descripcion = descripcion.toLowerCase();
        this.medida = medida.toLowerCase();
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMedida() {
        return medida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return Objects.equals(nombre, that.nombre) && Objects.equals(descripcion, that.descripcion) && Objects.equals(medida, that.medida) && Objects.equals(tipo, that.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, medida, tipo);
    }

    @Override
    public String toString() {
        return "Ingrediente{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", medida='" + medida + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
