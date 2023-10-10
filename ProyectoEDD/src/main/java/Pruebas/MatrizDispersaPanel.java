package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrizDispersaPanel extends JPanel {

    private static JTextField[][] matrizInput;
    private int[][] matrizCompacta;
    private int espacioAReservar, filas, columnas;
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

        obtenerPosicionesButton.setVisible(false);
        JPanel piePanel = new JPanel();
        piePanel.add(obtenerPosicionesButton);
        add(nuevo, BorderLayout.NORTH);
        add(piePanel, BorderLayout.SOUTH);

        crearMatrizButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                obtenerPosicionesButton.setVisible(true);
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

                filas = Integer.parseInt(inputFilas.getText());
                columnas = Integer.parseInt(inputColumnas.getText());
                String filasText = Integer.toString(filas);
                String columnasText = Integer.toString(columnas);

                if (filasText.isEmpty() || columnasText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa el número de filas y columnas.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (centinela == false) {

                    int filas = Integer.parseInt(filasText);
                    int columnas = Integer.parseInt(columnasText);

                    matrizInput = new JTextField[filas][columnas];

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

            @Override
            public void actionPerformed(ActionEvent e) {
                if (matrizInput != null) {

                    for (int j = 0; j < columnas; j++) {
                        for (int i = 0; i < filas; i++) {
                            int valor = Integer.parseInt(matrizInput[i][j].getText());
                            if (valor != 0) {
                                espacioAReservar++;
                            }
                        }
                    }
                    matrizCompacta = new int[3][espacioAReservar];
                    int k = 0;

                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (matrizInput[i][j].getText().equals("0")) {
                            } else {
                                matrizCompacta[0][k] = i + 1;
                                matrizCompacta[1][k] = j + 1;
                                matrizCompacta[2][k] = Integer.parseInt(matrizInput[i][j].getText());
                                k++;
                            }
                        }
                    }
                    // Mostrar los resultados en un cuadro de diálogo modal
                    System.out.println("Testeo de la matris dispersa");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < espacioAReservar; j++) {
                            System.out.print(matrizCompacta[i][j] + "");
                        }
                        System.out.println("");
                    }

                    removeAll();
                    add(mostrarPosicionesEnPantalla(matrizCompacta, espacioAReservar, matrizInput, filas, columnas, new MatrizDispersa(filas, columnas, matrizInput), new MatrizCompacta(3, espacioAReservar, matrizCompacta)), BorderLayout.NORTH);
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(null, "Primero crea una matriz.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
    }

    public static JPanel mostrarPosicionesEnPantalla(int[][] datos, int i, JTextField[][] txt, int filas, int columnas, MatrizDispersa dispersa, MatrizCompacta compacta) {
        JFrame ventana = new JFrame("Fuentes");
        ventana.add(dispersa);
        ventana.setSize(800, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mp = new JPanel(new GridLayout(0, 2));
        mp.setPreferredSize(new Dimension(500, 400));
        JScrollPane scroll = new JScrollPane(dispersa);
        JScrollPane scrollPane = new JScrollPane(compacta);
        mp.add(dispersa, 0);
        mp.add(compacta, 1);
        return mp;
    }

    public class MatrizDispersa extends JPanel {

        int filas, columnas;
        JTextField[][] matrizInput;

        public MatrizDispersa(int filas, int columnas, JTextField[][] matrizInput) {
            this.filas = filas;
            this.columnas = columnas;
            this.matrizInput = matrizInput;
        }

        @Override
        public void paint(Graphics g) {
            int pocisiones = 0;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (matrizInput[i][j].getText().length() > pocisiones) {
                        pocisiones = 0;
                        pocisiones = matrizInput[i][j].getText().length();
                        if (pocisiones < 2) {
                            pocisiones = 2;
                        }
                    }
                }
            }
            super.paint(g);
            pocisiones = (int) (pocisiones / 2);
            int x = 15, y = 30, z = 0;
            System.out.println(filas + " y " + columnas);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    g.drawString(Integer.toString(Integer.parseInt(matrizInput[i][j].getText())), x, y);
                    x += 45 * pocisiones;
                }
                y += 25;
                z = x;
                x = 15;
                System.out.println(z);
            }
            g.drawString("Matriz Dispersa", x, y);
            g.drawLine(10, 12, 10, y - 22);
            g.drawLine(10, 12, 20, 12);
            g.drawLine(z - 23, 12, z - 15, 12);
            g.drawLine(z - 15, 12, z - 15, y - 22);
            g.drawLine(10, y - 22, 20, y - 22);
            g.drawLine(z - 23, y - 22, z - 15, y - 22);
        }

    }

    public class MatrizCompacta extends JPanel {

        int filas, columnas;
        int[][] datos;

        public MatrizCompacta(int filas, int columnas, int[][] datos) {
            this.filas = filas;
            this.columnas = columnas;
            this.datos = datos;
        }

        @Override
        public void paint(Graphics g) {
            int pocisiones = 0;
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    if (Integer.toString(datos[i][j]).length() > pocisiones) {
                        pocisiones = 0;
                        pocisiones = ("0" + Integer.toString(0 + datos[i][j])).length();
                        if (pocisiones < 2) {
                            pocisiones = 2;
                        }
                    }
                }
            }
            super.paint(g);
            int x = 15, y = 30, z = 0;
            pocisiones = (int) (pocisiones / 2);
            g.setFont(new Font("Arial", Font.BOLD, 18));
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    g.drawString(Integer.toString(datos[i][j]), x, y);
                    x += (30 * pocisiones);
                }
                y += 25;
                z = x;
                x = 15;
                System.out.println(z);
            }
            g.drawString("Matriz Compacta", x, y);
            g.drawLine(10, 12, 10, y - 22);
            g.drawLine(10, 12, 20, 12);
            g.drawLine(z - 15, 12, z - 15, y - 22);
            g.drawLine(10, y - 22, 20, y - 22);
            g.drawLine(z - 23, 12, z - 15, 12);
            g.drawLine(z - 23, 12, z - 15, 12);
            g.drawLine(z - 23, y - 22, z - 15, y - 22);

        }
    }
}
