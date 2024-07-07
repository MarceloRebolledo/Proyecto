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

    public MainFrame() {

        // Inicializar el recetario y actualizar el registro
        recetario = new Recetario();
        recetario.actualizarRegistro();

        // Configuracion Frame
        setTitle("Recetario");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Usar BorderLayout para el JFrame
        setLayout(new BorderLayout());

        // Izquierda (recetas)

        JPanel panelRecetas = new JPanel();
        panelRecetas.setLayout(new BoxLayout(panelRecetas, BoxLayout.Y_AXIS));
        JLabel labelRecetas = new JLabel("Recetas Registradas");
        listadoRecetas = new DefaultListModel<>();
        JList<String> listaRecetas = new JList<>(listadoRecetas);
        JScrollPane scrollRecetas = new JScrollPane(listaRecetas);

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
                    if(recetario.eliminarReceta(receta)) {
                        JOptionPane.showMessageDialog(this, "Receta Eliminado Con Exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al Eliminar el Receta", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Receta No Encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una Receta", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        panelRecetas.add(labelRecetas);
        panelRecetas.add(scrollRecetas);
        panelRecetas.add(botonVerReceta);
        panelRecetas.add(botonEliminarReceta);

        add(panelRecetas, BorderLayout.WEST);

        // Derecha (ingredientes)

        JPanel panelIngredientes = new JPanel();
        panelIngredientes.setLayout(new BoxLayout(panelIngredientes, BoxLayout.Y_AXIS));
        JLabel labelIngredientes = new JLabel("Ingredientes Registrados");
        listadoIngredientes = new DefaultListModel<>();
        JList<String> listaIngredientes = new JList<>(listadoIngredientes);
        JScrollPane scrollIngredientes = new JScrollPane(listaIngredientes);

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
                    if(recetario.eliminarIngrediente(ingrediente)) {
                        JOptionPane.showMessageDialog(this, "Ingrediente Eliminado Con Exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al Eliminar el Ingrediente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Ingrediente no Encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un Engrediente", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        panelIngredientes.add(labelIngredientes);
        panelIngredientes.add(scrollIngredientes);
        panelIngredientes.add(botonVerIngrediente);
        panelIngredientes.add(botonAnadirIngrediente);
        panelIngredientes.add(botonEliminarIngrediente);

        add(panelIngredientes, BorderLayout.EAST);

        // boton actualizar registros

        JButton botonActualizarListas = new JButton("Actualizar Registros");
        botonActualizarListas.addActionListener(e -> {
            actualizarlistasRegistros();
        });

        add(botonActualizarListas, BorderLayout.NORTH);

        // ingredientes disponibles (panel central)

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BorderLayout());

        //      Paneles izquierda y derecha (panel central)

        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));

        //  Botonnes izquierda

        JButton botonCrearReceta = new JButton("Crear Receta");
        botonCrearReceta.addActionListener(e -> {
            new AgregarRecetaFrame(recetario);
        });

        JButton botonVerPreparables = new JButton("Ver Preparables");
        botonVerPreparables.addActionListener(e -> {
            new MostrarRecetasPreparablesFrame(recetario);
        });

        panelIzquierdo.add(botonCrearReceta);
        panelIzquierdo.add(botonVerPreparables);

        panelCentral.add(panelIzquierdo, BorderLayout.LINE_START);

        //  Centro (label lista boton)

        JLabel labelCentral = new JLabel("Ingredientes Disponibles");
        labelCentral.setHorizontalAlignment(SwingConstants.CENTER);

        panelCentral.add(labelCentral, BorderLayout.NORTH);

        DefaultListModel<String> listadoIngredientesDisponibles = new DefaultListModel<>();
        JList<String> listaIngredientesDisponibles = new JList<>(listadoIngredientesDisponibles);
        JScrollPane scrollIngredientesDisponibles = new JScrollPane(listaIngredientesDisponibles);

        panelCentral.add(scrollIngredientesDisponibles, BorderLayout.CENTER);

        JButton botonLimpiarIngredientesDisponibles = new JButton("Limpiar Ingredientes Disponibles");
        botonLimpiarIngredientesDisponibles.addActionListener(e -> {
            listadoIngredientesDisponibles.clear();
            recetario.limpiarIngredientesDisponibles();

        });
        panelCentral.add(botonLimpiarIngredientesDisponibles, BorderLayout.PAGE_END);

        //  botones derecha

        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));

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

        panelDerecho.add(botonAnadirIngredienteDisponible);
        panelDerecho.add(botonEliminarIngredienteDisponible);

        panelCentral.add(panelDerecho, BorderLayout.LINE_END);

        add(panelCentral, BorderLayout.CENTER);

        actualizarlistasRegistros();

        setVisible(true);
    }

    public void actualizarlistasRegistros() {

        // Limpiar los modelos de lista actuales
        listadoRecetas.clear();
        listadoIngredientes.clear();

        // Agregar las recetas registradas al modelo de lista
        for (Receta receta : recetario.getRecetasRegistradas()) {
            listadoRecetas.addElement(receta.getNombre());
        }

        // Agregar los ingredientes registrados al modelo de lista
        for (Ingrediente ingrediente : recetario.getIngredientesRegistrados()) {
            listadoIngredientes.addElement(ingrediente.getNombre());
        }
    }
}