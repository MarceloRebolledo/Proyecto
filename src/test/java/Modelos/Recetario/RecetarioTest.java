package Modelos.Recetario;

import Firebase.CRUDFirebase;
import Modelos.Ingredientes.Ingrediente;
import Modelos.Recetas.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecetarioTest {

    private Recetario recetario;

    @Mock
    private CRUDFirebase mockDb;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recetario = new Recetario();
        recetario.db = mockDb;
    }

    @Test
    public void testAgregarIngredienteRegistro() {
        Ingrediente ingrediente = new Ingrediente("Sal", "Condimento", "0.1grs");

        when(mockDb.addFirebase(ingrediente)).thenReturn(true);
        when(mockDb.obtenerFirebaseIngredientes(any())).thenReturn(true);

        assertTrue(recetario.agregarIngredienteRegistro(ingrediente));
        assertTrue(recetario.getIngredientesRegistrados().contains(ingrediente));
    }

    @Test
    public void testEliminarReceta() {
        Receta receta = new Receta("Tortilla de patatas", Arrays.asList(new Ingrediente("Patatas", "Verdura", "0.2")));

        when(mockDb.delFirebase(receta)).thenReturn(true);
        when(mockDb.obtenerFirebaseRecetas(any())).thenReturn(true);

        recetario.agregarRecetaRegistro(receta);

        assertTrue(recetario.eliminarReceta(receta));
        assertFalse(recetario.getRecetasRegistradas().contains(receta));
    }

    @Test
    public void testPreparables() {
        Ingrediente ingrediente1 = new Ingrediente("Harina", "Base", "0.3");
        Ingrediente ingrediente2 = new Ingrediente("Azúcar", "Dulce", "0.2");

        Receta receta1 = new Receta("Tarta", Arrays.asList(ingrediente1, ingrediente2));
        Receta receta2 = new Receta("Galletas", Arrays.asList(ingrediente1));

        recetario.agregarIngredienteDisponible(ingrediente1);
        recetario.agregarIngredienteDisponible(ingrediente2);

        recetario.agregarRecetaRegistro(receta1);
        recetario.agregarRecetaRegistro(receta2);

        ArrayList<Receta> preparables = recetario.preparables();

        assertTrue(preparables.contains(receta2));
        assertFalse(preparables.contains(receta1));
    }
}
