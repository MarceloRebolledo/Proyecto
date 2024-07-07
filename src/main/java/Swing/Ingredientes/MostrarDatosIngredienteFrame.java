package Swing.Ingredientes;

import Modelos.Ingredientes.*;

import javax.swing.*;
import java.awt.*;

public class MostrarDatosIngredienteFrame extends JFrame {

    public MostrarDatosIngredienteFrame(Ingrediente ingrediente) {
        setTitle("Ingrediente: " + ingrediente.getNombre());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        JPanel detallesPanel = new JPanel();
        detallesPanel.setLayout(new BoxLayout(detallesPanel, BoxLayout.Y_AXIS));

        // Labels para los detalles del ingrediente (GENERAL)
        JLabel nombreLabel = new JLabel("Nombre: " + ingrediente.getNombre());
        JLabel descripcionLabel = new JLabel("Descripcion: " + ingrediente.getDescripcion());
        JLabel medidaLabel = new JLabel("Medida: " + ingrediente.getMedida());

        // Añadir los labels al panel de detalles
        detallesPanel.add(nombreLabel);
        detallesPanel.add(descripcionLabel);
        detallesPanel.add(medidaLabel);

        // Añadir atributos extra segun tipo
        switch (ingrediente.getTipo()) {
            case "Liquido":
                Liquido liquido = (Liquido) ingrediente;
                JLabel alcoholicoLabel = new JLabel("Alcoholico: " + liquido.isAlcoholico());
                JLabel procedenciaLabel = new JLabel("Procedencia: " + liquido.getProcedencia());

                detallesPanel.add(alcoholicoLabel);
                detallesPanel.add(procedenciaLabel);
                break;
            case "Carne":
                Carne carne = (Carne) ingrediente;
                JLabel animalLabel = new JLabel("Animal: " + carne.getAnimal());

                detallesPanel.add(animalLabel);
                break;
            case "Verdura":
                Verdura verdura = (Verdura) ingrediente;
                JLabel tipoverduraLabel = new JLabel("Tipo: " + verdura.getTipoVerdura());

                detallesPanel.add(tipoverduraLabel);
                break;
            case "Masa":
                Masa masa = (Masa) ingrediente;
                JLabel tipoMasaLabel = new JLabel("Tipo: " + masa.getTipoMasa());

                detallesPanel.add(tipoMasaLabel);
                break;
            case "Fruta":
                Fruta fruta = (Fruta) ingrediente;
                JLabel colorLabel = new JLabel("Color: " + fruta.getColor());
                JLabel citricaLabel = new JLabel("Citrica: " + fruta.isCitrica());

                detallesPanel.add(colorLabel);
                detallesPanel.add(citricaLabel);
                break;
        }

        panel.add(detallesPanel, BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }
}