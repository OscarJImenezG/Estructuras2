package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ArreglosCola extends JPanel {

    private PanelPrincipal panelPrincipal;
    private JPanel formularioPanel;
    private JTextField elementoTextField;
    private JButton guardarButton;

    public ArreglosCola() {
    }

    public ArreglosCola(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.formularioPanel = new JPanel(); // Inicializa el formularioPanel
        setLayout(new BorderLayout());

        mostrarFormulario(false);
    }

    private void mostrarFormulario(boolean mostrar) {
        removeAll();

        if (mostrar) {

            formularioPanel = new JPanel(new GridLayout(0, 2));

            formularioPanel.add(new JLabel("Ingrese el elemento a agregar:"), new Font("Arial", Font.PLAIN, 16));
            elementoTextField = new JTextField(10);
            elementoTextField.setFont(new Font("Arial", Font.BOLD, 14));
            formularioPanel.add(elementoTextField);

            guardarButton = new JButton("Guardar");
            guardarButton.setFont(new Font("Arial", Font.BOLD, 16));
            guardarButton.setBackground(new Color(0, 43, 121));
            guardarButton.setForeground(new Color(213, 159, 15));

            guardarButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    guardarButton.setBackground(new Color(213, 159, 15));
                    guardarButton.setForeground(new Color(0, 43, 121));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    guardarButton.setBackground(new Color(0, 43, 121));
                    guardarButton.setForeground(new Color(213, 159, 15));
                }
            });
            formularioPanel.add(guardarButton);

            guardarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String elemento = elementoTextField.getText();
                    char[] caracteres1 = elementoTextField.getText().toCharArray();
                    for (int i = 0; i < elementoTextField.getText().length(); i++) {
                    if (caracteres1[i] == '1' || caracteres1[i] == '2' || caracteres1[i] == '3' || caracteres1[i] == '4' || caracteres1[i] == '5' || caracteres1[i] == '6' || caracteres1[i] == '7' || caracteres1[i] == '8' || caracteres1[i] == '9' || caracteres1[i] == '0') {
                    } else {
                        JOptionPane.showMessageDialog(null, "Solo admite numeros enteros por favor rectifica tu entrada", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                }
                    if (!elemento.isEmpty()) {
                        System.out.println("Elemento capturado: " + elemento);
                    }
                }
            });

            add(formularioPanel, BorderLayout.NORTH);
        } else {
            JPanel buttonsPanel = new JPanel(new FlowLayout());
            JButton botonArreglos = new JButton("Arreglos");
            botonArreglos.setFont(new Font("Arial", Font.BOLD, 16));
            botonArreglos.setBackground(new Color(0, 43, 121));
            botonArreglos.setForeground(new Color(213, 159, 15));
            botonArreglos.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    botonArreglos.setBackground(new Color(213, 159, 15));
                    botonArreglos.setForeground(new Color(0, 43, 121));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    botonArreglos.setBackground(new Color(0, 43, 121));
                    botonArreglos.setForeground(new Color(213, 159, 15));
                }
            });
            botonArreglos.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelPrincipal.mostrarPanel("arreglos");
                }
            });
            JButton botonCola = new JButton("Cola");
            botonCola.setVisible(false);
            botonCola.setFont(new Font("Arial", Font.BOLD, 16));
            botonCola.setForeground(new Color(213, 159, 15));
            botonCola.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    botonCola.setBackground(new Color(213, 159, 15));
                    botonCola.setForeground(new Color(0, 43, 121));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    botonCola.setBackground(new Color(0, 43, 121));
                    botonCola.setForeground(new Color(213, 159, 15));
                }
            });
            botonCola.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panelPrincipal.mostrarPanel("cola");
                }
            });
            buttonsPanel.add(botonArreglos);
            buttonsPanel.add(botonCola);

            add(buttonsPanel, BorderLayout.NORTH);
        }

        revalidate();
        repaint();
    }
}
