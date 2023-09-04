package Pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class ConexionBD {
    static Connection conexion;

    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/estructurasdedatos";
                String usuario = "root";
                String contraseña = "";
                conexion = DriverManager.getConnection(url, usuario, contraseña);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error al cerrar la conexión a la base de datos.");
            }
        }
    }
}