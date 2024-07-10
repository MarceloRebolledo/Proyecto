package Swing.Receta;

import Modelos.Ingredientes.Ingrediente;
import Modelos.Recetario.Recetario;
import Modelos.Recetas.Receta;

import javax.swing.*;
import java.awt.*;

public class AgregarRecetaFrame extends JFrame {

    public AgregarRecetaFrame(Recetario recetario) {
        setTitle("Agregar Receta");
        setSize(400, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre:"));
        JTextArea nombreTextArea = new JTextArea(1, 20);
        nombreTextArea.setLineWrap(true);
        nombreTextArea.setWrapStyleWord(true);
        JScrollPane nombreScrollPane = new JScrollPane(nombreTextArea);
        panel.add(nombreScrollPane);

        panel.add(new JLabel("Descripcion:"));
        JTextArea descripcionTextArea = new JTextArea(2, 20);
        descripcionTextArea.setLineWrap(true);
        descripcionTextArea.setWrapStyleWord(true);
        JScrollPane descripcionScrollPane = new JScrollPane(descripcionTextArea);
        panel.add(descripcionScrollPane);

        panel.add(new JLabel("Instrucciones:"));
        JTextArea instruccionesTextArea = new JTextArea(5, 20);
        instruccionesTextArea.setLineWrap(true);
        instruccionesTextArea.setWrapStyleWord(true);
        JScrollPane instruccionesScrollPane = new JScrollPane(instruccionesTextArea);
        panel.add(instruccionesScrollPane);

        panel.add(new JLabel("Ingredientes Disponibles:"));
        DefaultListModel<String> ingredientesDisponiblesModel = new DefaultListModel<>();
        for (Ingrediente ingrediente : recetario.getIngredientesDisponibles()) {
            ingredientesDisponiblesModel.addElement(ingrediente.getNombre());
        }
        JList<String> ingredientesDisponiblesList = new JList<>(ingredientesDisponiblesModel);
        JScrollPane ingredientesScrollPane = new JScrollPane(ingredientesDisponiblesList);
        panel.add(ingredientesScrollPane);

        // Boton guardar receta
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(e -> {
            String nombre = nombreTextArea.getText().trim();
            String descripcion = descripcionTextArea.getText().trim();
            String instrucciones = instruccionesTextArea.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || instrucciones.isEmpty() || recetario.getIngredientesDisponibles().isEmpty()) {
                JOptionPane.showMessageDialog(AgregarRecetaFrame.this, "Todos los Campos Deben Estar Completos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Receta nuevaReceta = new Receta(nombre, descripcion, instrucciones);

            for (Ingrediente ingredienteDisponible:recetario.getIngredientesDisponibles()) {
                nuevaReceta.agregarIngrediente(ingredienteDisponible);
            }

            recetario.agregarRecetaRegistro(nuevaReceta);
            JOptionPane.showMessageDialog(AgregarRecetaFrame.this, "Receta Agregada con Exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        panel.add(guardarButton);

        add(panel);

        setVisible(true);
    }
}
