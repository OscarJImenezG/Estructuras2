package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Inicio {
    private Connection conexion;

    public Inicio(Connection conexion) {
        this.conexion = conexion;
    }
    public void mostrarVentana() {
        PanelPrincipal panelPrincipal = new PanelPrincipal(conexion);
        panelPrincipal.mostrarVentana();
    }

    public static void main(String[] args) {
        Connection conexion = obtenerConexion();
        Inicio inicio = new Inicio(conexion);
        inicio.mostrarVentana();
    }

    private static Connection obtenerConexion() {
        String url = "jdbc:mysql://localhost:3306/estructurasdedatos";
        String usuario = "root";
        String contrasena = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejo de errores de conexión
            return null;
        }
    }
}