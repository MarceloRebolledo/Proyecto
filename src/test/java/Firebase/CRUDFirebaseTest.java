package Firebase;

import Modelos.Ingredientes.*;
import Modelos.Recetas.Receta;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CRUDFirebaseTest {

    @Mock
    Firestore firestore;

    @Mock
    CollectionReference collectionReference;

    @Mock
    DocumentReference documentReference;

    @Mock
    ApiFuture<WriteResult> writeResultApiFuture;

    @Mock
    DocumentSnapshot documentSnapshot;

    @Mock
    ApiFuture<DocumentSnapshot> documentSnapshotApiFuture;

    @Mock
    CRUDFirebase crudFirebase;

    @BeforeEach
    void setUp() {
        crudFirebase = new CRUDFirebase(); // Inicializa la clase que estás probando
        CRUDFirebase.db = firestore; // Inyecta el mock de Firestore en la clase que estás probando
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(firestore, collectionReference, documentReference,
                writeResultApiFuture, documentSnapshot, documentSnapshotApiFuture);
    }

    @Test
    void testExample() {
        assertNotNull(crudFirebase); // Verifica que crudFirebase no sea null
    }

    @Test
    void addFirebaseIngrediente() throws ExecutionException, InterruptedException {
        Ingrediente ingrediente = new Carne("Pollo", "Carne blanca", "200grs","Pollo");

        when(firestore.collection("Ingredientes")).thenReturn(collectionReference);
        when(collectionReference.document(ingrediente.getNombre())).thenReturn(documentReference);
        when(documentReference.get()).thenReturn(documentSnapshotApiFuture);
        when(documentSnapshotApiFuture.get()).thenReturn(documentSnapshot);
        when(documentSnapshot.exists()).thenReturn(false);
        when(documentReference.set(ingrediente)).thenReturn(writeResultApiFuture);
        when(writeResultApiFuture.get()).thenReturn(mock(WriteResult.class));

        boolean result = crudFirebase.addFirebase(ingrediente);

        assertTrue(result);
        verify(documentReference).set(ingrediente); // Verifica que se llamó a set con el ingrediente correcto
    }

    @Test
    void addFirebaseReceta() throws ExecutionException, InterruptedException {
        Receta receta = new Receta("Ensalada", "Ensalada mixta", "Mezclar ingredientes");

        when(firestore.collection("Recetas")).thenReturn(collectionReference);
        when(collectionReference.document(receta.getNombre())).thenReturn(documentReference);
        when(documentReference.get()).thenReturn(documentSnapshotApiFuture);
        when(documentSnapshotApiFuture.get()).thenReturn(documentSnapshot);
        when(documentSnapshot.exists()).thenReturn(false);
        when(documentReference.set(receta)).thenReturn(writeResultApiFuture);
        when(writeResultApiFuture.get()).thenReturn(mock(WriteResult.class));

        boolean result = crudFirebase.addFirebase(receta);

        assertTrue(result);
        verify(documentReference).set(receta);
    }


    @Test
    void delFirebaseIngrediente() throws ExecutionException, InterruptedException {
        Ingrediente ingrediente = new Carne("Pollo", "Carne blanca", "200grs","Pollo");

        when(firestore.collection("Ingredientes")).thenReturn(collectionReference);
        when(collectionReference.document(ingrediente.getNombre())).thenReturn(documentReference);
        when(documentReference.get()).thenReturn(documentSnapshotApiFuture);
        when(documentSnapshotApiFuture.get()).thenReturn(documentSnapshot);
        when(documentSnapshot.exists()).thenReturn(true);
        when(documentReference.delete()).thenReturn(writeResultApiFuture);
        when(writeResultApiFuture.get()).thenReturn(mock(WriteResult.class));

        boolean result = crudFirebase.delFirebase(ingrediente);

        assertTrue(result);
        verify(documentReference).delete();
    }

    @Test
    void delFirebaseReceta() throws ExecutionException, InterruptedException {
        Receta receta = new Receta("Ensalada", "Ensalada mixta", "Mezclar ingredientes");

        when(firestore.collection("Recetas")).thenReturn(collectionReference);
        when(collectionReference.document(receta.getNombre())).thenReturn(documentReference);
        when(documentReference.get()).thenReturn(documentSnapshotApiFuture);
        when(documentSnapshotApiFuture.get()).thenReturn(documentSnapshot);
        when(documentSnapshot.exists()).thenReturn(true);
        when(documentReference.delete()).thenReturn(writeResultApiFuture);
        when(writeResultApiFuture.get()).thenReturn(mock(WriteResult.class));

        boolean result = crudFirebase.delFirebase(receta);

        assertTrue(result);
        verify(documentReference).delete();
    }
}