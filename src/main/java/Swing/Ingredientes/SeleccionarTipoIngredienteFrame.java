package Swing.Ingredientes;

import Modelos.Recetario.Recetario;

import javax.swing.*;

public class SeleccionarTipoIngredienteFrame extends JFrame {

    private Recetario recetario;

    public SeleccionarTipoIngredienteFrame(Recetario recetario) {

        this.recetario = recetario;

        setTitle("Seleccionar Tipo de Ingrediente");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal con BoxLayout vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Seleccione el tipo de ingrediente:");
        String[] tipos = {"Carne", "Fruta", "Liquido", "Masa", "Verdura"};
        JComboBox<String> comboBox = new JComboBox<>(tipos);

        JButton seleccionarButton = new JButton("Seleccionar");
        seleccionarButton.addActionListener(e -> {
            String tipoSeleccionado = (String) comboBox.getSelectedItem();
            mostrarVentanaAgregarIngrediente(tipoSeleccionado);
            dispose();
        });

        // Añadir componentes al panel principal
        panel.add(label);
        panel.add(comboBox);
        panel.add(Box.createVerticalStrut(10));
        panel.add(seleccionarButton);

        add(panel);

        setVisible(true);
    }

    private void mostrarVentanaAgregarIngrediente(String tipo) {
        switch (tipo) {
            case "Carne":
                new AgregarCarneFrame(recetario);
                break;
            case "Fruta":
                new AgregarFrutaFrame(recetario);
                break;
            case "Liquido":
                new AgregarLiquidoFrame(recetario);
                break;
            case "Masa":
                new AgregarMasaFrame(recetario);
                break;
            case "Verdura":
                new AgregarVerduraFrame(recetario);
                break;
        }
    }
}