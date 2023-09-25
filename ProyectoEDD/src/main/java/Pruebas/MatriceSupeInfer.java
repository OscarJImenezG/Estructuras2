package Pruebas;

import static Pruebas.MatrizDispersaPanel.mostrarPosicionesEnPantalla;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatriceSupeInfer extends JPanel {

    private static JTextField[][] matrizInput;
    private static JTextField[][] matrizInput2;
    private Integer[][] matriz;
    private int filas, columnas;
    private JPanel matrizPanel;
    private JPanel matrizPanel2;
    public DisenoBotones crearMatrizButton;
    public DisenoBotones obtenerPosicionesButton;
    private JPanel fondoPanel;

    public static void main(String[] args) {
        MatrizDispersaPanel mt = new MatrizDispersaPanel();
        mt.createAndShowGUI();

    }

    public void createAndShowGUI() {
        setSize(new Dimension(500, 400));
        setLayout(new BorderLayout());

        JPanel nuevo = new JPanel();
        nuevo.setSize(500, 400);

        JLabel labelFilas = new JLabel("Filas:");
        labelFilas.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField inputFilas = new JTextField(5);
        inputFilas.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel labelColumnas = new JLabel("Columnas:");
        labelColumnas.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField inputColumnas = new JTextField(5);
        inputColumnas.setFont(new Font("Arial", Font.BOLD, 14));

        crearMatrizButton = new DisenoBotones("Crea Matrices", 160);
        obtenerPosicionesButton = new DisenoBotones("Juntar Matrices", 190);

        nuevo.add(labelFilas);
        nuevo.add(inputFilas);
        nuevo.add(labelColumnas);
        nuevo.add(inputColumnas);
        nuevo.add(crearMatrizButton);

        JPanel piePanel = new JPanel();
        piePanel.add(obtenerPosicionesButton);
        add(nuevo, BorderLayout.NORTH);
        add(piePanel, BorderLayout.SOUTH);

        crearMatrizButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean centinela = false;
                char[] caracteres = inputFilas.getText().toCharArray();
                char[] caracteres1 = inputColumnas.getText().toCharArray();
                for (int i = 0; i < inputFilas.getText().length(); i++) {
                    if (caracteres[i] == '1' || caracteres[i] == '2' || caracteres[i] == '3' || caracteres[i] == '4' || caracteres[i] == '5' || caracteres[i] == '6' || caracteres[i] == '7' || caracteres[i] == '8' || caracteres[i] == '9' || caracteres[i] == '0') {
                    } else {
                        centinela = true;
                        break;
                    }
                }
                for (int i = 0; i < inputColumnas.getText().length(); i++) {
                    if (caracteres1[i] == '1' || caracteres1[i] == '2' || caracteres1[i] == '3' || caracteres1[i] == '4' || caracteres1[i] == '5' || caracteres1[i] == '6' || caracteres1[i] == '7' || caracteres1[i] == '8' || caracteres1[i] == '9' || caracteres1[i] == '0') {
                    } else {
                        centinela = true;
                        break;
                    }
                }

                if (inputFilas.getText().isEmpty() || inputColumnas.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa el número de filas y columnas.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (centinela == false) {

                    filas = Integer.parseInt(inputFilas.getText());
                    columnas = Integer.parseInt(inputColumnas.getText());

                    matrizInput = new JTextField[filas][columnas];
                    matrizInput2 = new JTextField[filas][columnas];
                    fondoPanel = new JPanel(new GridLayout(0, 2));
                    matrizPanel = new JPanel(new GridLayout(filas + 1, columnas + 1, 5, 5));
                    matrizPanel2 = new JPanel(new GridLayout(filas + 1, columnas + 1, 5, 5));

                    // Agregar las etiquetas de las columnas
                    matrizPanel.add(new JLabel("Columnas"));
                    for (int j = 1; j <= columnas; j++) {
                        matrizPanel.add(new JLabel(Integer.toString(j)));
                    }

                    // Crear y mostrar la matriz de entrada editable
                    for (int i = 0; i < filas; i++) {
                        matrizPanel.add(new JLabel("Fila " + (i + 1)));
                        for (int j = 0; j < columnas; j++) {
                            if (j <= i) {
                                matrizInput[i][j] = new JTextField(2);
                                matrizInput[i][j].setText("0");
                                matrizPanel.add(matrizInput[i][j]);
                            } else {
                                matrizInput[i][j] = new JTextField(2);
                                matrizInput[i][j].setText("");
                                matrizInput[i][j].setEditable(false);
                                matrizPanel.add(matrizInput[i][j]);
                            }
                        }
                    }

                    // Agregar las etiquetas de las columnas
                    matrizPanel2.add(new JLabel("Columnas"));
                    for (int j = 1; j <= columnas; j++) {
                        matrizPanel2.add(new JLabel(Integer.toString(j)));
                    }

                    // Crear y mostrar la matriz de entrada editable
                    for (int i = 0; i < filas; i++) {
                        matrizPanel2.add(new JLabel("Fila " + (i + 1)));
                        for (int j = 0; j < columnas; j++) {
                            if (j >= i) {
                                matrizInput2[i][j] = new JTextField(2);
                                matrizInput2[i][j].setText("0");
                                matrizPanel2.add(matrizInput2[i][j]);
                            } else {
                                matrizInput2[i][j] = new JTextField(2);
                                matrizInput2[i][j].setText("");
                                matrizInput2[i][j].setEditable(false);
                                matrizPanel2.add(matrizInput2[i][j]);
                            }
                        }
                    }

                    JScrollPane scrollPane = new JScrollPane(matrizPanel);
                    JScrollPane scrollPane2 = new JScrollPane(matrizPanel2);
                    //JScrollPane scrollPane2 = new JScrollPane(matrizPanel);
                    removeAll();
                    fondoPanel.add(scrollPane, BorderLayout.CENTER, 0);
                    //fondoPanel.add(scrollPane2, BorderLayout.CENTER, 1);
                    add(fondoPanel);
                    fondoPanel.add(scrollPane2, BorderLayout.CENTER, 1);
                    //fondoPanel.add(scrollPane2, BorderLayout.CENTER, 1);
                    add(fondoPanel);

                    piePanel.add(obtenerPosicionesButton);
                    add(piePanel, BorderLayout.SOUTH);

                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo admite numeros enteros por favor rectifica tu entrada", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        obtenerPosicionesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (matrizInput != null) {
                    matriz = new Integer[filas + 1][columnas];

                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (matrizInput2[i][j].getText().equals("")) {
                                matriz[i][j] = null;
                            } else {
                                matriz[i][j] = Integer.parseInt(matrizInput2[i][j].getText());
                            }
                        }
                    }

                    for (int i = 1; i < filas + 1; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (matriz[i][j] == null) {
                                matriz[i][j] = Integer.parseInt(matrizInput[i - 1][j].getText());
                            }
                        }
                    }
                    // Mostrar los resultados en un cuadro de diálogo modal
                    removeAll();
                    add(mostrarArreglo(matriz, filas, columnas), BorderLayout.NORTH);
                    repaint();
                }
            }
        }
        );
    }

    public static JPanel mostrarArreglo(Integer[][] datos, int filas, int columnas) {
        //JFrame mp = new JFrame();
        JPanel mp = new JPanel();
        //mp.setDefaultCloseOperation(mp.EXIT_ON_CLOSE);
        mp.setPreferredSize(new Dimension(500, 400));
        mp.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);

        textArea.append("La matriz resultante de la unión de la matriz inferior y superior es:\n");
        for (int i = 0; i < filas + 1; i++) {
            for (int j = 0; j < columnas; j++) {
                if (j == 0) {
                    textArea.append("[" + datos[i][j]);
                } else if (j != columnas - 1) {
                    textArea.append(", " + datos[i][j]);
                } else {
                    textArea.append(", " + datos[i][j] + "]\n");
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        mp.add(scrollPane, BorderLayout.CENTER);

        //mp.setSize(300,200);
        //mp.setLocale(null);
        //mp.setVisible(true);
        return mp;
    }

}
