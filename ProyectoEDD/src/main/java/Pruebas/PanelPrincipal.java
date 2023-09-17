package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import static Pruebas.ConexionBD.conexion;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.Border;

public class PanelPrincipal extends JPanel {

    int xMouse, yMouse;
    private JLabel horaLabel;
    public JPanel contenedorCentral;
    private JButton botonArreglos;
    private JButton botonRegresar;
    private JButton btnVerInsertar;
    private JButton metodosDeOrdenacion;
    private JLabel salirlbl;
    private Arreglos arreglosPanel;
    //nuevo
    private VerArreglosPanel verArreglosFrame;
    private JButton verArreglosButton;
    private JButton matrizDispersa;

    public PanelPrincipal(Connection conexion) {

        setLayout(new BorderLayout());
        JPanel tituloPanel = crearBarraDeTitulo();
        add(tituloPanel, BorderLayout.NORTH);
        contenedorCentral = crearContenedorCentral();
        add(contenedorCentral, BorderLayout.CENTER);
        JPanel pieDePagina = crearPieDePaginaConBotones();
        add(pieDePagina, BorderLayout.SOUTH);
        //nuevo
        verArreglosFrame = new VerArreglosPanel(conexion);
        //Nuevo
        verArreglosButton = new JButton("Ver");

        verArreglosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    private JPanel crearBarraDeTitulo() {
        JPanel tituloPanel = new JPanel(new BorderLayout());
        int xMouse, yMouse;

        JLabel tituloLabel = new JLabel("Estructuras de Datos", SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(213, 159, 15));
        tituloLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JLabel fechaLabel = new JLabel(FechaYHora.obtenerFechaYHora());
        fechaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        fechaLabel.setForeground(new Color(213, 159, 15));
        fechaLabel.setBorder(new EmptyBorder(10, 10, 0, 0));

        String horaCronometro = FechaYHora.obtenerHoraCronometro();
        horaLabel = new JLabel(horaCronometro);
        horaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        horaLabel.setForeground(new Color(213, 159, 15));
        horaLabel.setBorder(new EmptyBorder(10, 0, 0, 10));
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String horaCronometro = FechaYHora.obtenerHoraCronometro();
                horaLabel.setText(horaCronometro);
            }
        });

        timer.start();

        tituloPanel.add(fechaLabel, BorderLayout.WEST);
        tituloPanel.add(tituloLabel, BorderLayout.CENTER);
        tituloPanel.add(horaLabel, BorderLayout.EAST);
        tituloPanel.setBorder(new LineBorder(Color.BLACK, 2));
        tituloPanel.setBackground(new Color(0, 43, 121));

        return tituloPanel;
    }

    private JPanel crearContenedorCentral() {
        JPanel contenedorCentral = new JPanel(new BorderLayout());

        JPanel imagenFondoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(0, 43, 121));

                int panelWidth = getWidth();
                int panelHeight = getHeight();

                double porcentajeAncho = 50;
                double porcentajeAlto = 70;

                int anchoImagen = (int) (panelWidth * porcentajeAncho / 100);
                int altoImagen = (int) (panelHeight * porcentajeAlto / 100);

                int x = (panelWidth - anchoImagen) / 2;
                int y = (panelHeight - altoImagen) / 2;
                String img = System.getProperty("user.dir");
                String rutaImagen = "UNAMSIUUU.png";
                ImageIcon icono = new ImageIcon(rutaImagen);
                icono = new ImageIcon(icono.getImage().getScaledInstance(anchoImagen, altoImagen, Image.SCALE_SMOOTH));
                icono.paintIcon(this, g, x, y);
            }
        };
        contenedorCentral.add(imagenFondoPanel, BorderLayout.CENTER);

        return contenedorCentral;
    }

    private JPanel crearPieDePaginaConBotones() {
        JPanel pieDePagina = new JPanel(/*new FlowLayout(FlowLayout.CENTER, 10, 0*/ new BorderLayout());
        pieDePagina.setBackground(new Color(0, 43, 121));
        String img = System.getProperty("user.dir");
        String rutaImagen = "agregar_32.png";
        botonArreglos = new JButton("Arreglos", new ImageIcon(rutaImagen));
        //eventoSobreBoton(botonArreglos, new ImageIcon("agregar_32.png"), new ImageIcon("agregar_64.png"));
        botonArreglos.setFont(new Font("Arial", Font.BOLD, 18));
        botonArreglos.setBackground(new Color(0, 43, 121));
        botonArreglos.setForeground(new Color(213, 159, 15));
        
        botonArreglos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanelArreglosCola();

            }
        });

        botonArreglos.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                botonArreglos.setBackground(new Color(213, 159, 15));
                botonArreglos.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonArreglos.setBackground(new Color(0, 43, 121));
                botonArreglos.setForeground(new Color(213, 159, 15));
            }
        });

        //Creamos los botones y les asignmos sus iconos:
        String rutaregresar = "regresar_32.png";
        botonRegresar = new JButton("Regresar", new ImageIcon(rutaregresar));

        salirlbl = new JLabel(new ImageIcon("salirOpaco.png"));
        btnVerInsertar = new JButton("Ver - Insertar", new ImageIcon("ver_32.png"));
        metodosDeOrdenacion = new JButton("M. de ordenación");
        matrizDispersa = new JButton("Matrices", new ImageIcon("matriz.png"));

        //Agregamos eventos vizuales sobre los botones:
        /*  this.eventoSobreBoton(botonRegresar, new ImageIcon("regresar_32.png"), new ImageIcon("regresar_64.png"));
        this.eventoSobreBoton(btnVer, new ImageIcon("ver_32.png"), new ImageIcon("ver_64.png"));
        this.eventoSobreBoton(btnInsert, new ImageIcon("añadir_32.png"), new ImageIcon("añadir_64.png")); */
        metodosDeOrdenacion.setFont(new Font("Arial", Font.BOLD, 18));
        btnVerInsertar.setFont(new Font("Arial", Font.BOLD, 18));
        botonRegresar.setFont(new Font("Arial", Font.BOLD, 18));
        matrizDispersa.setFont(new Font("Arial", Font.BOLD, 18));
        matrizDispersa.setBackground(new Color(0, 43, 121));
        matrizDispersa.setForeground(new Color(213, 159, 15));
        metodosDeOrdenacion.setBackground(new Color(0, 43, 121));
        metodosDeOrdenacion.setForeground(new Color(213, 159, 15));
        botonRegresar.setBackground(new Color(0, 43, 121));
        botonRegresar.setForeground(new Color(213, 159, 15));
        btnVerInsertar.setBackground(new Color(0, 43, 121));
        btnVerInsertar.setForeground(new Color(213, 159, 15));

        matrizDispersa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                matrizDispersa.setForeground(new Color(0, 43, 121));
                matrizDispersa.setBackground(new Color(213, 159, 15));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                matrizDispersa.setBackground(new Color(0, 43, 121));
                matrizDispersa.setForeground(new Color(213, 159, 15));
            }
        });
        metodosDeOrdenacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenedorCentral.removeAll();
                /*MatriceSupeInfer mdp = new MatriceSupeInfer();
                mdp.createAndShowGUI();
                contenedorCentral.add(mdp, BorderLayout.CENTER);*/
                contenedorCentral.repaint();
                botonRegresar.setVisible(true);
                botonArreglos.setVisible(false);
                matrizDispersa.setVisible(false);
                metodosDeOrdenacion.setText("Menu de metodos");
            }
        });
        salirlbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                salirlbl.setIcon(new ImageIcon("salir_64.png"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                salirlbl.setIcon(new ImageIcon("salirOpaco.png"));
            }
        });

        botonRegresar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                botonRegresar.setBackground(new Color(213, 159, 15));
                botonRegresar.setForeground(new Color(0, 43, 121));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonRegresar.setBackground(new Color(0, 43, 121));
                botonRegresar.setForeground(new Color(213, 159, 15));
            }
        });

        metodosDeOrdenacion.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                metodosDeOrdenacion.setBackground(new Color(213, 159, 15));
                metodosDeOrdenacion.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                metodosDeOrdenacion.setBackground(new Color(0, 43, 121));
                metodosDeOrdenacion.setForeground(new Color(213, 159, 15));
            }
        });

        btnVerInsertar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contenedorCentral.removeAll();
                VerArreglosPanel ventanaVerArreglos = new VerArreglosPanel(conexion);
                ventanaVerArreglos.cargarArreglos();
                contenedorCentral.add(ventanaVerArreglos, BorderLayout.CENTER);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnVerInsertar.setBackground(new Color(213, 159, 15));
                btnVerInsertar.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnVerInsertar.setBackground(new Color(0, 43, 121));
                btnVerInsertar.setForeground(new Color(213, 159, 15));
            }
        });

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("principal");
                matrizDispersa.setText("Matrices");
                metodosDeOrdenacion.setVisible(true);
            }
        });
        matrizDispersa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenedorCentral.removeAll();
                JPanel tmp = new JPanel();
                tmp.setLayout(new FlowLayout());
                JButton matrizDispers = new JButton("Matriz Dispersa", new ImageIcon("matriz.png"));
                JButton matrizSuperInfer = new JButton("Matriz Inferior y superior", new ImageIcon("matriz.png"));
                matrizDispers.setFont(new Font("Arial", Font.BOLD, 18));
                matrizDispers.setBackground(new Color(0, 43, 121));
                matrizDispers.setForeground(new Color(213, 159, 15));
                matrizSuperInfer.setFont(new Font("Arial", Font.BOLD, 18));
                matrizSuperInfer.setBackground(new Color(0, 43, 121));
                matrizSuperInfer.setForeground(new Color(213, 159, 15));
                tmp.add(matrizDispers);
                tmp.add(matrizSuperInfer);
                contenedorCentral.add(tmp, BorderLayout.CENTER);
                botonRegresar.setVisible(true);
                botonArreglos.setVisible(false);
                metodosDeOrdenacion.setVisible(false);
                matrizDispersa.setText("Menú de matrices");

                matrizDispers.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        contenedorCentral.removeAll();
                        MatrizDispersaPanel mdp = new MatrizDispersaPanel();
                        mdp.createAndShowGUI();
                        contenedorCentral.add(mdp, BorderLayout.CENTER);
                        contenedorCentral.repaint();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        matrizDispers.setForeground(new Color(0, 43, 121));
                        matrizDispers.setBackground(new Color(213, 159, 15));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        matrizDispers.setBackground(new Color(0, 43, 121));
                        matrizDispers.setForeground(new Color(213, 159, 15));
                    }
                });

                matrizSuperInfer.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        contenedorCentral.removeAll();
                        MatriceSupeInfer mdp = new MatriceSupeInfer();
                        mdp.createAndShowGUI();
                        contenedorCentral.add(mdp, BorderLayout.CENTER);
                        contenedorCentral.repaint();
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        matrizSuperInfer.setForeground(new Color(0, 43, 121));
                        matrizSuperInfer.setBackground(new Color(213, 159, 15));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        matrizSuperInfer.setBackground(new Color(0, 43, 121));
                        matrizSuperInfer.setForeground(new Color(213, 159, 15));
                    }
                });
                contenedorCentral.repaint();
            }
        });

        botonRegresar.setVisible(false);
        btnVerInsertar.setVisible(false);
        JPanel pieTmp = new JPanel(new FlowLayout());
        pieTmp.setBackground(new Color(0, 43, 121));
        pieTmp.setSize(500, 55);
        pieTmp.add(botonArreglos);
        pieDePagina.add(botonRegresar, BorderLayout.WEST);
        pieTmp.add(btnVerInsertar);
        pieTmp.add(matrizDispersa);
        pieTmp.add(metodosDeOrdenacion);
        pieDePagina.add(pieTmp,BorderLayout.CENTER);
        pieDePagina.add(salirlbl, BorderLayout.EAST);

        pieDePagina.setPreferredSize(new Dimension(450, 55));

        return pieDePagina;
    }

    public void mostrarPanel(String nombrePanel) {
        contenedorCentral.removeAll();

        if ("arreglos".equals(nombrePanel)) {
            botonArreglos.setVisible(false);
            botonRegresar.setVisible(true);
            btnVerInsertar.setVisible(true);
            metodosDeOrdenacion.setVisible(false);
            matrizDispersa.setVisible(false);

            arreglosPanel = new Arreglos(this);
            contenedorCentral.add(arreglosPanel, BorderLayout.CENTER);

            contenedorCentral.revalidate();
        } else if ("principal".equals(nombrePanel)) {
            botonArreglos.setVisible(true);
            botonRegresar.setVisible(false);
            btnVerInsertar.setVisible(false);
            matrizDispersa.setVisible(true);

            contenedorCentral.add(crearContenedorCentral(), BorderLayout.CENTER);
            contenedorCentral.revalidate();
        }
    }

    public void mostrarVentana() {
        JFrame frame = new JFrame("Estructuras de Datos");
        frame.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                frame.setLocation(x - xMouse, y - yMouse);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xMouse = e.getX();
                yMouse = e.getY();
            }
            
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.add(this);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public void mostrarPanelArreglosCola() {
        contenedorCentral.removeAll();

        botonArreglos.setVisible(false);
        botonRegresar.setVisible(true);
        btnVerInsertar.setVisible(true);
        metodosDeOrdenacion.setVisible(false);
        matrizDispersa.setVisible(false);

        //ArreglosCola arreglosColaPanel = new ArreglosCola(this);
        Arreglos arreglos = new Arreglos();
        contenedorCentral.add(arreglos, BorderLayout.CENTER);

        contenedorCentral.revalidate();
    }

    
    public void eventoSobreBoton(JButton btn, ImageIcon img1, ImageIcon img2) {
        btn.setContentAreaFilled(false);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setPressedIcon(img1);
        btn.setRolloverIcon(img2);
    }
}
