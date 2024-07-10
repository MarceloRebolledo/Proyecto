package Modelos.Recetario;

import Modelos.Ingredientes.Carne;
import Modelos.Ingredientes.Ingrediente;
import Modelos.Recetas.Receta;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RecetarioTest {

    private Recetario recetario;
    private Ingrediente ingrediente1;
    private Ingrediente ingrediente2;
    private Receta receta;

    @BeforeEach
    void setUp() {
        recetario = new Recetario();

        ingrediente1 = new Carne("IngredienteTest1","","","");
        ingrediente2 = new Carne("IngredienteTest2","","","");

        receta = new Receta("RecetaTest","","");
    }

    @AfterEach
    void tearDown() {
        this.recetario = null;

        this.ingrediente1 = null;
        this.ingrediente2 = null;

        this.receta = null;
    }

    @Test
    void testAgregarIngredienteRegistro() {
        boolean accionRealizada = recetario.agregarIngredienteRegistro(ingrediente1);
        recetario.eliminarIngredienteRegistrado(ingrediente1);

        assertTrue(accionRealizada);
    }

    @Test
    void testEliminarIngredienteRegistroSiExiste() {
        recetario.agregarIngredienteRegistro(ingrediente1);
        boolean accionRealizada = recetario.eliminarIngredienteRegistrado(ingrediente1);

        assertTrue(accionRealizada);
    }

    @Test
    void testEliminarIngredienteRegistroNoExiste() {
        boolean accionRealizada = recetario.eliminarIngredienteRegistrado(ingrediente1);

        assertFalse(accionRealizada);
    }

    @Test
    void testAgregarRecetaRegistro() {
        boolean accionRealizada = recetario.agregarRecetaRegistro(receta);
        recetario.eliminarRecetaRegistrada(receta);

        assertTrue(accionRealizada);
    }

    @Test
    void testEliminarRecetaRegistro() {
        recetario.agregarRecetaRegistro(receta);
        boolean accionRealizada = recetario.eliminarRecetaRegistrada(receta);

        assertTrue(accionRealizada);
    }

    @Test
    void testGetRecetaPorNombreSiExiste() {
        recetario.agregarRecetaRegistro(receta);

        String nombreReceta = receta.getNombre();
        Receta recetaBuscada = recetario.getRecetaPorNombre(nombreReceta);

        recetario.eliminarRecetaRegistrada(receta);

        assertEquals(receta, recetaBuscada);
    }

    @Test
    void testGetRecetaPorNombreNoExiste() {
        String nombreReceta = receta.getNombre();
        Receta recetaBuscada = recetario.getRecetaPorNombre(nombreReceta);

        assertNull(recetaBuscada);
    }

    @Test
    void testGetIngredientesPorNombreSiExiste() {
        recetario.agregarIngredienteRegistro(ingrediente1);

        String nombreIngrediente1 = ingrediente1.getNombre();
        Ingrediente ingredienteBuscado = recetario.getIngredientePorNombre(nombreIngrediente1);

        recetario.eliminarIngredienteRegistrado(ingrediente1);

        assertEquals(ingrediente1, ingredienteBuscado);
    }

    @Test
    void testGetIngredientesPorNombreNoExiste() {
        String nombreIngrediente1 = ingrediente1.getNombre();
        Ingrediente ingredienteBuscado = recetario.getIngredientePorNombre(nombreIngrediente1);

        recetario.eliminarIngredienteRegistrado(ingrediente1);

        assertNull(ingredienteBuscado);
    }

    @Test
    void testLimpiarIngredientesDisponibles() {
        recetario.agregarIngredienteDisponible(ingrediente1);
        recetario.agregarIngredienteDisponible(ingrediente2);

        recetario.limpiarIngredientesDisponibles();

        boolean recetarioContieneIngredientesDisponibles = recetario.getIngredientesDisponibles().isEmpty();

        assertTrue(recetarioContieneIngredientesDisponibles);
    }

    @Test
    void testAgregarIngredienteDisponible() {
        boolean resultadoAccion = recetario.agregarIngredienteDisponible(ingrediente1);

        assertTrue(resultadoAccion);
    }

    @Test void testEliminarIngredienteDisponible() {
        recetario.agregarIngredienteDisponible(ingrediente1);
        recetario.eliminarIngredienteDisponible(ingrediente1);
        boolean contieneIngrediente = recetario.getIngredientesDisponibles().contains(ingrediente1);

        assertFalse(contieneIngrediente);
    }

    @Test
    void testPreparables() {
        receta.agregarIngrediente(ingrediente1);
        recetario.agregarIngredienteDisponible(ingrediente1);

        recetario.agregarRecetaRegistro(receta);

        ArrayList<Receta> preparables = recetario.preparables();

        recetario.eliminarRecetaRegistrada(receta);

        boolean contieneReceta = preparables.contains(receta);

        assertTrue(contieneReceta);
    }
}