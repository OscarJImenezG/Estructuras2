package Pruebas;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
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
    private JPanel nuevo;

    private JScrollPane scrollPaneFormulario;

    public Arreglos(PanelPrincipal panelPrincipal) {
        this.panelPrincipal = panelPrincipal;
        this.conexion = ConexionBD.obtenerConexion();
        setLayout(new FlowLayout());
        elementosTextField = new ArrayList<>();
        numeroArreglo = 0; // Inicializar el contador de arreglos en 0
        insertarDatos = new InsertarDatos(conexion);
        mostrarFormulario(false); // Inicia ocultando el formulario
    }

    public Arreglos() {
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
            nuevo = new JPanel();
            setSize(500, 400);
            setLayout(new BorderLayout());
            nuevo.setSize(new Dimension(500, 400));
            formularioPanel = new JPanel(new GridLayout(0, 2));
            elementosTextField.clear();
            formularioPanel.add(new JLabel("Ingrese el tamaño del arreglo:  "));
            formularioPanel.setFont(new Font("Arial", Font.PLAIN, 16));
            JTextField tamanoTextField = new JTextField(10);
            tamanoTextField.setFont(new Font("Arial", Font.BOLD, 14));
            formularioPanel.add(tamanoTextField);
            JButton capturarButton = new JButton("Capturar");
            capturarButton.setFont(new Font("Arial", Font.BOLD, 16));
            capturarButton.setForeground(new Color(213, 159, 15));
            capturarButton.setForeground(new Color(213, 159, 15));
            capturarButton.setBackground(new Color(0, 43, 121));

            capturarButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    capturarButton.setBackground(new Color(213, 159, 15));
                    capturarButton.setForeground(new Color(0, 43, 121));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    capturarButton.setForeground(new Color(213, 159, 15));
                    capturarButton.setBackground(new Color(0, 43, 121));
                }
            });
            formularioPanel.add(capturarButton);

            capturarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int tamano = obtenerTamanoDesdeTextField(tamanoTextField.getText());
                    boolean bandera = false;
                    char[] caracteres1 = tamanoTextField.getText().toCharArray();
                    for (int i = 0; i < tamanoTextField.getText().length(); i++) {
                        if (caracteres1[i] == '1' || caracteres1[i] == '2' || caracteres1[i] == '3' || caracteres1[i] == '4' || caracteres1[i] == '5' || caracteres1[i] == '6' || caracteres1[i] == '7' || caracteres1[i] == '8' || caracteres1[i] == '9' || caracteres1[i] == '0') {
                        } else {
                            bandera = true;
                            JOptionPane.showMessageDialog(null, "Solo admite numeros enteros por favor rectifica tu entrada", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                    if (bandera == false) {
                        if (tamanoTextField.getText().isEmpty() || tamanoTextField.getText().equals("0")) {
                            JOptionPane.showMessageDialog(null, "Debe ingresar un numero entero mayor a cero", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
                        } else {
                            crearCamposElementos(tamano);
                        }
                    }
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

            nuevo.add(formularioPanel);
            add(nuevo, BorderLayout.NORTH);

        } else {
            mostrarFormulario(true);
            JButton crearArregloButton = new JButton("Crear Arreglo");
            crearArregloButton.setFont(new Font("Arial", Font.BOLD, 16));
            crearArregloButton.setForeground(new Color(213, 159, 15));
            crearArregloButton.setBackground(new Color(0, 43, 121));
            crearArregloButton.setForeground(new Color(213, 159, 15));
            crearArregloButton.setBackground(new Color(0, 43, 121));

            crearArregloButton.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseEntered(MouseEvent e) {
                    crearArregloButton.setBackground(new Color(213, 159, 15));
                    crearArregloButton.setForeground(new Color(0, 43, 121));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    crearArregloButton.setForeground(new Color(213, 159, 15));
                    crearArregloButton.setBackground(new Color(0, 43, 121));
                }
            });
            crearArregloButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            // Botón "Ver Arreglos"
            verArreglosButton = new JButton("Ver Arreglos");
            verArreglosButton.setFont(new Font("Arial", Font.BOLD, 16));

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

        JPanel contenidoPanel = new JPanel(new GridLayout(0, 2));

        for (int i = 0; i < tamano; i++) {
            JTextField elementoTextField = new JTextField(10);
            elementoTextField.setFont(new Font("Arial", Font.BOLD, 14));

            elementosTextField.add(elementoTextField);
            /* Añade los formularios al contenidoPanel */
            contenidoPanel.add(new JLabel("Elemento " + (i + 1) + ":"), new Font("Arial", Font.BOLD, 24));
            contenidoPanel.add(elementoTextField);
        }

        scrollPaneFormulario = new JScrollPane(contenidoPanel);
        scrollPaneFormulario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFormulario.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        guardarButton = new JButton("Guardar", new ImageIcon("guardar_32.png"));
        guardarButton.setFont(new Font("Arial", Font.BOLD, 16));
        guardarButton.setForeground(new Color(213, 159, 15));
        guardarButton.setBackground(new Color(0, 43, 121));

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

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearNuevoArreglo();
            }
        });

        JPanel nuevo = new JPanel(new BorderLayout());
        nuevo.setPreferredSize(new Dimension(500, 400));
        nuevo.add(scrollPaneFormulario, BorderLayout.CENTER);
        nuevo.add(guardarButton, BorderLayout.SOUTH);

        removeAll();
        add(nuevo);
        revalidate();
        repaint();
    }

    /**/
    private void crearNuevoArreglo() {
        Integer tamano = elementosTextField.size();
        String[] arreglo = new String[tamano];

        for (int i = 0; i < tamano; i++) {
            arreglo[i] = elementosTextField.get(i).getText();
        }

        System.out.println("Valores a enviar a insertarDatosCampopadre:");
        System.out.println("Categoría Padre: Arreglo");
        System.out.println("Número de conteo: " + numeroArreglo);
        System.out.println("Valores del arreglo:");
        for (int i = 0; i < tamano; i++) {
            System.out.println("  - " + arreglo[i]);
        }

        if (tamano > 0) {
            String categoriaPadre = "Arreglo";
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
