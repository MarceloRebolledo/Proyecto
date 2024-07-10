package Swing.Receta;

import Modelos.Recetas.Receta;
import Modelos.Recetario.Recetario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MostrarRecetasPreparablesFrame extends JFrame {

    private JList<String> listaRecetasPreparables;
    private DefaultListModel<String> modeloRecetasPreparables;
    private Recetario recetario;

    public MostrarRecetasPreparablesFrame(Recetario recetario) {
        this.recetario = recetario;

        setTitle("Recetas Preparables");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Obtener recetas preparables
        ArrayList<Receta> recetasPreparables = recetario.preparables();

        if (recetasPreparables.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No Hay Recetas Preparables", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Crear modelo de lista para las recetas preparables
        modeloRecetasPreparables = new DefaultListModel<>();
        for (Receta receta : recetasPreparables) {
            modeloRecetasPreparables.addElement(receta.getNombre());
        }

        listaRecetasPreparables = new JList<>(modeloRecetasPreparables);
        JScrollPane scrollRecetasPreparables = new JScrollPane(listaRecetasPreparables);

        JPanel panel = new JPanel(new BorderLayout());

        panel.add(scrollRecetasPreparables, BorderLayout.CENTER);

        // Boton detalles de la receta seleccionada
        JButton botonVerDetalles = new JButton("Ver Receta");
        botonVerDetalles.addActionListener(e ->
                verDetallesRecetaSeleccionada()
        );
        panel.add(botonVerDetalles, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    private void verDetallesRecetaSeleccionada() {
        int selectedIndex = listaRecetasPreparables.getSelectedIndex();
        if (selectedIndex != -1) {
            String recetaNombre = modeloRecetasPreparables.getElementAt(selectedIndex);
            Receta receta = recetario.getRecetaPorNombre(recetaNombre);
            if (receta != null) {
                new MostrarDatosRecetaFrame(receta);
            } else {
                JOptionPane.showMessageDialog(this, "Receta No Encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una Receta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}
