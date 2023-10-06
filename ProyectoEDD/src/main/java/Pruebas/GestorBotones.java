package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestorBotones {

    public static JButton crearBoton(String texto, ActionListener listener) {
        JButton boton = new JButton(texto);
        boton.addActionListener(listener);

        boton.setBackground(UIManager.getColor("Button.background"));
        boton.setForeground(UIManager.getColor("Button.foreground"));

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(new Color(0, 43, 121));
                boton.setForeground(Color.WHITE);
                Font font = boton.getFont();
                boton.setFont(new Font(font.getName(), Font.BOLD, font.getSize()));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(UIManager.getColor("Button.background"));
                boton.setForeground(UIManager.getColor("Button.foreground"));
                Font font = boton.getFont();
                boton.setFont(new Font(font.getName(), Font.PLAIN, font.getSize()));
            }
        });

        return boton;
    }


}