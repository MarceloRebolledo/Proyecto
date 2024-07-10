package Swing;

import Modelos.Ingredientes.Ingrediente;
import Modelos.Recetario.Recetario;
import Modelos.Recetas.Receta;
import Swing.Ingredientes.MostrarDatosIngredienteFrame;
import Swing.Ingredientes.SeleccionarTipoIngredienteFrame;
import Swing.Receta.AgregarRecetaFrame;
import Swing.Receta.MostrarDatosRecetaFrame;
import Swing.Receta.MostrarRecetasPreparablesFrame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private Recetario recetario;
    private DefaultListModel<String> listadoRecetas;
    private DefaultListModel<String> listadoIngredientes;
    private DefaultListModel<String> listadoIngredientesDisponibles;

    public MainFrame() {
        // Inicializar el recetario y actualizar el registro
        recetario = new Recetario();
        recetario.actualizarRegistro();

        setTitle("Recetario");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel Principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes de 10 píxeles en todos los lados

        // Panel componentes principales
        JPanel gridPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes de 10 píxeles en todos los lados

        // Izquierda (recetas)
        JPanel panelRecetas = new JPanel(new BorderLayout());
        panelRecetas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes de 10 píxeles en todos los lados

        JLabel labelRecetas = new JLabel("Recetas Registradas");
        listadoRecetas = new DefaultListModel<>();
        JList<String> listaRecetas = new JList<>(listadoRecetas);
        JScrollPane scrollRecetas = new JScrollPane(listaRecetas);

        JPanel panelBotonesRecetas = new JPanel(new FlowLayout());
        JButton botonVerReceta = new JButton("Ver Receta");
        botonVerReceta.addActionListener(e -> {
            int selectedIndex = listaRecetas.getSelectedIndex();
            if (selectedIndex != -1) {
                String recetaNombre = listadoRecetas.getElementAt(selectedIndex);
                Receta receta = recetario.getRecetaPorNombre(recetaNombre);
                if (receta != null) {
                    new MostrarDatosRecetaFrame(receta);
                } else {
                    JOptionPane.showMessageDialog(this, "Receta no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una receta.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botonEliminarReceta = new JButton("Eliminar Receta");
        botonEliminarReceta.addActionListener(e -> {
            int selectedIndex = listaRecetas.getSelectedIndex();
            if (selectedIndex != -1) {
                String recetaNombre = listadoRecetas.getElementAt(selectedIndex);
                Receta receta = recetario.getRecetaPorNombre(recetaNombre);
                if (receta != null) {
                    if (recetario.eliminarRecetaRegistrada(receta)) {
                        JOptionPane.showMessageDialog(this, "Receta Eliminada Con Éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al Eliminar la Receta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Receta No Encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una Receta", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        panelBotonesRecetas.add(botonVerReceta);
        panelBotonesRecetas.add(botonEliminarReceta);

        panelRecetas.add(labelRecetas, BorderLayout.NORTH);
        panelRecetas.add(scrollRecetas, BorderLayout.CENTER);
        panelRecetas.add(panelBotonesRecetas, BorderLayout.SOUTH);

        gridPanel.add(panelRecetas);

        // Centro (ingredientes disponibles)
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes de 10 píxeles en todos los lados

        JLabel labelCentral = new JLabel("Ingredientes Disponibles", SwingConstants.CENTER);

        listadoIngredientesDisponibles = new DefaultListModel<>();
        JList<String> listaIngredientesDisponibles = new JList<>(listadoIngredientesDisponibles);
        JScrollPane scrollIngredientesDisponibles = new JScrollPane(listaIngredientesDisponibles);

        listadoIngredientes = new DefaultListModel<>();
        JList<String> listaIngredientes = new JList<>(listadoIngredientes);
        JScrollPane scrollIngredientes = new JScrollPane(listaIngredientes);

        JPanel panelBotonesDisponibles = new JPanel(new BorderLayout());
        JButton botonAnadirIngredienteDisponible = new JButton("Añadir");
        botonAnadirIngredienteDisponible.addActionListener(e -> {
            int selectedIndex = listaIngredientes.getSelectedIndex();
            if (selectedIndex != -1) {
                String ingredienteNombre = listadoIngredientes.getElementAt(selectedIndex);

                Ingrediente ingrediente = recetario.getIngredientePorNombre(ingredienteNombre);

                if (ingrediente != null) {
                    if (!recetario.getIngredientesDisponibles().contains(ingrediente)) {
                        recetario.agregarIngredienteDisponible(ingrediente);
                        listadoIngredientesDisponibles.addElement(ingrediente.getNombre());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrediente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un ingrediente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botonEliminarIngredienteDisponible = new JButton("Eliminar");
        botonEliminarIngredienteDisponible.addActionListener(e -> {
            int selectedIndex = listaIngredientesDisponibles.getSelectedIndex();
            if (selectedIndex != -1) {
                String ingredienteNombre = listadoIngredientesDisponibles.getElementAt(selectedIndex);
                Ingrediente ingrediente = recetario.getIngredientePorNombre(ingredienteNombre);

                if (ingrediente != null) {
                    recetario.eliminarIngredienteDisponible(ingrediente);
                    listadoIngredientesDisponibles.removeElement(ingrediente.getNombre());
                }
            }
        });

        JButton botonLimpiarIngredientesDisponibles = new JButton("Limpiar");
        botonLimpiarIngredientesDisponibles.addActionListener(e -> {
            listadoIngredientesDisponibles.clear();
            recetario.limpiarIngredientesDisponibles();
        });

        panelBotonesDisponibles.add(botonAnadirIngredienteDisponible, BorderLayout.WEST);
        panelBotonesDisponibles.add(botonEliminarIngredienteDisponible, BorderLayout.EAST);
        panelBotonesDisponibles.add(botonLimpiarIngredientesDisponibles, BorderLayout.CENTER);

        panelCentral.add(labelCentral, BorderLayout.NORTH);
        panelCentral.add(scrollIngredientesDisponibles, BorderLayout.CENTER);
        panelCentral.add(panelBotonesDisponibles, BorderLayout.SOUTH);

        gridPanel.add(panelCentral);

        // Derecha (ingredientes registrados)
        JPanel panelIngredientes = new JPanel(new BorderLayout());
        panelIngredientes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Márgenes de 10 píxeles en todos los lados

        JLabel labelIngredientes = new JLabel("Ingredientes Registrados");

        JPanel panelBotonesIngredientes = new JPanel(new BorderLayout());
        JButton botonVerIngrediente = new JButton("Ver Ingrediente");
        botonVerIngrediente.addActionListener(e -> {
            int selectedIndex = listaIngredientes.getSelectedIndex();
            if (selectedIndex != -1) {
                String ingredienteNombre = listadoIngredientes.getElementAt(selectedIndex);
                Ingrediente ingrediente = recetario.getIngredientePorNombre(ingredienteNombre);
                if (ingrediente != null) {
                    new MostrarDatosIngredienteFrame(ingrediente);
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrediente No Encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Ingrediente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton botonAnadirIngrediente = new JButton("Añadir Ingrediente");
        botonAnadirIngrediente.addActionListener(e -> {
            new SeleccionarTipoIngredienteFrame(recetario);
        });

        JButton botonEliminarIngrediente = new JButton("Eliminar Ingrediente");
        botonEliminarIngrediente.addActionListener(e -> {
            int selectedIndex = listaIngredientes.getSelectedIndex();
            if (selectedIndex != -1) {
                String ingredienteNombre = listadoIngredientes.getElementAt(selectedIndex);
                Ingrediente ingrediente = recetario.getIngredientePorNombre(ingredienteNombre);
                if (ingrediente != null) {
                    if (recetario.eliminarIngredienteRegistrado(ingrediente)) {
                        JOptionPane.showMessageDialog(this, "Ingrediente Eliminado Con Éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al Eliminar el Ingrediente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrediente no Encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Ingrediente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        panelBotonesIngredientes.add(botonVerIngrediente, BorderLayout.WEST);
        panelBotonesIngredientes.add(botonAnadirIngrediente, BorderLayout.EAST);
        panelBotonesIngredientes.add(botonEliminarIngrediente, BorderLayout.SOUTH);

        panelIngredientes.add(labelIngredientes, BorderLayout.NORTH);
        panelIngredientes.add(scrollIngredientes, BorderLayout.CENTER);
        panelIngredientes.add(panelBotonesIngredientes, BorderLayout.SOUTH);

        gridPanel.add(panelIngredientes);

        mainPanel.add(gridPanel, BorderLayout.CENTER);

        // Panel Inferior (Botones generales)
        JPanel panelInferior = new JPanel(new FlowLayout());
        JButton botonMostrarRecetasPreparables = new JButton("Mostrar Recetas Preparables");
        botonMostrarRecetasPreparables.addActionListener(e -> {
            new MostrarRecetasPreparablesFrame(recetario);
        });

        JButton botonAnadirReceta = new JButton("Añadir Receta");
        botonAnadirReceta.addActionListener(e -> {
            new AgregarRecetaFrame(recetario);
        });

        panelInferior.add(botonMostrarRecetasPreparables);
        panelInferior.add(botonAnadirReceta);

        mainPanel.add(panelInferior, BorderLayout.SOUTH);

        // Botón actualizar registros
        JButton botonActualizarListas = new JButton("Actualizar Registros");
        botonActualizarListas.addActionListener(e -> {
            actualizarlistasRegistros();
        });

        JPanel panelActualizar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelActualizar.add(botonActualizarListas);

        mainPanel.add(panelActualizar, BorderLayout.NORTH);

        setContentPane(mainPanel);

        actualizarlistasRegistros();

        setVisible(true);
    }
    public void actualizarlistasRegistros() {

        listadoRecetas.clear();
        listadoIngredientes.clear();

        for (Receta receta : recetario.getRecetasRegistradas()) {
            listadoRecetas.addElement(receta.getNombre());
        }

        for (Ingrediente ingrediente : recetario.getIngredientesRegistrados()) {
            listadoIngredientes.addElement(ingrediente.getNombre());
        }
        setVisible(true);
    }
}