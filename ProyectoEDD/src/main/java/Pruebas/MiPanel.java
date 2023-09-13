package Pruebas;

import javax.swing.*;
import java.awt.*;

public class MiPanel extends JPanel {
    public MiPanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(new Color(0, 43, 121));
    }
}