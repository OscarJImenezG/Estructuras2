package Pruebas;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerArreglosPanel extends JPanel {
    private Connection conexion;
    private JTextArea areaTexto;

    public VerArreglosPanel(Connection conexion) {
        this.conexion = conexion;
        setLayout(new BorderLayout());

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaTexto);
        add(scrollPane, BorderLayout.CENTER);

        cargarArreglos();
    }

    void cargarArreglos() {
        try (Statement statement = conexion.createStatement()) {
            String consulta = "SELECT * FROM datoshijos";
            ResultSet resultSet = statement.executeQuery(consulta);

            areaTexto.setText(""); // Limpiar el área de texto antes de cargar los arreglos

            while (resultSet.next()) {
                int idPadre = resultSet.getInt("idPadre");
                String datosHijo = resultSet.getString("datosHijo");

                areaTexto.append("ID Padre: " + idPadre + "\n");
                areaTexto.append("Arreglo: " + datosHijo + "\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}