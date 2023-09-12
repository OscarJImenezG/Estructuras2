package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatrizDispersaPanel extends JPanel {

    private static JTextField[][] matrizInput;
    private static final List<Integer[]> datosMatriz = new ArrayList<>(); // Lista para almacenar los datos y sus posiciones
    private JPanel matrizPanel;
    public DisenoBotones crearMatrizButton;
    public DisenoBotones obtenerPosicionesButton;

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

        crearMatrizButton = new DisenoBotones("Crear Matriz", 160);
        obtenerPosicionesButton = new DisenoBotones("Obtener Posiciones", 190);

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

                String filasText = inputFilas.getText();
                String columnasText = inputColumnas.getText();

                if (filasText.isEmpty() || columnasText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa el número de filas y columnas.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (centinela == false) {

                    int filas = Integer.parseInt(filasText);
                    int columnas = Integer.parseInt(columnasText);

                    matrizInput = new JTextField[filas][columnas];

                    datosMatriz.clear(); // Limpiar la lista de datos y posiciones

                    matrizPanel = new JPanel(new GridLayout(filas + 1, columnas + 1, 5, 5));

                    // Agregar las etiquetas de las columnas
                    matrizPanel.add(new JLabel("Columnas"));
                    for (int j = 1; j <= columnas; j++) {
                        matrizPanel.add(new JLabel(Integer.toString(j)));
                    }

                    // Crear y mostrar la matriz de entrada editable
                    for (int i = 0; i < filas; i++) {
                        matrizPanel.add(new JLabel("Fila " + (i + 1)));
                        for (int j = 0; j < columnas; j++) {
                            matrizInput[i][j] = new JTextField(2);
                            matrizInput[i][j].setText("0");
                            matrizPanel.add(matrizInput[i][j]);
                        }
                    }

                    JScrollPane scrollPane = new JScrollPane(matrizPanel);
                    add(scrollPane, BorderLayout.CENTER);

                    revalidate();
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo admite numeros enteros por favor rectifica tu entrada", "Error en la entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );

        obtenerPosicionesButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (matrizInput != null) {
                    datosMatriz.clear();

                    int filas = matrizInput.length;
                    int columnas = matrizInput[0].length;

                    for (int j = 0; j < columnas; j++) {
                        for (int i = 0; i < filas; i++) {
                            int valor = Integer.parseInt(matrizInput[i][j].getText());
                            if (valor != 0) {
                                datosMatriz.add(new Integer[]{j + 1, i + 1, valor});
                            }
                        }
                    }
                    // Mostrar los resultados en un cuadro de diálogo modal

                    removeAll();
                    add(mostrarPosicionesEnPantalla(datosMatriz), BorderLayout.NORTH);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Primero crea una matriz.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
    }

    public static JPanel mostrarPosicionesEnPantalla(List<Integer[]> datos) {
        //JFrame mp = new JFrame();
        JPanel mp = new JPanel();
        //mp.setDefaultCloseOperation(mp.EXIT_ON_CLOSE);
        mp.setPreferredSize(new Dimension(500, 400));
        mp.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);

        for (Integer[] dato : datos) {
            int columna = dato[0];
            int fila = dato[1];
            int valor = dato[2];
            textArea.append("Fila: " + fila + ", Columna: " + columna + ", Valor: " + valor + "\n");
        } 

        JScrollPane scrollPane = new JScrollPane(textArea);
        mp.add(scrollPane, BorderLayout.CENTER);
        //mp.setSize(300,200);
        //mp.setLocale(null);
        //mp.setVisible(true);
        return mp;
    }
}
