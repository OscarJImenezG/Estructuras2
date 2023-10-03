package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

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
    private JPanel aux;
    private JPanel aux1;
    private byte key;

    private Connection conexion;

    public MatriceSupeInfer() {
    }

    public MatriceSupeInfer(Connection conexion) {
        this.conexion = conexion;
    }

    public void mostrarVentana() {
        PanelPrincipal panelPrincipal = new PanelPrincipal(conexion);
        panelPrincipal.mostrarVentana();
    }

    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();
        if (conexion != null) {
            Inicio inicio = new Inicio(conexion);
            inicio.mostrarVentana();
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }
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

        crearMatrizButton = new DisenoBotones("Crear Matrices", 160);
        obtenerPosicionesButton = new DisenoBotones("Juntar Matrices", 190);

        obtenerPosicionesButton.setVisible(false);
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
                removeAll();
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

                if (inputFilas.getText().isEmpty() || inputColumnas.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingresa el número de filas y columnas.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (centinela == false) {

                    filas = Integer.parseInt(inputFilas.getText());
                    columnas = Integer.parseInt(inputColumnas.getText());

                    matrizInput = new JTextField[filas][columnas];
                    matrizInput2 = new JTextField[filas][columnas];
                    fondoPanel = new JPanel(new GridLayout(0, 2));
                    DisenoBotones matSuperior0 = new DisenoBotones("Crear Matriz superior", 235, new ImageIcon("superior.png"));
                    DisenoBotones matInferior0 = new DisenoBotones("Crear Matriz Inferior", 235, new ImageIcon("inferior.png"));
                    DisenoBotones matSuperior1 = new DisenoBotones("Crear Matriz superior", 235, new ImageIcon("superior.png"));
                    DisenoBotones matInferior1 = new DisenoBotones("Crear Matriz Inferior", 235, new ImageIcon("inferior.png"));
                    JPanel aux = new JPanel();
                    aux.setSize(300, 400);
                    JPanel aux1 = new JPanel();
                    aux1.setSize(300, 400);
                    aux.add(matSuperior0);
                    aux.add(matInferior0);
                    aux1.add(matSuperior1);
                    aux1.add(matInferior1);
                    fondoPanel.add(aux, BorderLayout.CENTER, 0);
                    fondoPanel.add(aux1, BorderLayout.CENTER, 1);
                    add(fondoPanel);

                    matInferior0.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
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
                            JScrollPane scrollPane = new JScrollPane(matrizPanel);
                            removeAll();
                            fondoPanel.remove(aux);
                            fondoPanel.add(scrollPane, BorderLayout.CENTER, 0);
                            add(fondoPanel);

                            piePanel.add(obtenerPosicionesButton);
                            add(piePanel, BorderLayout.SOUTH);

                            revalidate();
                            repaint();
                            key += 1;
                            System.out.println("Estamos en caso matriz inferior sumamos 1");
                        }
                    });

                    matSuperior0.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
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
                                    if (j >= i) {
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
                            JScrollPane scrollPane2 = new JScrollPane(matrizPanel);
                            removeAll();
                            fondoPanel.remove(aux);
                            fondoPanel.add(scrollPane2, BorderLayout.CENTER, 0);
                            add(fondoPanel);

                            piePanel.add(obtenerPosicionesButton);
                            add(piePanel, BorderLayout.SOUTH);

                            revalidate();
                            repaint();
                            key += 2;
                            System.out.println("Estamos en caso matriz superior sumamos 2");
                        }
                    });

                    matInferior1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            matrizPanel2 = new JPanel(new GridLayout(filas + 1, columnas + 1, 5, 5));
                            // Agregar las etiquetas de las columnas
                            matrizPanel2.add(new JLabel("Columnas"));
                            for (int j = 1; j <= columnas; j++) {
                                matrizPanel2.add(new JLabel(Integer.toString(j)));
                            }

                            // Crear y mostrar la matriz de entrada editable
                            for (int i = 0; i < filas; i++) {
                                matrizPanel2.add(new JLabel("Fila " + (i + 1)));
                                for (int j = 0; j < columnas; j++) {
                                    if (j <= i) {
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
                            JScrollPane scrollPane2 = new JScrollPane(matrizPanel2);
                            removeAll();
                            fondoPanel.remove(aux1);
                            fondoPanel.add(scrollPane2, BorderLayout.CENTER, 1);
                            add(fondoPanel);

                            piePanel.add(obtenerPosicionesButton);
                            add(piePanel, BorderLayout.SOUTH);

                            revalidate();
                            repaint();
                            key += 1;
                            System.out.println("Estamos en caso matriz inferior sumamos 1");
                        }
                    });

                    matSuperior1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            matrizPanel2 = new JPanel(new GridLayout(filas + 1, columnas + 1, 5, 5));
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
                            JScrollPane scrollPane = new JScrollPane(matrizPanel2);
                            removeAll();
                            fondoPanel.remove(aux1);
                            fondoPanel.add(scrollPane, BorderLayout.CENTER, 1);
                            add(fondoPanel);

                            piePanel.add(obtenerPosicionesButton);
                            add(piePanel, BorderLayout.SOUTH);

                            revalidate();
                            repaint();
                            key += 2;
                            System.out.println("Estamos en caso matriz superor sumamos 1");
                        }
                    });
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
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            System.out.println(matrizInput[i][j].getText());
                        }
                    }
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            System.out.println(matrizInput2[i][j].getText());
                        }
                    }
                    matriz = new Integer[filas][columnas + 1];
                    int[][] temp = new int[filas][columnas];

                    //Todos los espacios en blanco sobrescribe un cero
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            if (matrizInput[i][j].getText().equals("")) {
                                matrizInput[i][j].setText("0");
                            }
                            if (matrizInput2[i][j].getText().equals("")) {
                                matrizInput2[i][j].setText("0");
                            }
                        }
                    }

                    System.out.println("Testeo de las matrices de espacio a ceros");
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            System.out.println(matrizInput[i][j].getText());
                        }
                    }
                    for (int i = 0; i < filas; i++) {
                        for (int j = 0; j < columnas; j++) {
                            System.out.println(matrizInput2[i][j].getText());
                        }
                    }

                    switch (key) {
                        case 2:

                            //Ingresamos datos de la matriz B en una matriz de referencia
                            System.out.println("Testeo de temporal");
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas; j++) {
                                    temp[i][j] = Integer.parseInt(matrizInput2[i][j].getText());
                                    System.out.println(temp[i][j]);
                                    if (matrizInput2[i][j].getText().equals("0")) {
                                        temp[i][j] = 0;
                                    }
                                }
                            }
                            //Rotamos la matriz B
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas; j++) {
                                    if (j <= i) {
                                        matrizInput2[j][i].setText(matrizInput2[i][j].getText());
                                        if (j != i) {
                                            matrizInput2[i][j].setText("0");
                                        }
                                        if (matrizInput2[i][j].equals("0")) {
                                            matrizInput2[i][j].setText("0");
                                        }
                                    }
                                }
                            }
                            System.out.println("Testeo de rotaciones");
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas; j++) {
                                    System.out.println(matrizInput2[i][j].getText());
                                }
                            }

                            //Asignamos la matriz A en matriz
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas; j++) {
                                    matriz[i][j] = Integer.parseInt(matrizInput[i][j].getText());
                                }
                            }

                            for (int i = 0; i < filas; i++) {
                                for (int j = 1; j < columnas + 1; j++) {
                                    if (matriz[i][j] == null) {
                                        matriz[i][j] = Integer.parseInt(matrizInput2[i][j - 1].getText());
                                    } else if (matriz[i][j] == 0) {
                                        matriz[i][j] = Integer.parseInt(matrizInput2[i][j - 1].getText());
                                    }
                                }
                            }

                            System.out.println("Testeo de matriz completa");
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas + 1; j++) {
                                    System.out.println(matriz[i][j]);
                                }
                            }

                            removeAll();
                            add(mostrarArreglo(matriz, filas, columnas, temp, matrizInput), BorderLayout.NORTH);
                            repaint();
                            break;
                        case 4:

                            //Ingresamos datos de la matriz B en una matriz de referencia
                            System.out.println("Testeo de temporal");
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas; j++) {
                                    temp[i][j] = Integer.parseInt(matrizInput2[i][j].getText());
                                    System.out.println(temp[i][j]);
                                    if (matrizInput2[i][j].getText().equals("0")) {
                                        temp[i][j] = 0;
                                    }
                                }
                            }

                            //Rotamos la matriz B
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas; j++) {
                                    if (j >= i) {
                                        matrizInput2[j][i].setText(matrizInput2[i][j].getText());
                                        if (j != i) {
                                            matrizInput2[i][j].setText("0");
                                        }
                                        if (matrizInput2[i][j].equals("0")) {
                                            matrizInput2[i][j].setText("0");
                                        }
                                    }
                                }
                            }

                            //Asignamos la matriz B en matriz
                            for (int i = 0; i < filas; i++) {
                                for (int j = 0; j < columnas; j++) {
                                    matriz[i][j] = Integer.parseInt(matrizInput2[i][j].getText());
                                }
                            }

                            for (int i = 0; i < filas; i++) {
                                for (int j = 1; j < columnas + 1; j++) {
                                    if (matriz[i][j] == null) {
                                        matriz[i][j] = Integer.parseInt(matrizInput[i][j - 1].getText());
                                    } else if (matriz[i][j] == 0) {
                                        matriz[i][j] = Integer.parseInt(matrizInput[i][j - 1].getText());
                                    }
                                }
                            }

                            removeAll();
                            add(mostrarArreglo(matriz, filas, columnas, temp, matrizInput), BorderLayout.NORTH);
                            repaint();
                            break;

                        case 3:
                            if (matrizInput2[0][1].getText().equals("0")) {
                                //Ingresamos datos de la matriz B en una matriz de referencia
                                System.out.println("Testeo de temporal");
                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        temp[i][j] = Integer.parseInt(matrizInput2[i][j].getText());
                                        System.out.println(temp[i][j]);
                                        if (matrizInput2[i][j].getText().equals("0")) {
                                            temp[i][j] = 0;
                                        }
                                    }
                                }
                                //Asignamos la matriz B en matriz
                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        matriz[i][j] = Integer.parseInt(matrizInput2[i][j].getText());
                                    }
                                }

                                for (int i = 0; i < filas; i++) {
                                    for (int j = 1; j < columnas + 1; j++) {
                                        if (matriz[i][j] == null) {
                                            matriz[i][j] = Integer.parseInt(matrizInput[i][j - 1].getText());
                                        } else if (matriz[i][j] == 0) {
                                            matriz[i][j] = Integer.parseInt(matrizInput[i][j - 1].getText());
                                        }
                                    }
                                }
                                removeAll();
                                add(mostrarArreglo(matriz, filas, columnas, temp, matrizInput), BorderLayout.NORTH);
                                repaint();
                            } else {
                                System.out.println("Testeo de temporal");
                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        temp[i][j] = Integer.parseInt(matrizInput[i][j].getText());
                                        System.out.println(temp[i][j]);
                                        if (matrizInput[i][j].getText().equals("0")) {
                                            temp[i][j] = 0;
                                        }
                                    }
                                }
                                //Asignamos la matriz B en matriz
                                for (int i = 0; i < filas; i++) {
                                    for (int j = 0; j < columnas; j++) {
                                        matriz[i][j] = Integer.parseInt(matrizInput[i][j].getText());
                                    }
                                }

                                for (int i = 0; i < filas; i++) {
                                    for (int j = 1; j < columnas + 1; j++) {
                                        if (matriz[i][j] == null) {
                                            matriz[i][j] = Integer.parseInt(matrizInput2[i][j - 1].getText());
                                        } else if (matriz[i][j] == 0) {
                                            matriz[i][j] = Integer.parseInt(matrizInput2[i][j - 1].getText());
                                        }
                                    }
                                }

                                removeAll();
                                add(mostrarArreglo(matriz, filas, columnas, temp, matrizInput2), BorderLayout.NORTH);
                                repaint();

                            }

                            break;
                    }

                    // Mostrar los resultados en un cuadro de diálogo modal
                }
            }
        }
        );
    }

    private static JPanel mostrarArreglo(Integer[][] datos, int filas, int columnas, int[][] tempe, JTextField[][] text) {

        JPanel pan = new JPanel(new GridLayout(0, 2));
        pan.setPreferredSize(new Dimension(500, 400));

        JPanel panel = new JPanel(new GridLayout(2, 0));
        pan.setPreferredSize(new Dimension(250, 400));

        JTextArea matrizA = new JTextArea();
        matrizA.setFont(new Font("Arial", Font.PLAIN, 18));
        matrizA.setEditable(false);

        JTextArea matrizB = new JTextArea();
        matrizB.setFont(new Font("Arial", Font.PLAIN, 18));
        matrizB.setEditable(false);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (j == 0) {
                    matrizA.append("[" + Integer.toString(Integer.parseInt(text[i][j].getText())));
                } else if (j != columnas - 1) {
                    matrizA.append(" " + Integer.toString(Integer.parseInt(text[i][j].getText())));
                } else {
                    matrizA.append(" " + Integer.toString(Integer.parseInt(text[i][j].getText())) + "]\n");
                }
            }
        }
        matrizA.append("Matriz A");

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (j == 0) {
                    matrizB.append("[" + Integer.toString(tempe[i][j]));
                } else if (j != columnas - 1) {
                    matrizB.append(" " + Integer.toString(tempe[i][j]));
                } else {
                    matrizB.append(" " + Integer.toString(tempe[i][j]) + "]\n");
                }
            }
        }
        matrizB.append("Matriz B");

        JScrollPane jc = new JScrollPane(matrizA);
        JScrollPane js = new JScrollPane(matrizB);
        panel.add(jc, FlowLayout.CENTER, 0);
        panel.add(js, FlowLayout.CENTER, 1);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setEditable(false);

        //Obtenemos posicion mas chica
        String espacio = "";
        int pocisiones = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (Integer.toString(datos[i][j]).length() > pocisiones) {
                    pocisiones = 0;
                    pocisiones = Integer.toString(datos[i][j]).length();
                }
            }
        }

        System.out.println("pocisiones " + pocisiones);
        //Las posiciones las agregamos a un String de espacios vacios
        for (int i = 0; i < pocisiones; i++) {
            espacio += " ";
        }

        //Ocurre la magia le da formato a la matriz
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas + 1; j++) {
                if (j == 0) {
                    if (Integer.toString(datos[i][j]).length() > 1) {
                        String temp;
                        if ((Integer.toString(datos[i][j]).length()) + 1 == 1) {
                            temp = "  ";
                        } else {
                            temp = " ";
                        }
                        for (int k = espacio.length() - (Integer.toString(datos[i][j]).length()); k > 0; k--) {
                            temp += " ";
                        }
                        textArea.append("[" + datos[i][j] + temp);
                    } else {
                        textArea.append("[" + datos[i][j] + espacio);
                    }
                } else if (j != filas) {
                    if (Integer.toString(datos[i][j]).length() > 1) {
                        String temp;
                        if ((Integer.toString(datos[i][j]).length()) + 1 == 1) {
                            temp = "  ";
                        } else {
                            temp = " ";
                        }
                        for (int k = espacio.length() - (Integer.toString(datos[i][j]).length()); k > 0; k--) {
                            temp += " ";
                        }
                            textArea.append(datos[i][j] + temp);
                        }else {
                        textArea.append(datos[i][j] + espacio);
                    }
                    } else if (i < j) {
                        if (Integer.toString(datos[i][j]).length() > 1) {
                            String temp = "";
                            for (int k = (espacio.length() - (Integer.toString(datos[i][j]).length())); k > 0; k--) {
                                temp += " ";
                            }
                            textArea.append(datos[i][j] + temp + "]\n");
                        } else {
                            textArea.append(datos[i][j] + "]\n");
                        }
                    }
                }
            }

            textArea.append("Matriz Resultante");

            pan.add(panel, FlowLayout.CENTER, 0);
            JScrollPane jx = new JScrollPane(textArea);
            pan.add(jx, FlowLayout.CENTER, 1);

            return pan;
        }

    }
