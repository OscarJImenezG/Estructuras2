package Pruebas;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.util.ArrayList;
        import java.util.List;

public class MatrizDispersaPanel extends JFrame{
    private static JTextField[][] matrizInput;
    private static List<Integer[]> datosMatriz = new ArrayList<>(); // Lista para almacenar los datos y sus posiciones

    public MatrizDispersaPanel(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(Short.MAX_VALUE,Short.MAX_VALUE);
        setLayout(new FlowLayout());

        JLabel labelFilas = new JLabel("Filas:");
        JTextField inputFilas = new JTextField(5);

        JLabel labelColumnas = new JLabel("Columnas:");
        JTextField inputColumnas = new JTextField(5);

        JButton crearMatrizButton = new JButton("Crear Matriz");
        JButton obtenerPosicionesButton = new JButton("Obtener Posiciones");

        getContentPane().add(labelFilas);
        getContentPane().add(inputFilas);
        getContentPane().add(labelColumnas);
        getContentPane().add(inputColumnas);
        getContentPane().add(crearMatrizButton);

        JPanel piePanel = new JPanel();
        piePanel.add(obtenerPosicionesButton);
        getContentPane().add(piePanel, BorderLayout.SOUTH);

        crearMatrizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filas = Integer.parseInt(inputFilas.getText());
                int columnas = Integer.parseInt(inputColumnas.getText());

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
                getContentPane().add(scrollPane, BorderLayout.CENTER);

                getContentPane().revalidate();
                getContentPane().repaint();
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


                    System.out.print("Columnas ----> ");
                    for (int i = 1; i <= columnas; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    System.out.print("Fils   -------> ");
                    for (Integer[] dato : datosMatriz) {
                        int fila = dato[1];
                        System.out.print(fila + " ");
                    }
                    System.out.println();
                    System.out.print("Valor  -------> ");
                    for (Integer[] dato : datosMatriz) {
                        int valor = dato[2];
                        System.out.print(valor + " ");
                    }
                    System.out.println();
                } else {

                    JOptionPane.showMessageDialog(null, "Primero crea una matriz.");
                }
            }
        });

        setVisible(true);

    }

    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/
        MatrizDispersaPanel m = new MatrizDispersaPanel("Panel");
    }

    /*private static void createAndShowGUI() {
        JFrame frame = new JFrame("Matriz Dispersa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                int filas = Integer.parseInt(inputFilas.getText());
                int columnas = Integer.parseInt(inputColumnas.getText());

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

                frame.revalidate();
                frame.repaint();
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


                    System.out.print("Columnas ----> ");
                    for (int i = 1; i <= columnas; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
                    System.out.print("Fils   -------> ");
                    for (Integer[] dato : datosMatriz) {
                        int fila = dato[1];
                        System.out.print(fila + " ");
                    }
                    System.out.println();
                    System.out.print("Valor  -------> ");
                    for (Integer[] dato : datosMatriz) {
                        int valor = dato[2];
                        System.out.print(valor + " ");
                    }
                    System.out.println();
                } else {

                    JOptionPane.showMessageDialog(frame, "Primero crea una matriz.");
                }
            }
        });

        frame.setVisible(true);
    }*/
}