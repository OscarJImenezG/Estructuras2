package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;

public class MatrizDispersaPanel {
    private static JTextField[][] matrizInput;
    private static List<Integer[]> datosMatriz = new ArrayList<>(); // Lista para almacenar los datos y sus posiciones

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    static void createAndShowGUI() {
        JFrame frame = new JFrame("Matriz Dispersa");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel labelFilas = new JLabel("Filas:");
        JTextField inputFilas = new JTextField(5);

        JLabel labelColumnas = new JLabel("Columnas:");
        JTextField inputColumnas = new JTextField(5);

        JButton crearMatrizButton = new JButton("Crear Matriz");
        JButton obtenerPosicionesButton = new JButton("Obtener Posiciones");

        inputPanel.add(labelFilas);
        inputPanel.add(inputFilas);
        inputPanel.add(labelColumnas);
        inputPanel.add(inputColumnas);
        inputPanel.add(crearMatrizButton);

        JPanel piePanel = new JPanel();
        piePanel.add(obtenerPosicionesButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(piePanel, BorderLayout.SOUTH);

        crearMatrizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filasText = inputFilas.getText();
                String columnasText = inputColumnas.getText();

                if (filasText.isEmpty() || columnasText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Ingresa el número de filas y columnas.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int filas = Integer.parseInt(filasText);
                    int columnas = Integer.parseInt(columnasText);

                    matrizInput = new JTextField[filas][columnas];

                    datosMatriz.clear(); // Limpiar la lista de datos y posiciones

                    JPanel matrizPanel = new JPanel(new GridLayout(filas + 1, columnas + 1, 5, 5));

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
                    frame.add(scrollPane, BorderLayout.CENTER);

                    // Centrar la ventana en la pantalla
                    frame.setLocationRelativeTo(null);

                    frame.revalidate();
                    frame.repaint();
                }
            }
        });

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
                    mostrarPosicionesEnPantalla(datosMatriz);
                } else {
                    JOptionPane.showMessageDialog(frame, "Primero crea una matriz.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Centrar la ventana principal en la pantalla
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private static void mostrarPosicionesEnPantalla(List<Integer[]> datos) {
        JFrame frame = new JFrame("Posiciones en Pantalla");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        for (Integer[] dato : datos) {
            int columna = dato[0];
            int fila = dato[1];
            int valor = dato[2];
            textArea.append("Fila: " + fila + ", Columna: " + columna + ", Valor: " + valor + "\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}