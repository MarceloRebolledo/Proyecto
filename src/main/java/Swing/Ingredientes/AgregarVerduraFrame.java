package Swing.Ingredientes;

import Modelos.Ingredientes.Verdura;
import Modelos.Recetario.Recetario;

import javax.swing.*;

public class AgregarVerduraFrame extends JFrame {

    public AgregarVerduraFrame(Recetario recetario) {
        setTitle("Agregar Verdura");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        panel.add(nombreField);

        panel.add(new JLabel("Descripcion:"));
        JTextField descripcionField = new JTextField();
        panel.add(descripcionField);

        panel.add(new JLabel("Medida:"));
        String[] medidas = {"g", "kg", "unidad"};
        JComboBox<String> medidaComboBox = new JComboBox<>(medidas);
        panel.add(medidaComboBox);

        panel.add(new JLabel("Tipo de Verdura:"));
        JTextField tipoVerduraField = new JTextField();
        panel.add(tipoVerduraField);

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
            String tipoVerdura = tipoVerduraField.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || tipoVerdura.isEmpty() || medida.isEmpty() || medidaComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Rellenar Todos los Campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Verdura verdura = new Verdura(nombre, descripcion, medida, tipoVerdura);

                if (recetario.agregarIngredienteRegistro(verdura)) {
                    JOptionPane.showMessageDialog(this, "Ingrediente Agregado con Exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error al Agregar el Ingrediente", "Error", JOptionPane.ERROR_MESSAGE);
                }

                dispose();
            }
        });
        setVisible(true);
    }
}
