package Swing.Receta;

import Modelos.Recetas.Receta;

import javax.swing.*;
import java.awt.*;

public class MostrarDatosRecetaFrame extends JFrame {
    public MostrarDatosRecetaFrame(Receta receta) {
        setTitle("Receta: " + receta.getNombre());
        setSize(400, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // JTextArea para el nombre
        JTextArea nombreTextArea = new JTextArea();
        nombreTextArea.setEditable(false);
        nombreTextArea.setLineWrap(true);
        nombreTextArea.setWrapStyleWord(true);
        nombreTextArea.setText(receta.getNombre());

        // JTextArea para la descripción
        JTextArea descripcionTextArea = new JTextArea();
        descripcionTextArea.setEditable(false);
        descripcionTextArea.setLineWrap(true);
        descripcionTextArea.setWrapStyleWord(true);
        descripcionTextArea.setText(receta.getDescripcion());

        // JTextArea para las instrucciones
        JTextArea instruccionesTextArea = new JTextArea();
        instruccionesTextArea.setEditable(false);
        instruccionesTextArea.setLineWrap(true);
        instruccionesTextArea.setWrapStyleWord(true);
        instruccionesTextArea.setText(receta.getInstrucciones());

        // Panel para los detalles usando BoxLayout vertical
        JPanel detallesPanel = new JPanel();
        detallesPanel.setLayout(new BoxLayout(detallesPanel, BoxLayout.Y_AXIS));
        detallesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        detallesPanel.add(new JLabel("Nombre:"));
        detallesPanel.add(new JScrollPane(nombreTextArea));
        detallesPanel.add(new JLabel("Descripcion:"));
        detallesPanel.add(new JScrollPane(descripcionTextArea));
        detallesPanel.add(new JLabel("Instrucciones:"));
        detallesPanel.add(new JScrollPane(instruccionesTextArea));

        // Panel para los ingredientes usando BoxLayout vertical
        JPanel ingredientesPanel = new JPanel();
        ingredientesPanel.setLayout(new BoxLayout(ingredientesPanel, BoxLayout.Y_AXIS));
        ingredientesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelIngredientes = new JLabel("Ingredientes: ");
        labelIngredientes.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredientesPanel.add(labelIngredientes);
        ingredientesPanel.add(Box.createVerticalStrut(5)); // Espacio entre el label y la lista de ingredientes

        for (int i = 0; i < receta.getIngredientes().size(); i++) {
            ingredientesPanel.add(new JLabel(receta.getIngredientes().get(i).getNombre()));
        }

        // Agregar componentes al panel principal
        panel.add(detallesPanel);
        panel.add(ingredientesPanel);

        // Agregar panel principal al JFrame
        add(panel);

        // Hacer visible el JFrame
        setVisible(true);
    }
}