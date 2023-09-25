package Pruebas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Colas {

    private Integer[] colas;
    private Colas cola;
    private static final int TAMANIO_ARREGLO = 100;

    public Colas() {
        //Creamos el arreglo de enteros para trabajar sobre el.
        colas = new Integer[TAMANIO_ARREGLO];
    }

    public void enqueue(int entrada) {
        if (colas[TAMANIO_ARREGLO - 1] == null) {
            colas[TAMANIO_ARREGLO - 1] = entrada;
            for (int i = TAMANIO_ARREGLO - 1; i > 0; i--) {
                if (colas[i - 1] == null) {
                    colas[i - 1] = colas[i];
                    colas[i] = null;
                } else {
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La cola esta llena", "Cola llena", JOptionPane.OK_OPTION);
        }
    }

    public void dequeue() {
        boolean bandera = false;
        if (colas[0] != null) {
            colas[0] = null;
            for (int i = 0; i < TAMANIO_ARREGLO - 1; i++) {
                if (colas[i + 1] != null) {
                    colas[i] = colas[i + 1];
                    colas[i + 1] = null;
                } else {
                    break;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "La cola esta vacia", "Cola vacia", JOptionPane.OK_OPTION);
        }
    }

    public Integer valor() {
        return colas[0];
    }

    @Override
    public String toString() {
        String valores = " ";
        for (int i = 0; i < TAMANIO_ARREGLO; i++) {
            if (colas[i] != null) {
                if (i == 0) {
                    valores += "[" + colas[i];
                } else if (i != TAMANIO_ARREGLO - 1 && colas[i + 1] != null) {
                    valores += ", " + colas[i];
                } else {
                    valores += ", " + colas[i] + "]";
                }
            } else {
                if (i == 1) {
                    valores += "]";
                }
                break;
            }
        }
        return valores;
    }

    public static void main(String[] args) {
        Colas colas = new Colas();
        colas.menuPrincipal();

    }

    public void menuPrincipal() {
        Colas colas = new Colas();
        int key = 0;
        colas.crearCola();
        JOptionPane n = new JOptionPane();

        for (int i = 0; i <= 0;) {
            key = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero del metodo output:\n1. valor\n2. Enqueue\n3. Dequeue\n4. Salir"));
            char[] digitos = Integer.toString(key).toCharArray();
            for (int j = 0; j < Integer.toString(key).length(); j++) {
                if (digitos[j] == '0' || digitos[j] == '1' || digitos[j] == '2' || digitos[j] == '3' || digitos[j] == '4' || digitos[j] == '5' || digitos[j] == '6' || digitos[j] == '7' || digitos[j] == '8' || digitos[j] == '9') {
                    colas.metodosOut(key);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa una cantidad numerica", "Error", JOptionPane.ERROR_MESSAGE);

                    break;
                }
            }
        }
    }

    public void crearCola() {
        cola = new Colas();
    }

    public void metodosOut(int k) {
        switch (k) {
            case 1:
                JOptionPane.showMessageDialog(null, "El valor es: " + cola.valor());
                break;

            case 2:
                String eque = JOptionPane.showInputDialog(null, "Ingrese el valor a agregar:");
                System.out.println("Estamos en ventana 2");
                if (eque != null) {
                    try {
                        int valor = Integer.parseInt(eque);
                        cola.enqueue(valor);
                        JOptionPane.showMessageDialog(null, "Cola actualizada:" + cola.toString());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor ingresa una cantidad numerica", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;

            case 3:
                String deque = Integer.toString(JOptionPane.showConfirmDialog(null, "¡Esta seguro de eliminar un dato de la cola?", "Colas", JOptionPane.YES_NO_OPTION));
                if (deque.equals(Integer.toString(JOptionPane.YES_OPTION))) {
                    try {
                        cola.dequeue();
                        JOptionPane.showMessageDialog(null, "Cola actualizada:" + cola.toString());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Por favor ingresa una cantidad numerica", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (JOptionPane.OK_OPTION == JOptionPane.NO_OPTION) {
                }

                break;

            case 4:
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "Por favor ingresa un caso valido", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static JPanel BurbujaPanel() {
        JPanel tmp = new JPanel(new BorderLayout());
        JPanel botones = new JPanel(new FlowLayout());
        botones.setPreferredSize(new Dimension(550, 55));
        JPanel impresion = new JPanel(new BorderLayout());
        JButton btnMejor = new JButton("Colas", new ImageIcon("diagrama.png"));
        JButton btnPeor = new JButton("Pilas", new ImageIcon("diagrama.png"));
        JButton btnAleatorio = new JButton("Cola de doble inserción", new ImageIcon("diagrama.png"));
        JTextField tamanio = new JTextField(5);
        btnMejor.setFont(new Font("Arial", Font.BOLD, 16));
        btnPeor.setFont(new Font("Arial", Font.BOLD, 16));
        btnAleatorio.setFont(new Font("Arial", Font.BOLD, 16));

        btnMejor.setForeground(new Color(213, 159, 15));
        btnMejor.setBackground(new Color(0, 43, 121));
        btnPeor.setForeground(new Color(213, 159, 15));
        btnPeor.setBackground(new Color(0, 43, 121));
        btnAleatorio.setForeground(new Color(213, 159, 15));
        btnAleatorio.setBackground(new Color(0, 43, 121));

        btnMejor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Colas colas = new Colas();
                colas.menuPrincipal();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnMejor.setBackground(new Color(213, 159, 15));
                btnMejor.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnMejor.setForeground(new Color(213, 159, 15));
                btnMejor.setBackground(new Color(0, 43, 121));
            }
        });

        btnPeor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPeor.setBackground(new Color(213, 159, 15));
                btnPeor.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnPeor.setForeground(new Color(213, 159, 15));
                btnPeor.setBackground(new Color(0, 43, 121));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                Pilas pila;
                int key = 0;
                pila = new Pilas();
                pila.crearPila();
                for (int i = 0; i <= 0;) {
                    key = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero del metodo output:\n1. Peek\n2. Push\n3. Pop\n4. Salir"));
                    char[] digitos = Integer.toString(key).toCharArray();
                    for (int j = 0; j < Integer.toString(key).length(); j++) {
                        if (digitos[j] == '0' || digitos[j] == '1' || digitos[j] == '2' || digitos[j] == '3' || digitos[j] == '4' || digitos[j] == '5' || digitos[j] == '6' || digitos[j] == '7' || digitos[j] == '8' || digitos[j] == '9') {
                            pila.metodosOut(key);
                        } else {
                            JOptionPane.showMessageDialog(null, "Por favor ingresa una cantidad numerica", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                }
            }
        });

        btnAleatorio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnAleatorio.setBackground(new Color(213, 159, 15));
                btnAleatorio.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAleatorio.setForeground(new Color(213, 159, 15));
                btnAleatorio.setBackground(new Color(0, 43, 121));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                ListaCircular listaCircular = new ListaCircular();
                int opcion = 0, elemento = 0;
                boolean eliminado = false;
                do {
                    try {
                        opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                                "1. Agregar un Nodo la lista circular\n"
                                + "2. Eliminar un Nodo de la lista circular\n"
                                + "3. Mostrar los datos de la lista circular\n"
                                + "4. Salir\n"
                                + "¿Que deseas hacer?", "Menú de Opciones", JOptionPane.INFORMATION_MESSAGE));
                        switch (opcion) {
                            case 1:
                                elemento = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el elemento del Nodo",
                                        "Agregando Nodo a la lista circular", JOptionPane.INFORMATION_MESSAGE));
                                listaCircular.insertar(elemento);
                                break;
                            case 2:
                                if (!listaCircular.estaVacia()) {
                                    elemento = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresa el elemento del Nodo a eliminar",
                                            "Eliminando nodo", JOptionPane.INFORMATION_MESSAGE));
                                    int x = JOptionPane.showConfirmDialog(null, "Desea eliminar el elemento: " + elemento, "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                                    if (x == JOptionPane.YES_OPTION) {
                                        eliminado = listaCircular.eliminar(elemento);
                                        if (eliminado) {

                                        } else {
                                            JOptionPane.showMessageDialog(null, "El elemento: " + elemento + "No esta en la lista",
                                                    "Elemento no encontrado", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showConfirmDialog(null, "Se ha cancelado la eliminación", "Aviso", JOptionPane.CANCEL_OPTION);
                                    }
                                } else {
                                    JOptionPane.showConfirmDialog(null, "No hay nodos aún", "Lista vacía",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                break;
                            case 3:
                                if (!listaCircular.estaVacia()) {
                                    listaCircular.mostrarLista();
                                } else {
                                    JOptionPane.showConfirmDialog(null, "No hay nodos aún", "Lista vacía",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                                break;
                            case 4:
                                JOptionPane.showConfirmDialog(null, "Aplicación finalizada",
                                        "Saliendo", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            default:
                                JOptionPane.showConfirmDialog(null, "La opción no esta en el menú",
                                        "Incorrecto", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (NumberFormatException n) {
                        JOptionPane.showConfirmDialog(null, "Error " + n.getMessage());
                    }
                } while (opcion != 4);
            }
        });

        botones.add(btnMejor);
        botones.add(btnPeor);
        botones.add(btnAleatorio);

        tmp.add(botones, BorderLayout.NORTH);
        tmp.add(impresion, BorderLayout.CENTER);

        return tmp;
    }
}
