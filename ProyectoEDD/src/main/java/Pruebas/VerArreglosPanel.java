package Pruebas;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VerArreglosPanel extends JPanel {
    private Connection conexion;
    private JPanel cardPanel;
    private int numeroArreglo = 1;
    private JDialog finalDetallesDialog;
    private JDialog detallesDialog;

    public VerArreglosPanel(Connection conexion) {
        this.conexion = conexion;
        setLayout(new BorderLayout());
        cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        add(scrollPane, BorderLayout.CENTER);
    }

    void cargarArreglos() {
        try (Statement statement = conexion.createStatement()) {
            String consulta = "SELECT idPadre, MAX(fecha) AS fechaCreacion, MAX(hora) AS horaCreacion FROM datoshijos GROUP BY idPadre";

            consulta += " ORDER BY fechaCreacion DESC, horaCreacion DESC";

            ResultSet resultSet = statement.executeQuery(consulta);
            int posicionArreglo = 0;

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
                card.add(detallesPanel, BorderLayout.SOUTH);
                cardPanel.add(card);
                numeroArreglo++;
                int finalPosicionArreglo = posicionArreglo;
                detallesPanel.add(dateLabel);
                detallesPanel.add(fechaLabel);
                detallesPanel.add(timeLabel);
                detallesPanel.add(horaLabel);

                JButton detailsButton = new JButton("Ver Arreglos");
                detallesPanel.add(detailsButton);

                detailsButton.addActionListener(e -> mostrarDetalles(idPadre, card, finalPosicionArreglo));
                posicionArreglo++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void mostrarDetalles(int idPadre, JPanel card, int posicionArreglo) {
        try (Statement statement = conexion.createStatement()) {
            String consulta = "SELECT * FROM datoshijos WHERE idPadre = " + idPadre;
            ResultSet resultSet = statement.executeQuery(consulta);
            List<String> datosList = new ArrayList<>(); // Lista para almacenar los datos originales

            String fechaCreacion = "";
            String horaCreacion = "";

            while (resultSet.next()) {
                fechaCreacion = resultSet.getString("fecha");
                horaCreacion = resultSet.getString("hora");
                String datosHijo = resultSet.getString("datosHijo");
                String[] datosArray = datosHijo.split(",");
                for (String dato : datosArray) {
                    datosList.add(dato.trim().toLowerCase());
                }
            }
            datosList.sort(String.CASE_INSENSITIVE_ORDER);

            JPanel detallesPanel = new JPanel(new BorderLayout());
            detallesPanel.setBackground(Color.WHITE);
            JPanel infoPanel = new JPanel(new BorderLayout());


            JTextArea datosTextArea = new JTextArea(10, 30);
            datosTextArea.setEditable(false);

            for (String dato : datosList) {
                String datoFormateado = capitalizeWords(dato);
                datosTextArea.append("Dato del arreglo: " + datoFormateado + "\n");
            }

            JScrollPane scrollPane = new JScrollPane(datosTextArea);
            detallesPanel.add(scrollPane, BorderLayout.CENTER);

            JLabel fechaLabel = new JLabel("Arreglo creado: " + fechaCreacion);
            fechaLabel.setHorizontalAlignment(SwingConstants.LEFT);
            JLabel horaLabel = new JLabel("Hora: " + horaCreacion);
            horaLabel.setHorizontalAlignment(SwingConstants.LEFT);
            infoPanel.add(fechaLabel, BorderLayout.NORTH);
            infoPanel.add(horaLabel, BorderLayout.SOUTH);
            detallesPanel.add(infoPanel, BorderLayout.NORTH);

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton insertButton = new JButton("Insertar");
            insertButton.setPreferredSize(new Dimension(100, 30));
            bottomPanel.add(insertButton);
            JButton eliminarButton = new JButton("Eliminar"); // Botón de Eliminar
            eliminarButton.setPreferredSize(new Dimension(100, 30));
            bottomPanel.add(eliminarButton);
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
                    if (nuevoDato != null) {
                        insertarDato(idPadre, nuevoDato);

                        detallesDialog.dispose();
                        mostrarDetalles(idPadre, card, posicionArreglo);
                    } else {

                        JOptionPane.showMessageDialog(card, "Acción cancelada");
                    }
                }
            });

            eliminarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String datoAEliminar = JOptionPane.showInputDialog(card, "Ingrese el dato a eliminar:");
                    if (datoAEliminar != null) {
                        eliminarDato(idPadre, datoAEliminar);

                        detallesDialog.dispose();
                        mostrarDetalles(idPadre, card, posicionArreglo);
                    } else {

                        JOptionPane.showMessageDialog(card, "Acción cancelada");
                    }
                }
            });


            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    detallesDialog.dispose();
                }
            });
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
    public void eliminarDato(int id, String datoAEliminar) {
        try (PreparedStatement preparedStatement = conexion.prepareStatement(
                "SELECT datosHijo FROM datoshijos WHERE id = ?"
        )) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String datosHijo = resultSet.getString("datosHijo");
                String[] datosArray = datosHijo.split(",");

                List<String> datosList = new ArrayList<>(Arrays.asList(datosArray));
                String datoAEliminarLowerCase = datoAEliminar.toLowerCase().trim(); // Convertir a minúsculas y quitar espacios en blanco


                datosList.removeIf(dato -> dato.toLowerCase().trim().equals(datoAEliminarLowerCase));


                String datosNuevos = String.join(",", datosList);


                try (PreparedStatement updateStatement = conexion.prepareStatement(
                        "UPDATE datoshijos SET datosHijo = ? WHERE id = ?"
                )) {
                    updateStatement.setString(1, datosNuevos);
                    updateStatement.setInt(2, id);

                    int filasActualizadas = updateStatement.executeUpdate();

                    if (filasActualizadas > 0) {
                        System.out.println("Se ha eliminado el dato con éxito.");
                    } else {
                        System.out.println("No se pudo eliminar el dato.");
                    }
                }
            } else {
                System.out.println("No se encontró el registro con ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private String capitalizeWords(String input) {
        String[] words = input.split("\\s");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    result.append(word.substring(1).toLowerCase());
                }
            }
        }

        return result.toString();
    }

}