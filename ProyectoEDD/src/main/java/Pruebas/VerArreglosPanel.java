package Pruebas;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class VerArreglosPanel extends JPanel {
    private Connection conexion;
    private JPanel cardPanel;
    private int numeroArreglo = 1;
    private List<String> datos = new ArrayList<>(); // Lista para almacenar los datos
    private JDialog finalDetallesDialog;
    public VerArreglosPanel(Connection conexion) {
        this.conexion = conexion;
        setLayout(new BorderLayout());
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        add(scrollPane, BorderLayout.CENTER);
        cargarArreglos();
    }
    void cargarArreglos() {
        try (Statement statement = conexion.createStatement()) {
            String consulta = "SELECT idPadre, MAX(fecha) AS fechaCreacion, MAX(hora) AS horaCreacion FROM datoshijos GROUP BY idPadre";
            ResultSet resultSet = statement.executeQuery(consulta);
            int posicionArreglo = 0; // Inicializar la posición del arreglo

            while (resultSet.next()) {
                int idPadre = resultSet.getInt("idPadre");
                String fechaCreacion = resultSet.getString("fechaCreacion");
                String horaCreacion = resultSet.getString("horaCreacion");
                JPanel card = new JPanel();
                card.setPreferredSize(new Dimension(200, 200)); // Tamaño de 200x200 píxeles
                card.setLayout(new BorderLayout());
                Border roundedBorder = BorderFactory.createLineBorder(Color.BLACK);
                card.setBorder(BorderFactory.createCompoundBorder(roundedBorder, roundedBorder));
                card.setBackground(Color.WHITE);
                JLabel arreglosLabel = new JLabel("Arreglos");
                arreglosLabel.setFont(new Font("Arial", Font.BOLD, 18));
                arreglosLabel.setHorizontalAlignment(SwingConstants.CENTER);
                card.add(arreglosLabel, BorderLayout.NORTH);
                JLabel titleLabel = new JLabel("Arreglo " + posicionArreglo);
                titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                card.add(titleLabel, BorderLayout.CENTER);
                JPanel detallesPanel = new JPanel();
                detallesPanel.setLayout(new GridLayout(0, 1));
                JLabel dateLabel = new JLabel("Fecha de Creación:");
                dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel fechaLabel = new JLabel(fechaCreacion);
                fechaLabel.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel timeLabel = new JLabel("Hora de Creación:");
                timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                JLabel horaLabel = new JLabel(horaCreacion);
                horaLabel.setHorizontalAlignment(SwingConstants.CENTER);
                JButton detailsButton = new JButton("Ver Arreglos");
                detallesPanel.add(dateLabel);
                detallesPanel.add(fechaLabel);
                detallesPanel.add(timeLabel);
                detallesPanel.add(horaLabel);
                detallesPanel.add(detailsButton);
                card.add(detallesPanel, BorderLayout.SOUTH);
                cardPanel.add(card);
                numeroArreglo++;
                int finalPosicionArreglo = posicionArreglo;
                detailsButton.addActionListener(e -> mostrarDetalles(idPadre, card, finalPosicionArreglo));
                posicionArreglo++; // Incrementar la posición del arreglo
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    void mostrarDetalles(int idPadre, JPanel card, int posicionArreglo) {
        try (Statement statement = conexion.createStatement()) {
            String consulta = "SELECT * FROM datoshijos WHERE idPadre = " + idPadre;
            ResultSet resultSet = statement.executeQuery(consulta);
            JPanel detallesPanel = new JPanel(new BorderLayout());
            detallesPanel.setBackground(Color.WHITE);
            JPanel infoPanel = new JPanel(new BorderLayout());
            String fechaCreacion = "";
            String horaCreacion = "";
            JPanel datosPanel = new JPanel(new GridLayout(0, 1));
            datosPanel.setBackground(Color.WHITE); // Fondo blanco
            int posicion = 1;
            while (resultSet.next()) {
                fechaCreacion = resultSet.getString("fecha");
                horaCreacion = resultSet.getString("hora");
                String datosHijo = resultSet.getString("datosHijo");
                String[] datosArray = datosHijo.split(",");
                for (String dato : datosArray) {
                    JLabel datoLabel = new JLabel(posicion + " Dato del arreglo: " + dato.trim());
                    datosPanel.add(datoLabel);
                    posicion++;
                }
            }
            JLabel fechaLabel = new JLabel("Arreglo creado: " + fechaCreacion);
            fechaLabel.setHorizontalAlignment(SwingConstants.LEFT);
            JLabel horaLabel = new JLabel("Hora: " + horaCreacion);
            horaLabel.setHorizontalAlignment(SwingConstants.LEFT);
            infoPanel.add(fechaLabel, BorderLayout.NORTH);
            infoPanel.add(horaLabel, BorderLayout.SOUTH);
            detallesPanel.add(infoPanel, BorderLayout.NORTH);
            detallesPanel.add(datosPanel, BorderLayout.CENTER);
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton insertButton = new JButton("Insertar");
            insertButton.setPreferredSize(new Dimension(100, 30));
            bottomPanel.add(insertButton);
            JButton closeButton = new JButton("Salir");
            closeButton.setPreferredSize(new Dimension(100, 30));
            bottomPanel.add(closeButton);
            detallesPanel.add(bottomPanel, BorderLayout.SOUTH);
            JDialog detallesDialog = new JDialog((Frame) null, "Detalles del ID: " + idPadre, false);
            detallesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            detallesDialog.setUndecorated(true);
            detallesDialog.setLocationRelativeTo(null);
            detallesDialog.setSize(400, 300);
            detallesDialog.getContentPane().setBackground(Color.WHITE);
            detallesPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            detallesDialog.add(detallesPanel);
            detallesDialog.setVisible(true);
            insertButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nuevoDato = JOptionPane.showInputDialog(card, "Ingrese el nuevo dato:");
                    insertarDato(idPadre, nuevoDato);
                    mostrarDetalles(idPadre, card, posicionArreglo);
                }
            });
            closeButton.addActionListener(e -> {
                detallesDialog.dispose();
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertarDato(int id, int posicion, String nuevoDato) {
        try (Statement statement = conexion.createStatement()) {
            String consulta = "SELECT * FROM datoshijos WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(consulta);
            if (resultSet.next()) {
                String datosHijo = resultSet.getString("datosHijo");
                String[] datosArray = datosHijo.split(",");
                if (posicion >= 0 && posicion <= datosArray.length) {
                    List<String> nuevosDatos = new ArrayList<>();
                    for (int i = 0; i < posicion; i++) {
                        nuevosDatos.add(datosArray[i]);
                    }
                    nuevosDatos.add(nuevoDato);
                    for (int i = posicion; i < datosArray.length; i++) {
                        nuevosDatos.add(datosArray[i]);
                    }
                    StringBuilder nuevoDatosHijo = new StringBuilder();
                    for (int i = 0; i < nuevosDatos.size(); i++) {
                        nuevoDatosHijo.append(nuevosDatos.get(i));
                        if (i < nuevosDatos.size() - 1) {
                            nuevoDatosHijo.append(",");
                        }
                    }
                    String actualizacion = "UPDATE datoshijos SET datosHijo = ? WHERE id = ?";
                    PreparedStatement preparedStatement = conexion.prepareStatement(actualizacion);
                    preparedStatement.setString(1, nuevoDatosHijo.toString());
                    preparedStatement.setInt(2, id);
                    int filasActualizadas = preparedStatement.executeUpdate();
                    if (filasActualizadas > 0) {
                        System.out.println("Se ha insertado el dato con éxito.");
                    } else {
                        System.out.println("No se pudo insertar el dato.");
                    }
                } else {
                    System.out.println("Posición no válida.");
                }
            } else {
                System.out.println("No se encontró el registro con ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertarDato(int id, String nuevoDato) {
        try (Statement statement = conexion.createStatement()) {
            String consulta = "SELECT * FROM datoshijos WHERE id = " + id;
            ResultSet resultSet = statement.executeQuery(consulta);
            if (resultSet.next()) {
                String datosHijo = resultSet.getString("datosHijo");
                datosHijo += "," + nuevoDato;
                String actualizacion = "UPDATE datoshijos SET datosHijo = ? WHERE id = ?";
                PreparedStatement preparedStatement = conexion.prepareStatement(actualizacion);
                preparedStatement.setString(1, datosHijo);
                preparedStatement.setInt(2, id);
                int filasActualizadas = preparedStatement.executeUpdate();

                if (filasActualizadas > 0) {
                    System.out.println("Se ha insertado el dato con éxito.");
                } else {
                    System.out.println("No se pudo insertar el dato.");
                }
            } else {
                System.out.println("No se encontró el registro con ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void actualizarInterfazUsuario(JPanel card) {

        card.removeAll();


        JPanel datosPanel = new JPanel(new GridLayout(0, 1));
        datosPanel.setBackground(Color.WHITE);


        for (int i = 0; i < datos.size(); i++) {
            String dato = datos.get(i);
            JLabel datoLabel = new JLabel(i + " - \"Dato del arreglo\": " + dato);
            datosPanel.add(datoLabel);
        }


        card.add(datosPanel, BorderLayout.CENTER);


        card.revalidate();
        card.repaint();
    }
}