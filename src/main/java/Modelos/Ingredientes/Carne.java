package Modelos.Ingredientes;

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
    public String toString() {
        return "Carne{" +
                "animal='" + animal + '\'' +
                "} " + super.toString();
    }
}
