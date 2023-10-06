package Pruebas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Colas {

    private String[] colas;
    private int fin, inicio;
    private int TAMANIO_ARREGLO;

    public Colas() {
        TAMANIO_ARREGLO = 10;
        colas = new String[TAMANIO_ARREGLO];
        inicio = 0;
        fin = 0;
    }

    public void enqueue(String entrada) {
        if (colas[fin] == null && fin < TAMANIO_ARREGLO) {
            colas[fin] = entrada;
            if (fin != TAMANIO_ARREGLO - 1) {
                fin++;
            } else if (fin == TAMANIO_ARREGLO - 1) {
                fin = 0;
            } else {
                JOptionPane.showMessageDialog(null, "La cola esta llena", "Cola llena", JOptionPane.OK_OPTION);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La cola esta llena", "Cola llena", JOptionPane.OK_OPTION);
        }
    }

    public void dequeue() {
        boolean bandera = false;
        if (colas != null && inicio < TAMANIO_ARREGLO) {
            if (colas[inicio] != null) {
                System.out.println("eliminamos valor:" + colas[inicio]);
                colas[inicio] = null;
                bandera = true;
            }
            if (inicio != TAMANIO_ARREGLO - 1 && bandera == true) {
                inicio++;
            } else if (inicio == TAMANIO_ARREGLO - 1 || colas[0] != null) {
                inicio = 0;
            } else {
                JOptionPane.showMessageDialog(null, "La cola esta vacia", "Cola vacia", JOptionPane.OK_OPTION);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La cola esta vacia", "Cola vacia", JOptionPane.OK_OPTION);
        }
    }

    public String valor() {
        return colas[inicio];
    }

    @Override
    public String toString() {
        String valores = "";
        int temp = fin;
        for (int i = 0; i < TAMANIO_ARREGLO; i++) {
            if (colas[temp] != null && temp < TAMANIO_ARREGLO) {
                valores += "[" + colas[temp] + "] ";
                if (temp != TAMANIO_ARREGLO - 1) {
                    temp++;
                } else if (temp == TAMANIO_ARREGLO - 1) {
                    temp = 0;
                }
            } else {
                if (temp != TAMANIO_ARREGLO - 1) {
                    temp++;
                } else if (temp == TAMANIO_ARREGLO - 1) {
                    temp = 0;
                }
            }
        }

        return valores;
    }

    public String[] conexionAIG() {
        int contador = 0;
        for (int i = 0; i < TAMANIO_ARREGLO; i++) {
            if (colas[i] != null) {
                contador++;
            }
        }
        String[] datos = new String[contador];
        contador = 0;
        int temp = fin;
        for (int i = 0; i < TAMANIO_ARREGLO; i++) {
            if (colas[temp] != null && temp < TAMANIO_ARREGLO) {
                datos[contador] = colas[temp];
                contador++;
                if (temp != TAMANIO_ARREGLO - 1) {
                    temp++;
                } else if (temp == TAMANIO_ARREGLO - 1) {
                    temp = 0;
                }
            } else {
                if (temp != TAMANIO_ARREGLO - 1) {
                    temp++;
                } else if (temp == TAMANIO_ARREGLO - 1) {
                    temp = 0;
                }
            }
        }
        return datos;
    }

    public static void main(String[] args) {
        Colas colas = new Colas();
        System.out.println(colas.toString());
        colas.enqueue("Hola");
        colas.enqueue("Hola2");
        colas.dequeue();
        System.out.println(colas.valor());
        colas.enqueue("Adios");
        colas.enqueue("Adios2");
        System.out.println(colas.toString());
        System.out.println(Arrays.toString(colas.conexionAIG()));
        colas.dequeue();
        System.out.println(colas.valor());
        colas.dequeue();
        System.out.println(colas.valor());
        colas.dequeue();
        System.out.println(colas.toString());
        System.out.println(Arrays.toString(colas.conexionAIG()));
        colas.enqueue("NOOO");
        colas.enqueue("Hola no");
        System.out.println(colas.valor());
        colas.dequeue();
        System.out.println(colas.toString());
        System.out.println(Arrays.toString(colas.conexionAIG()));
        System.out.println(colas.valor());
        colas.enqueue("Hola no");
        colas.enqueue("Hola no");
        System.out.println(colas.toString());
        System.out.println(Arrays.toString(colas.conexionAIG()));
        colas.dequeue();
        colas.dequeue();
        colas.dequeue();
        colas.dequeue();
        System.out.println(colas.valor());
        colas.enqueue("hola otra vez");
        System.out.println(colas.toString());
        System.out.println(Arrays.toString(colas.conexionAIG()));
        System.out.println(colas.valor());
        colas.dequeue();
        colas.dequeue();
        colas.enqueue("hola Adios ya no");
        colas.dequeue();
        colas.dequeue();
        colas.enqueue("cah");
        colas.enqueue("chaaa");
        colas.enqueue("chaaaleee");
        colas.enqueue("chaaarros");
        colas.enqueue("chaaa");
        System.out.println(colas.valor());
        System.out.println(colas.toString());
        System.out.println(Arrays.toString(colas.conexionAIG()));
        colas.dequeue();
        colas.dequeue();
        colas.dequeue();
        colas.dequeue();
        colas.dequeue();
        colas.enqueue("cah");
        colas.enqueue("chaaa");

        System.out.println(colas.valor());
        System.out.println(colas.toString());
        System.out.println(Arrays.toString(colas.conexionAIG()));
    }

    public JPanel manejoDeCola() {
        JPanel tmp = new JPanel(new GridLayout(2, 0));
        JPanel botones = new JPanel(new FlowLayout());
        botones.setPreferredSize(new Dimension(550, 55));
        JButton btnColas = new JButton("Colas", new ImageIcon("diagrama.png"));
        JButton btnPilas = new JButton("Pilas", new ImageIcon("diagrama.png"));
        JButton btnColaDoble = new JButton("Cola de doble inserción", new ImageIcon("diagrama.png"));
        btnColas.setFont(new Font("Arial", Font.BOLD, 16));
        btnPilas.setFont(new Font("Arial", Font.BOLD, 16));
        btnColaDoble.setFont(new Font("Arial", Font.BOLD, 16));

        btnColas.setForeground(new Color(213, 159, 15));
        btnColas.setBackground(new Color(0, 43, 121));
        btnPilas.setForeground(new Color(213, 159, 15));
        btnPilas.setBackground(new Color(0, 43, 121));
        btnColaDoble.setForeground(new Color(213, 159, 15));
        btnColaDoble.setBackground(new Color(0, 43, 121));

        btnColas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tmp.removeAll();
                tablaHorizontal(new String[]{}, tmp);
                JPanel tempe = new JPanel(new GridLayout(0, 2));
                JPanel pan1 = new JPanel(new FlowLayout());
                JPanel pan2 = new JPanel(new FlowLayout());
                JButton btnEnqueue = new JButton("Enqueue");
                JTextField txtEnqueue = new JTextField(5);
                JButton btnDequeue = new JButton("Dequeue");
                btnEnqueue.setFont(new Font("Arial", Font.BOLD, 16));
                btnDequeue.setFont(new Font("Arial", Font.BOLD, 16));
                txtEnqueue.setFont(new Font("Arial", Font.BOLD, 17));
                txtEnqueue.setForeground(Color.BLACK);
                btnEnqueue.setForeground(new Color(213, 159, 15));
                btnEnqueue.setBackground(new Color(0, 43, 121));
                btnDequeue.setForeground(new Color(213, 159, 15));
                btnDequeue.setBackground(new Color(0, 43, 121));

                btnEnqueue.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnEnqueue.setBackground(new Color(213, 159, 15));
                        btnEnqueue.setForeground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnEnqueue.setForeground(new Color(213, 159, 15));
                        btnEnqueue.setBackground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (txtEnqueue.getText() != null) {
                            enqueue(txtEnqueue.getText());
                            tituloLabel.removeAll();
                            tablaHorizontal(conexionAIG(), tmp);
                            tituloLabel.repaint();
                            txtEnqueue.setText("");
                            System.out.println("Se esta encolando" + txtEnqueue.getText());
                        } else {
                            txtEnqueue.setText("");
                        }
                    }

                });

                btnDequeue.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnDequeue.setBackground(new Color(213, 159, 15));
                        btnDequeue.setForeground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnDequeue.setForeground(new Color(213, 159, 15));
                        btnDequeue.setBackground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        dequeue();
                        tituloLabel.removeAll();
                        tablaHorizontal(conexionAIG(), tmp);
                        tituloLabel.repaint();
                    }

                });

                pan1.add(txtEnqueue);
                pan1.add(btnEnqueue);
                pan2.add(btnDequeue);
                tempe.add(pan1, 0);
                tempe.add(pan2, 1);
                tmp.add(tempe, BorderLayout.CENTER);
                tmp.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnColas.setBackground(new Color(213, 159, 15));
                btnColas.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnColas.setForeground(new Color(213, 159, 15));
                btnColas.setBackground(new Color(0, 43, 121));
            }
        });

        btnPilas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPilas.setBackground(new Color(213, 159, 15));
                btnPilas.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnPilas.setForeground(new Color(213, 159, 15));
                btnPilas.setBackground(new Color(0, 43, 121));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                tmp.removeAll();
                tablaVertical(new String[]{}, tmp);
                JPanel tempe = new JPanel(new GridLayout(2, 0));
                JPanel pan1 = new JPanel(new FlowLayout());
                JPanel pan2 = new JPanel(new FlowLayout());
                JButton btnEnqueue = new JButton("Push");
                JTextField txtEnqueue = new JTextField(5);
                JButton btnDequeue = new JButton("Pop");
                btnEnqueue.setFont(new Font("Arial", Font.BOLD, 16));
                btnDequeue.setFont(new Font("Arial", Font.BOLD, 16));
                txtEnqueue.setFont(new Font("Arial", Font.BOLD, 17));
                txtEnqueue.setForeground(Color.BLACK);
                btnEnqueue.setForeground(new Color(213, 159, 15));
                btnEnqueue.setBackground(new Color(0, 43, 121));
                btnDequeue.setForeground(new Color(213, 159, 15));
                btnDequeue.setBackground(new Color(0, 43, 121));
                Pilas pila = new Pilas();
                btnEnqueue.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnEnqueue.setBackground(new Color(213, 159, 15));
                        btnEnqueue.setForeground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnEnqueue.setForeground(new Color(213, 159, 15));
                        btnEnqueue.setBackground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (txtEnqueue.getText() != null) {
                            pila.push(txtEnqueue.getText());
                            tituloLabel.removeAll();
                            tablaVertical(pila.conexionAIG(), tmp);
                            tituloLabel.repaint();
                            txtEnqueue.setText("");
                            System.out.println("Se esta encolando" + txtEnqueue.getText());
                        } else {
                            txtEnqueue.setText("");
                        }
                    }

                });

                btnDequeue.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnDequeue.setBackground(new Color(213, 159, 15));
                        btnDequeue.setForeground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnDequeue.setForeground(new Color(213, 159, 15));
                        btnDequeue.setBackground(new Color(0, 43, 121));
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        pila.pop();
                        tituloLabel.removeAll();
                        tablaVertical(pila.conexionAIG(), tmp);
                        tituloLabel.repaint();
                    }

                });

                pan1.add(txtEnqueue);
                pan1.add(btnEnqueue);
                pan2.add(btnDequeue);
                tempe.add(pan1, 0);
                tempe.add(pan2, 1);
                tmp.add(tempe, BorderLayout.CENTER);
                tmp.repaint();
            }
        });

        btnColaDoble.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnColaDoble.setBackground(new Color(213, 159, 15));
                btnColaDoble.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnColaDoble.setForeground(new Color(213, 159, 15));
                btnColaDoble.setBackground(new Color(0, 43, 121));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ListaCircular listaCircular = new ListaCircular();
            }
        });

        botones.add(btnColas);
        botones.add(btnPilas);
        botones.add(btnColaDoble);

        tmp.add(botones, 0);

        return tmp;
    }

    public JPanel tablaHorizontal(String[] arreglo, JPanel tmp) {
        tmp.setLayout(new BorderLayout());
        JPanel temp = new JPanel(new FlowLayout());
        JPanel tabla = new JPanel(new GridLayout(0, 12));
        JPanel[] arreg = new JPanel[12];
        tituloLabel = new JLabel("Operaciones con una Cola", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 22));
        tituloLabel.setForeground(Color.BLACK);
        JLabel inicio = new JLabel("Inicio");
        inicio.setFont(new Font("Arial", Font.BOLD, 13));
        JLabel fin = new JLabel("Fin");
        fin.setFont(new Font("Arial", Font.BOLD, 13));
        inicio.setForeground(Color.BLACK);
        fin.setForeground(Color.BLACK);
        for (int i = 0; i < 12; i++) {
            arreg[i] = new JPanel(new FlowLayout());
            arreg[i].setPreferredSize(new Dimension(55, 50));
            arreg[i].setBorder(new LineBorder(Color.BLACK, 2));
            tabla.add(arreg[i], i);
        }
        byte contador = 10;
        for (int i = 0; i < arreglo.length; i++) {
            JLabel[] lbls = new JLabel[arreglo.length];
            lbls[i] = new JLabel(arreglo[i]);
            lbls[i].setForeground(Color.BLACK);
            System.out.println("Agregamos a la IG" + arreglo[i]);
            arreg[contador].add(lbls[i]);
            contador--;
        }

        arreg[0].setBorder(null);
        arreg[11].setBorder(null);
        arreg[0].add(fin);
        arreg[11].add(inicio);
        temp.setPreferredSize(new Dimension(600, 100));
        tabla.setSize(600, 250);
        temp.add(tituloLabel);
        temp.setPreferredSize(new Dimension(600, 100));
        temp.add(tabla);
        JScrollPane redimesion = new JScrollPane(temp);
        tmp.add(redimesion, BorderLayout.NORTH);
        return tmp;
    }
    private JLabel tituloLabel;

    public JPanel tablaVertical(String[] arreglo, JPanel tmp) {
        tmp.setLayout(new BorderLayout());
        JPanel temp = new JPanel(new FlowLayout());
        JPanel tabla = new JPanel(new GridLayout(10, 0));
        JPanel[] arreg = new JPanel[10];
        tituloLabel = new JLabel("Operaciones con una Cola", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 22));
        tituloLabel.setForeground(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            arreg[i] = new JPanel(new FlowLayout());
            arreg[i].setPreferredSize(new Dimension(100, 36));
            arreg[i].setBorder(new LineBorder(Color.BLACK, 2));
            tabla.add(arreg[i], i);
        }
        byte contador = 9;
        for (int i = 0; i < arreglo.length; i++) {
            JLabel[] lbls = new JLabel[arreglo.length];
            lbls[i] = new JLabel(arreglo[i]);
            lbls[i].setForeground(Color.BLACK);
            System.out.println("Agregamos a la IG" + arreglo[i]);
            arreg[contador].add(lbls[i]);
            contador--;
        }
        tabla.setSize(600, 250);
        temp.add(tituloLabel);
        temp.setPreferredSize(new Dimension(350, 500));
        temp.add(tabla);
        JScrollPane redimesion = new JScrollPane(temp);
        tmp.add(temp, BorderLayout.WEST, 0);
        return tmp;
    }
}
