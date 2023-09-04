package Pruebas;

import javax.swing.*;
import java.awt.*;

public class BarraTitulo extends JPanel {

    public BarraTitulo() {
        JLabel titulo = new JLabel("Estructuras de Datos");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new BorderLayout());
        add(titulo, BorderLayout.CENTER);
        setPreferredSize(new Dimension(0, 30)); // Altura de la barra de título
    }
}