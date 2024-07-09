package Firebase;

import Modelos.Ingredientes.*;
import Modelos.Recetas.Receta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class CRUDFirebase {

    private static final String nombreColeccionIngredientes = "Ingredientes";
    private static final String nombreColeccionRecetas = "Recetas";

    static Firestore db = null;

    public CRUDFirebase() {
        ConexionFirebase conexionFirebase = new ConexionFirebase();
        db = conexionFirebase.iniciarFirebase();
    }

    private boolean flag;

    public boolean addFirebase(Ingrediente ingrediente) {

        flag = false;

        DocumentReference docRef = db.collection(nombreColeccionIngredientes)
                .document(ingrediente.getNombre());

        try {
            DocumentSnapshot documentSnapshot = docRef.get().get();
            if (documentSnapshot.exists()) {
                System.out.println("ID de ingrediente ya existe");
                return flag;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ApiFuture<WriteResult> future = db.collection(nombreColeccionIngredientes)
                .document(ingrediente.getNombre())
                .set(ingrediente);

        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
            flag = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return flag;

    }

    public boolean addFirebase(Receta receta) {

        flag = false;

        DocumentReference docRef = db.collection(nombreColeccionRecetas)
                .document(receta.getNombre());

        try {
            DocumentSnapshot documentSnapshot = docRef.get().get();
            if (documentSnapshot.exists()) {
                System.out.println("ID de receta ya existe");
                return flag;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ApiFuture<WriteResult> future = db.collection(nombreColeccionRecetas)
                .document(receta.getNombre())
                .set(receta);

        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
            flag = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return flag;

    }

    public boolean obtenerFirebaseIngredientes(ArrayList<Ingrediente> ingredientes) {

        flag = false;

        ApiFuture<QuerySnapshot> future = db.collection(nombreColeccionIngredientes).get();
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                if(Objects.equals(document.get("tipo"), "Carne")) {
                    ingredientes.add(document.toObject(Carne.class));
                } else if (Objects.equals(document.get("tipo"), "Liquido")) {
                    ingredientes.add(document.toObject(Liquido.class));
                } else if (Objects.equals(document.get("tipo"), "Verdura")) {
                    ingredientes.add(document.toObject(Verdura.class));
                } else if (Objects.equals(document.get("tipo"), "Masa")) {
                    ingredientes.add(document.toObject(Masa.class));
                } else if (Objects.equals(document.get("tipo"), "Fruta")) {
                    ingredientes.add(document.toObject(Fruta.class));
                }
            }
            flag = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return flag;

    }

    public boolean obtenerFirebaseRecetas(ArrayList<Receta> recetas) {

        flag = false;

        ApiFuture<QuerySnapshot> future = db.collection(nombreColeccionRecetas).get();

        List<QueryDocumentSnapshot> documents;

        try {

            documents = future.get().getDocuments();

            for (QueryDocumentSnapshot document : documents) {

                Receta receta = new Receta(
                        document.get("nombre").toString(),
                        document.get("descripcion").toString(),
                        document.get("instrucciones").toString()
                );



                List<Map<String, Object>> ingredienteMaps = (List<Map<String, Object>>) document.get("ingredientes");

                for (Map<String, Object> ingredienteMap : ingredienteMaps) {

                    ObjectMapper objectMapper = new ObjectMapper();

                    if (ingredienteMap.get("tipo").equals("Carne")) {

                        Ingrediente ingrediente = objectMapper.convertValue(ingredienteMap, Carne.class);

                        receta.agregarIngrediente(ingrediente);
                    }

                    else if (ingredienteMap.get("tipo").equals("Liquido")) {

                        Ingrediente ingrediente = objectMapper.convertValue(ingredienteMap, Liquido.class);

                        receta.agregarIngrediente(ingrediente);

                    } else if (ingredienteMap.get("tipo").equals("Verdura")) {

                        Ingrediente ingrediente = objectMapper.convertValue(ingredienteMap, Verdura.class);

                        receta.agregarIngrediente(ingrediente);

                    } else if (ingredienteMap.get("tipo").equals("Masa")) {

                        Ingrediente ingrediente = objectMapper.convertValue(ingredienteMap, Masa.class);

                        receta.agregarIngrediente(ingrediente);

                    } else if (ingredienteMap.get("tipo").equals("Fruta")) {

                        Ingrediente ingrediente = objectMapper.convertValue(ingredienteMap, Fruta.class);

                        receta.agregarIngrediente(ingrediente);

                    }

                }

                recetas.add(receta);

            }

            flag = true;

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return flag;

    }



    public boolean delFirebase(Ingrediente ingrediente) {

        flag = false;

        DocumentReference docRef = db.collection(nombreColeccionIngredientes)
                .document(ingrediente.getNombre());

        try {
            DocumentSnapshot documentSnapshot = docRef.get().get();
            if (!documentSnapshot.exists()) {
                System.out.println("ID de ingrediente no existe");
                return flag;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ApiFuture<WriteResult> future = db.collection(nombreColeccionIngredientes)
                .document(ingrediente.getNombre())
                .delete();

        try {
            System.out.println("Delete time : " + future.get().getUpdateTime());
            flag = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return flag;

    }

    public boolean delFirebase(Receta receta) {

        flag = false;

        DocumentReference docRef = db.collection(nombreColeccionRecetas)
                .document(receta.getNombre());

        try {
            DocumentSnapshot documentSnapshot = docRef.get().get();
            if (!documentSnapshot.exists()) {
                System.out.println("ID de receta no existe");
                return flag;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ApiFuture<WriteResult> future = db.collection(nombreColeccionRecetas)
                .document(receta.getNombre())
                .delete();

        try {
            System.out.println("Delete time : " + future.get().getUpdateTime());
            flag = true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return flag;

    }
}