package Modelos.Recetas;

import Modelos.Ingredientes.Ingrediente;

import java.util.ArrayList;
import java.util.Objects;

public class Receta {
    private String nombre;
    private String descripcion;
    private String instrucciones;
    private ArrayList<Ingrediente> ingredientes;

    public Receta() {

    }

    public Receta(String nombre, String descripcion, String instrucciones) {
        this.nombre = nombre.toLowerCase();
        this.descripcion = descripcion.toLowerCase();
        this.instrucciones = instrucciones.toLowerCase();
        this.ingredientes = new ArrayList<>();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        this.ingredientes.add(ingrediente);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receta receta = (Receta) o;
        return Objects.equals(nombre, receta.nombre) && Objects.equals(descripcion, receta.descripcion) && Objects.equals(instrucciones, receta.instrucciones) && Objects.equals(ingredientes, receta.ingredientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, descripcion, instrucciones, ingredientes);
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", instrucciones='" + instrucciones + '\'' +
                ", ingredientes=" + ingredientes +
                '}';
    }
}
