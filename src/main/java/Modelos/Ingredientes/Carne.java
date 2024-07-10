package Modelos.Ingredientes;

import java.util.Objects;

public class Carne extends Ingrediente{

    private String animal;

    public Carne(){

    }

    public Carne(String nombre, String descripcion, String medida,String animal) {
        super(nombre, descripcion, medida);
        this.animal = animal.toLowerCase();
        this.setTipo(this.getClass().getSimpleName());
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Carne carne = (Carne) o;
        return Objects.equals(animal, carne.animal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), animal);
    }

    @Override
    public String toString() {
        return "Carne{" +
                "animal='" + animal + '\'' +
                "} " + super.toString();
    }
}
