package Swing.Ingredientes;

import Modelos.Ingredientes.Carne;
import Modelos.Recetario.Recetario;

import javax.swing.*;

public class AgregarCarneFrame extends JFrame {

    public AgregarCarneFrame(Recetario recetario) {
        setTitle("Agregar Carne");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Descripción:"));
        JTextField descripcionField = new JTextField();
        panel.add(descripcionField);

        panel.add(new JLabel("Medida:"));
        String[] medidas = {"mg", "kg"};
        JComboBox<String> medidaComboBox = new JComboBox<>(medidas);
        panel.add(medidaComboBox);

        panel.add(new JLabel("Animal:"));
        JTextField animalField = new JTextField();
        panel.add(animalField);

        // Botones
        JButton guardarButton = new JButton("Guardar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(guardarButton);

        panel.add(buttonPanel);

        add(panel);

        guardarButton.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String descripcion = descripcionField.getText().trim();
            String medida = medidaComboBox.getSelectedItem().toString();
            String animal = animalField.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || animal.isEmpty() || medida.isEmpty() || medidaComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Carne carne = new Carne(nombre, descripcion, medida, animal);

                if (recetario.agregarIngredienteRegistro(carne)) {
                    JOptionPane.showMessageDialog(this, "Ingrediente Agregado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al Agregar el Ingrediente", "Error", JOptionPane.ERROR_MESSAGE);
                }

                dispose();
            }
        });

        setVisible(true);
    }
}