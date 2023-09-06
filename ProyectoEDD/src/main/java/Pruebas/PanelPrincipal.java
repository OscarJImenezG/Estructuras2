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

public class PanelPrincipal extends JPanel {
    private JLabel horaLabel;
    private JPanel contenedorCentral;
    private JPanel arreglosColaPanel;
    private JButton botonArreglos;
    private JButton botonRegresar;
    private JButton btnVer;
    private JButton btnInsert;
    private JLabel salirlbl;
    private Arreglos arreglosPanel;
    //nuevo
    private VerArreglosPanel verArreglosFrame;
    private JButton verArreglosButton;
    private JButton matrizDispersa;
    private MatrizDispersaPanel matrizDispersaPanel;

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

        JLabel tituloLabel = new JLabel("Estructuras de Datos",SwingConstants.CENTER);
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setForeground(new Color(213, 159, 15));
        tituloLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        JLabel fechaLabel = new JLabel(FechayHora.obtenerFechaYHora());
        fechaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        fechaLabel.setForeground(new Color(213, 159, 15));
        fechaLabel.setBorder(new EmptyBorder(10, 10, 0, 0));

        String horaCronometro = FechayHora.obtenerHoraCronometro();
        horaLabel = new JLabel(horaCronometro);
        horaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        horaLabel.setForeground(new Color(213, 159, 15));
        horaLabel.setBorder(new EmptyBorder(10, 0, 0, 10));
        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String horaCronometro = FechayHora.obtenerHoraCronometro();
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

