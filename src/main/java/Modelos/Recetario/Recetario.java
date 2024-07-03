package Modelos.Recetario;

import Firebase.CRUDFirebase;
import Interfazes.Preparables;
import Modelos.Ingredientes.Ingrediente;
import Modelos.Recetas.Receta;

import java.util.ArrayList;

public class Recetario implements Preparables {

    private CRUDFirebase db = new CRUDFirebase();

    private ArrayList<Ingrediente> ingredientesDisponibles;
    private ArrayList<Ingrediente> ingredientesRegistrados;
    private ArrayList<Receta> recetasRegistradas;

    public Recetario() {
        this.ingredientesDisponibles = new ArrayList<>();
        this.ingredientesRegistrados = new ArrayList<>();
        this.recetasRegistradas = new ArrayList<>();
    }

    private boolean flag;

    public ArrayList<Ingrediente> getIngredientesDisponibles() {
        return ingredientesDisponibles;
    }

    public ArrayList<Ingrediente> getIngredientesRegistrados() {
        return ingredientesRegistrados;
    }

    public ArrayList<Receta> getRecetasRegistradas() {
        return recetasRegistradas;
    }

    public boolean actualizarRegistro() {
        flag = false;

        this.ingredientesRegistrados.clear();
        this.recetasRegistradas.clear();

        if (db.obtenerFirebaseIngredientes(this.ingredientesRegistrados) && db.obtenerFirebaseRecetas(this.recetasRegistradas)) {
            System.out.println("Registro actualizado");
            flag = true;
        } else {
            System.out.println("No se a podido actualizar el registro");
        }
        return flag;
    }

    public Receta getRecetaPorNombre(String nombreRecetaBuscada) {
        for (Receta receta : recetasRegistradas) {
            if (receta.getNombre().equalsIgnoreCase(nombreRecetaBuscada)) {
                return receta;
            }
        }
        return null;
    }

    public Ingrediente getIngredientePorNombre(String nombreIngredienteBuscado) {
        for (Ingrediente ingrediente : ingredientesRegistrados) {
            if (ingrediente.getNombre().equalsIgnoreCase(nombreIngredienteBuscado)) {
                return ingrediente;
            }
        }
        return null;
    }

    public boolean eliminarIngrediente(Ingrediente ingrediente)    {

        flag = false;

        for (Receta receta : recetasRegistradas) {
            if (!(receta.getIngredientes().contains(ingrediente))) {
                if(db.delFirebase(ingrediente) && actualizarRegistro()){
                    flag = true;
                    return flag;
                } else {
                    System.out.println("No se a podido eliminar el ingrediente");
                }
            }
        }

        return flag;
    }

    public boolean eliminarReceta(Receta recetaEliminar) {
        flag = false;

        if (db.delFirebase(recetaEliminar) && actualizarRegistro()) {
            flag = true;
            return flag;
        } else {
            System.out.println("No se a podido eliminar la receta");
        }

        return flag;
    }

    public boolean agregarIngredienteDisponible(Ingrediente ingrediente) {

        flag = ingredientesDisponibles.add(ingrediente);

        return flag;
    }

    public void eliminarIngredienteDisponible(Ingrediente ingrediente) {
        ingredientesDisponibles.remove(ingrediente);
    }

    public void limpiarIngredientesDisponibles() {
        ingredientesDisponibles.clear();
    }

    public boolean agregarIngredienteRegistro(Ingrediente ingrediente) {
        flag = false;
        if(db.addFirebase(ingrediente) && actualizarRegistro()){
            flag = true;
        } else {
            System.out.println("No se a podido agregar el ingrediente");
        }
        return flag;
    }

    public boolean agregarRecetaRegistro(Receta receta) {
        flag = false;
        if(db.addFirebase(receta) && actualizarRegistro()){
            flag = true;
        } else {
            System.out.println("No se a podido agregar la receta");
        }
        return flag;
    }

    @Override
    public ArrayList<Receta> preparables() {

        ArrayList<Receta> recetasPreparables = new ArrayList<>();

        for (Receta receta : recetasRegistradas) {

            ArrayList<Ingrediente> ingredientesReceta = receta.getIngredientes();

            boolean preparable = true;

            for (Ingrediente ingrediente : ingredientesReceta) {
                if (!ingredientesDisponibles.contains(ingrediente)) {
                    preparable = false;
                    break;
                }
            }

            if (preparable) {
                recetasPreparables.add(receta);
            }
        }

        return recetasPreparables;
    }

    @Override
    public String toString() {
        return "Recetario{" +
                "ingredientesDisponibles=" + ingredientesDisponibles +
                ", ingredientesRegistrados=" + ingredientesRegistrados +
                ", recetasRegistradas=" + recetasRegistradas +
                '}';
    }
}

