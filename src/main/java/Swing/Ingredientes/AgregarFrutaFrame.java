package Swing.Ingredientes;

import Modelos.Ingredientes.Fruta;
import Modelos.Recetario.Recetario;

import javax.swing.*;

public class AgregarFrutaFrame extends JFrame {

    public AgregarFrutaFrame(Recetario recetario) {
        setTitle("Agregar Fruta");
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
        String[] medidas = {"unidad", "kg", "g"};
        JComboBox<String> medidaComboBox = new JComboBox<>(medidas);
        panel.add(medidaComboBox);

        panel.add(new JLabel("Color:"));
        JTextField colorField = new JTextField();
        panel.add(colorField);

        // Checkbox citrica
        panel.add(new JLabel("Citrica:"));
        JCheckBox citricaCheckBox = new JCheckBox();
        panel.add(citricaCheckBox);

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
            String color = colorField.getText().trim();
            boolean citrica = citricaCheckBox.isSelected();

            if (nombre.isEmpty() || descripcion.isEmpty() || color.isEmpty() || medida.isEmpty() || medidaComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Fruta fruta = new Fruta(nombre, descripcion, medida, color, citrica);

                if (recetario.agregarIngredienteRegistro(fruta)) {
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