                ImageIcon icono = new ImageIcon("UNAMSIUUU.png");
                icono = new ImageIcon(icono.getImage().getScaledInstance(anchoImagen, altoImagen, Image.SCALE_SMOOTH));
                icono.paintIcon(this, g, x, y);
            }
        };
        contenedorCentral.add(imagenFondoPanel, BorderLayout.CENTER);

        return contenedorCentral;
    }

    private JPanel crearPieDePaginaConBotones() {
        JPanel pieDePagina = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        pieDePagina.setBackground(new Color(0, 43, 121));

        botonArreglos = new JButton("Arreglos", new ImageIcon("agregar_32.png"));
        eventoSobreBoton(botonArreglos, new ImageIcon("agregar_32.png"), new ImageIcon("agregar_64.png"));
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
        botonRegresar = new JButton("Regresar", new ImageIcon("regresar_32.png"));
        botonRegresar.setSize(200,200);
        salirlbl = new JLabel(new ImageIcon("salirOpaco.png"));
        btnVer = new JButton("Ver", new ImageIcon("ver_32.png"));
        btnInsert = new JButton("Insertar", new ImageIcon("añadir_32.png"));
        matrizDispersa = new JButton("Matriz Dispersa");

        //Agregamos eventos vizuales sobre los botones:
        this.eventoSobreBoton(botonRegresar, new ImageIcon("regresar_32.png"), new ImageIcon("regresar_64.png"));
        this.eventoSobreBoton(btnVer, new ImageIcon("ver_32.png"), new ImageIcon("ver_64.png"));
        this.eventoSobreBoton(btnInsert, new ImageIcon("añadir_32.png"), new ImageIcon("añadir_64.png"));

        btnInsert.setFont(new Font("Arial", Font.BOLD, 18));
        btnVer.setFont(new Font("Arial", Font.BOLD, 18));
        botonRegresar.setFont(new Font("Arial", Font.BOLD, 18));
        matrizDispersa.setFont(new Font("Arial", Font.BOLD, 18));
        botonRegresar.setBackground(new Color(0, 43, 121));
        botonRegresar.setForeground(new Color(213, 159, 15));
        btnVer.setBackground(new Color(0, 43, 121));
        btnVer.setForeground(new Color(213, 159, 15));

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenedorCentral.removeAll();
                ArreglosCola arreglosCola = new ArreglosCola();
                contenedorCentral.add(arreglosCola, BorderLayout.CENTER);
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
                botonRegresar.setText("");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonRegresar.setBackground(new Color(0, 43, 121));
                botonRegresar.setText("Regresar");
            }
        });

        btnInsert.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                btnInsert.setBackground(new Color(213, 159, 15));
                btnInsert.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnInsert.setBackground(new Color(0, 43, 121));
                btnInsert.setForeground(new Color(213, 159, 15));
            }
        });

        btnVer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contenedorCentral.removeAll();
                VerArreglosPanel ventanaVerArreglos = new VerArreglosPanel(conexion);
                ventanaVerArreglos.cargarArreglos();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnVer.setBackground(new Color(213, 159, 15));
                btnVer.setForeground(new Color(0, 43, 121));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnVer.setBackground(new Color(0, 43, 121));
                btnVer.setForeground(new Color(213, 159, 15));
            }
        });

        botonRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPanel("principal");
            }
        });
        matrizDispersa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                contenedorCentral.removeAll();
                matrizDispersaPanel = new MatrizDispersaPanel("");
                contenedorCentral.add(matrizDispersaPanel, BorderLayout.CENTER);
                contenedorCentral.revalidate();
            }
        });
        matrizDispersa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contenedorCentral.removeAll();
                matrizDispersaPanel = new MatrizDispersaPanel("");
                contenedorCentral.add(matrizDispersaPanel, BorderLayout.CENTER);
                contenedorCentral.revalidate();
                contenedorCentral.repaint();
            }
        });

        botonRegresar.setVisible(false);
        btnVer.setVisible(false);
        btnInsert.setVisible(false);

        pieDePagina.add(botonArreglos);
        pieDePagina.add(botonRegresar);
        pieDePagina.add(btnInsert);
        pieDePagina.add(btnVer);
        pieDePagina.add(matrizDispersa);
        pieDePagina.add(salirlbl);

        pieDePagina.setPreferredSize(new Dimension(600, 70));

        return pieDePagina;
    }

    public void mostrarPanel(String nombrePanel) {
        contenedorCentral.removeAll();

        if ("arreglos".equals(nombrePanel)) {
            botonArreglos.setVisible(false);
            botonRegresar.setVisible(true);
            btnVer.setVisible(true);
            btnInsert.setVisible(true);
            matrizDispersa.setVisible(false);

            arreglosPanel = new Arreglos(this);
            contenedorCentral.add(arreglosPanel, BorderLayout.CENTER);

            contenedorCentral.revalidate();
        } else if ("principal".equals(nombrePanel)) {
            botonArreglos.setVisible(true);
            botonRegresar.setVisible(false);
            btnVer.setVisible(false);
            btnInsert.setVisible(false);
            matrizDispersa.setVisible(true);

            contenedorCentral.add(crearContenedorCentral(), BorderLayout.CENTER);
            contenedorCentral.revalidate();
        }
    }

    public void mostrarVentana() {
        JFrame frame = new JFrame("Estructuras de Datos");
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
        btnVer.setVisible(true);
        btnInsert.setVisible(true);
        matrizDispersa.setVisible(false);

        ArreglosCola arreglosColaPanel = new ArreglosCola(this);
        contenedorCentral.add(arreglosColaPanel, BorderLayout.CENTER);

        contenedorCentral.revalidate();
    }
    //Nuevo
    public void mostrarPanelVerArreglos() {
        // Lógica para mostrar la ventana de ver arreglos
        VerArreglosPanel ventanaVerArreglos = new VerArreglosPanel(conexion);
        ventanaVerArreglos.cargarArreglos();
    }

    public ImageIcon generarIconos(String string){
        return new ImageIcon(string);
    }

    public void eventoSobreBoton(JButton btn, ImageIcon img1, ImageIcon img2){
        btn.setContentAreaFilled(false);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setSelectedIcon(img2);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        btn.setPressedIcon(img1);
        btn.setRolloverIcon(img2);
    }
}