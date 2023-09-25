package Pruebas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisenoBotones extends JButton {

    public DisenoBotones(String texto, int widght) {
        super(texto);
        setPreferredSize(new Dimension(widght, 30));
        setFont(new Font("Arial", Font.BOLD, 14));
        setBackground(new Color(0, 43, 121));
        setForeground(new Color(213, 159, 15));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(213, 159, 15));
                setForeground(new Color(0, 43, 121));
            }

            public void mouseExited(MouseEvent e) {
                setBackground(new Color(0, 43, 121));
                setForeground(new Color(213, 159, 15));
            }
        });

        setMargin(new Insets(20, 20, 20, 20));

        //setBorder(new RoundedBorder(10));
    }
}