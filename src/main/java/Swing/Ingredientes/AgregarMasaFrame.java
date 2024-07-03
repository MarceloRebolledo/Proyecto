package Swing.Ingredientes;

import Modelos.Ingredientes.Masa;
import Modelos.Recetario.Recetario;

import javax.swing.*;

public class AgregarMasaFrame extends JFrame {

    public AgregarMasaFrame(Recetario recetario) {
        setTitle("Agregar Masa");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Campo para nombre
        panel.add(new JLabel("Nombre:"));
        JTextField nombreField = new JTextField();
        panel.add(nombreField);

        // Campo para descripción
        panel.add(new JLabel("Descripcion:"));
        JTextField descripcionField = new JTextField();
        panel.add(descripcionField);

        // Campo para medida
        panel.add(new JLabel("Medida:"));
        String[] medidas = {"g", "kg", "unidad"};
        JComboBox<String> medidaComboBox = new JComboBox<>(medidas);
        panel.add(medidaComboBox);

        // Checkbox para integral
        panel.add(new JLabel("Integral:"));
        JCheckBox integralCheckBox = new JCheckBox();
        panel.add(integralCheckBox);

        // Campo para tipo de masa
        panel.add(new JLabel("Tipo de Masa:"));
        JTextField tipoMasaField = new JTextField();
        panel.add(tipoMasaField);

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
            boolean integral = integralCheckBox.isSelected();
            String tipoMasa = tipoMasaField.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || tipoMasa.isEmpty() || medida.isEmpty() || medidaComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Masa masa = new Masa(nombre, descripcion, medida, integral, tipoMasa);

                if (recetario.agregarIngredienteRegistro(masa)) {
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
