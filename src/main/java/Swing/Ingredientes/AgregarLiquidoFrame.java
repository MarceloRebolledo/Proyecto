package Swing.Ingredientes;

import Modelos.Ingredientes.Liquido;
import Modelos.Recetario.Recetario;

import javax.swing.*;

public class AgregarLiquidoFrame extends JFrame {

    public AgregarLiquidoFrame(Recetario recetario) {
        setTitle("Agregar Liquido");
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
        String[] medidas = {"ml", "li"};
        JComboBox<String> medidaComboBox = new JComboBox<>(medidas);
        panel.add(medidaComboBox);

        // Checkbox alcoholico
        panel.add(new JLabel("Alcoholico:"));
        JCheckBox alcoholicoCheckBox = new JCheckBox();
        panel.add(alcoholicoCheckBox);

        panel.add(new JLabel("Procedencia:"));
        JTextField procedenciaField = new JTextField();
        panel.add(procedenciaField);

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
            String procedencia = procedenciaField.getText().trim();

            if (nombre.isEmpty() || descripcion.isEmpty() || medida.isEmpty() || procedencia.isEmpty() || medidaComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Rellenar Todos los Campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                boolean alcoholico = alcoholicoCheckBox.isSelected();

                Liquido liquido = new Liquido(nombre, descripcion, medida, alcoholico, procedencia);

                if (recetario.agregarIngredienteRegistro(liquido)) {
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
