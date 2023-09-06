package Pruebas;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Pruebas.ConexionBD.conexion;

public class Arreglos extends JPanel {
    private PanelPrincipal panelPrincipal;
    private JPanel formularioPanel;
    private List<JTextField> elementosTextField;
    private JButton guardarButton;
    private JButton verArreglosButton; // Botón "Ver Arreglos"
    private Connection conexion;
    private InsertarDatos insertarDatos;
    private int numeroArreglo;

    public Arreglos(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.conexion = ConexionBD.obtenerConexion();
        setLayout(new FlowLayout());
        elementosTextField = new ArrayList<>();
        numeroArreglo = 0; // Inicializar el contador de arreglos en 0
        insertarDatos = new InsertarDatos(conexion);
        mostrarFormulario(false); // Inicia ocultando el formulario
    }

    void mostrarFormulario(boolean mostrar) {
        removeAll();

        if (mostrar) {
            formularioPanel = new JPanel(new GridLayout(0, 2));
            elementosTextField.clear();

            formularioPanel.add(new JLabel("Ingrese el tamaño del arreglo:"));
            JTextField tamanoTextField = new JTextField(10);
            formularioPanel.add(tamanoTextField);

            JButton capturarButton = new JButton("Capturar");
            capturarButton.setFont(new Font("Arial", Font.BOLD, 16));
            formularioPanel.add(capturarButton);

            capturarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int tamano = obtenerTamanoDesdeTextField(tamanoTextField.getText());
                    crearCamposElementos(tamano);
                }
            });

            tamanoTextField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    capturarButton.setEnabled(!tamanoTextField.getText().isEmpty());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    capturarButton.setEnabled(!tamanoTextField.getText().isEmpty());
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    // No se usa en JTextField
                }
            });

            add(formularioPanel);
        } else {
            JButton crearArregloButton = new JButton("Crear Arreglo");
            crearArregloButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarFormulario(true);

                }
            });


            // Botón "Ver Arreglos"
            verArreglosButton = new JButton("Ver Arreglos");
            verArreglosButton.setFont(new Font("Arial", Font.BOLD, 16));
            verArreglosButton.setForeground(new Color(0, 43, 121));

            verArreglosButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarVentanaVerArreglos();
                }
            });

            if (ConexionBD.existenArreglos()) {
                verArreglosButton.setEnabled(true);
            } else {
                verArreglosButton.setEnabled(false);
            }

            add(crearArregloButton);
            add(verArreglosButton); // Agregar el botón "Ver Arreglos" al panel
        }

        revalidate();
        repaint();
    }

    private int obtenerTamanoDesdeTextField(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void crearCamposElementos(int tamano) {
        elementosTextField.clear();
        formularioPanel.removeAll();

        for (int i = 0; i < tamano; i++) {
            JTextField elementoTextField = new JTextField(10);
            elementosTextField.add(elementoTextField);

            formularioPanel.add(new JLabel("Elemento " + (i + 1) + ":"));
            formularioPanel.setFont(new Font("Arial", Font.BOLD, 24));
            formularioPanel.add(elementoTextField);
        }

        guardarButton = new JButton("Guardar", new ImageIcon("guardar_32.png"));
        panelPrincipal.eventoSobreBoton(guardarButton, new ImageIcon("guardar_32.png"),new ImageIcon("guardar_64.png"));
        guardarButton.setFont(new Font("Arial", Font.BOLD, 16));
        guardarButton.setForeground(new Color(0, 43, 121));

        formularioPanel.add(guardarButton);

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearNuevoArreglo();
            }
        });

        revalidate();
        repaint();
    }

    private void crearNuevoArreglo() {
        int tamano = elementosTextField.size();
        String[] arreglo = new String[tamano];

        for (int i = 0; i < tamano; i++) {
            JTextField textField = elementosTextField.get(i);
            arreglo[i] = textField.getText();
        }

        if (tamano > 0) {
            String categoriaPadre = "Arreglo"; // Captura la palabra "Arreglo"
            insertarDatos.insertarDatosCampopadre(categoriaPadre, numeroArreglo, arreglo);
            JOptionPane.showMessageDialog(this, "Datos guardados con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            mostrarFormulario(false);
            numeroArreglo++;
        } else {
            JOptionPane.showMessageDialog(this, "No hay datos para guardar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void mostrarVentanaVerArreglos() {
        JFrame frame = new JFrame("Ver Arreglos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        VerArreglosPanel verArreglosPanel = new VerArreglosPanel(conexion);
        frame.add(verArreglosPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}