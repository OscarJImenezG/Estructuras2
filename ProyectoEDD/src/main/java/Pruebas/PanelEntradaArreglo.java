package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//NO SE OCUPA
public class PanelEntradaArreglo extends JPanel {
    private JTextField tamanoTextField;
    private JButton crearArregloButton;

    public PanelEntradaArreglo(ActionListener crearArregloListener) {
        setLayout(new FlowLayout());
        this.setBackground(new Color(0, 43, 121));

        JLabel tamanoLabel = new JLabel("Tamaño del arreglo:");
        tamanoLabel.setFont(new Font("Arial",  Font.PLAIN, 18));
        tamanoLabel.setForeground(new Color(213, 159, 15));

        tamanoTextField = new JTextField(10);

        crearArregloButton = new JButton("Crear Arreglo");
        crearArregloButton.setFont(new Font("Arial", Font.BOLD, 16));
        crearArregloButton.setForeground(new Color(213, 159, 15));

        crearArregloButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                crearArregloButton.setBackground(new Color(213, 159, 15));
                crearArregloButton.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                crearArregloButton.setBackground(new Color(0, 43, 121));
                crearArregloButton.setForeground(new Color(213, 159, 15));
            }
        });
        crearArregloButton.addActionListener(crearArregloListener);

        add(tamanoLabel);
        add(tamanoTextField);
        add(crearArregloButton);
    }

    public int getTamanoArreglo() {
        try {
            return Integer.parseInt(tamanoTextField.getText());
        } catch (NumberFormatException e) {
            return -1; // Valor inválido
        }
    }
}