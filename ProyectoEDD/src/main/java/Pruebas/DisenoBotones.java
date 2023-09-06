package Pruebas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisenoBotones extends JButton {

    public DisenoBotones(String texto) {
        super(texto);
        setPreferredSize(new Dimension(200, 40));
        setBackground(new Color(0, 43, 121));
        setForeground(Color.WHITE);
        setFont(getFont().deriveFont(Font.BOLD));
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(0, 26, 92));
            }

            public void mouseExited(MouseEvent e) {
                setBackground(new Color(0, 43, 121));
            }
        });

        setMargin(new Insets(20, 20, 20, 20));

        //setBorder(new RoundedBorder(10));
    }
}